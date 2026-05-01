package com.aguilex.aguilexstupidities.client;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.entity.ModBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;

public class AguilexStupiditiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.THUNDERBOLT_SIREN_BLOCK_ENTITY, ThunderboltSirenRenderer::new);

        HudRenderCallback.EVENT.register(new RadiationCounterHud());

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ResourceLocation(AguilexStupidities.MOD_ID, "block/thunderbolt_siren_base"));
            out.accept(new ResourceLocation(AguilexStupidities.MOD_ID, "block/thunderbolt_siren_head"));
        });
    }
}
