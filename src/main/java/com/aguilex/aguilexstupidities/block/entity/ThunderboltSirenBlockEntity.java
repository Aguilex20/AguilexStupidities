package com.aguilex.aguilexstupidities.block.entity;

import com.aguilex.aguilexstupidities.client.ThunderboltSirenSoundInstance;
import com.aguilex.aguilexstupidities.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ThunderboltSirenBlockEntity extends BlockEntity {
    private float yaw = 0;
    private float prevYaw = 0;
    public float currentSpeed = 0.0f;

    private ThunderboltSirenSoundInstance soundInstance;

    public ThunderboltSirenBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.THUNDERBOLT_SIREN_BLOCK_ENTITY, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ThunderboltSirenBlockEntity be) {
        be.prevYaw = be.yaw;
        if (level.hasNeighborSignal(blockPos)) {
            be.currentSpeed = Math.min(be.currentSpeed + 0.01f, 0.8f);
        } else {
            be.currentSpeed = Math.max(be.currentSpeed - 0.01f, 0.0f);
        }

        if (level.isClientSide && be.currentSpeed > 0.005f) {
            var soundManager = Minecraft.getInstance().getSoundManager();

            if (be.soundInstance == null || !soundManager.isActive(be.soundInstance)) {
                be.soundInstance = new ThunderboltSirenSoundInstance(be);
                soundManager.play(be.soundInstance);
            }
        }
        be.yaw = (be.yaw + be.currentSpeed) % 360;
    }

    public float getVisualYaw(float tickDelta) {
        float rotation = this.yaw - this.prevYaw;
        while (rotation < -180.0F) {
            rotation += 360.0F;
        }
        while (rotation >= 180.0F) {
            rotation -= 360.0F;
        }
        return this.prevYaw + tickDelta * rotation;
    }
}
