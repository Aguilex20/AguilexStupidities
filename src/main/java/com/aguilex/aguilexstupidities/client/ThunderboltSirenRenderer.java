package com.aguilex.aguilexstupidities.client;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.entity.ThunderboltSirenBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class ThunderboltSirenRenderer implements BlockEntityRenderer<ThunderboltSirenBlockEntity> {
    private final BlockRenderDispatcher dispatcher;

    public ThunderboltSirenRenderer(BlockEntityRendererProvider.Context ctx) {
        this.dispatcher = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(ThunderboltSirenBlockEntity blockEntity, float tickDelta, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (blockEntity.getLevel() == null) return;

        ModelManager modelManager = Minecraft.getInstance().getModelManager();
        BakedModel headModel = modelManager.getModel(new ResourceLocation(AguilexStupidities.MOD_ID, "block/thunderbolt_siren_head"));

        if (headModel == modelManager.getMissingModel()) return;

        BlockState state = blockEntity.getBlockState();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutout());

        RandomSource random = RandomSource.create();

        poseStack.pushPose();

        poseStack.translate(0.5, 0.0, 0.5);
        float angle = blockEntity.getVisualYaw(tickDelta);
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        poseStack.translate(-0.5, -0.0, -0.5);

        this.dispatcher.getModelRenderer().tesselateBlock(
                blockEntity.getLevel(),
                headModel,
                state,
                blockEntity.getBlockPos(),
                poseStack,
                buffer,
                true,
                random,
                state.getSeed(blockEntity.getBlockPos()),
                packedOverlay
        );

        poseStack.popPose();
    }
}
