package com.aguilex.aguilexstupidities.client;

import com.aguilex.aguilexstupidities.block.custom.RadioactiveBlock;
import com.aguilex.aguilexstupidities.item.ModItems;
import com.aguilex.aguilexstupidities.sound.ModSounds;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class RadiationCounterHud implements HudRenderCallback {

    private float lastJitter = 0.0f;
    private long lastTickChange = 0;

    @Override
    public void onHudRender(GuiGraphics drawContext, float tickDelta) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null) return;

        Player player = client.player;
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        if (mainHand.is(ModItems.GEIGER_COUNTER) || offHand.is(ModItems.GEIGER_COUNTER)) {
            float baseRadiation = calculateSurfaceRadiation(player);

            // jitter 0.0 - 0.4 μSv/h
            long currentTime = player.level().getGameTime();
            if (currentTime - lastTickChange > 8) {
                lastJitter = player.getRandom().nextFloat() * 0.2f;
                lastTickChange = currentTime;
            }

            float finalValueMicro = baseRadiation + lastJitter;

            String displayText;
            int color;

            if (finalValueMicro >= 100.0f) {
                // mSv/h
                float valueMilli = finalValueMicro / 1000.0f;
                displayText = String.format("%.2f mSv/h", valueMilli);

                if (valueMilli >= 2.0f) {
                    color = 0xFFFF5555; // reed
                } else if (valueMilli >= 1.0f) {
                    color = 0xFFFFAA00; // orange
                } else {
                    color = 0xFF55FF55; // green
                }
            } else {
                // μSv/h
                displayText = String.format("%.2f μSv/h", finalValueMicro);
                color = 0xFFAAAAAA;
            }

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            drawContext.drawString(client.font, displayText, width - client.font.width(displayText) - 10, height - 20, color, true);

            if (player.level().getGameTime() != lastTickChange) {
                if (finalValueMicro > 0) {
                    float soundIntensity = Math.min(finalValueMicro / 7500.0f, 1.0f);

                    if (player.getRandom().nextFloat() < (0.003f + soundIntensity)) {
                        player.level().playLocalSound(
                                player.getX(), player.getY(), player.getZ(),
                                ModSounds.GEIGER_TICK,
                                net.minecraft.sounds.SoundSource.PLAYERS,
                                0.6f,
                                0.9f + player.getRandom().nextFloat() * 0.4f,
                                false
                        );
                    }
                }
            }
        }
    }

    private float calculateSurfaceRadiation(Player player) {
        float radiationSum = 0;
        double px = player.getX();
        double py = player.getY();
        double pz = player.getZ();
        BlockPos playerPos = player.blockPosition();
        int scanRange = 25;

        for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-scanRange, -scanRange, -scanRange),
                playerPos.offset(scanRange, scanRange, scanRange))) {
            BlockState state = player.level().getBlockState(pos);
            if (state.getBlock() instanceof RadioactiveBlock radBlock) {
                double closestX = Math.max(pos.getX(), Math.min(px, pos.getX() + 1.0));
                double closestY = Math.max(pos.getY(), Math.min(py, pos.getY() + 1.0));
                double closestZ = Math.max(pos.getZ(), Math.min(pz, pos.getZ() + 1.0));

                double dx = px - closestX;
                double dy = py - closestY;
                double dz = pz - closestZ;
                double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);

                double maxRadius = radBlock.getRadius();
                if (distance <= maxRadius) {
                    double ratio = distance / maxRadius;
                    float smoothForce = (float) Math.pow(1.0 - ratio, 2);
                    radiationSum += radBlock.getRadiationAmount() * smoothForce;
                }
            }
        }
        return radiationSum;
    }
}
