package com.aguilex.aguilexstupidities.client;

import com.aguilex.aguilexstupidities.AguilexStupidities;
import com.aguilex.aguilexstupidities.block.custom.DemonCoreBlock;
import com.aguilex.aguilexstupidities.block.entity.DemonCoreBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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

public class DemonCoreRenderer implements BlockEntityRenderer<DemonCoreBlockEntity> {
    private final BlockRenderDispatcher dispatcher;
    private static final ResourceLocation TOP_MODEL_RL = new ResourceLocation(AguilexStupidities.MOD_ID, "block/demon_core_top");
    private static final ResourceLocation SCREWDRIVER_MODEL_RL = new ResourceLocation(AguilexStupidities.MOD_ID, "block/demon_core_screwdriver");

    public DemonCoreRenderer(BlockEntityRendererProvider.Context ctx) {
        this.dispatcher = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(DemonCoreBlockEntity blockEntity, float tickDelta, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();

        if (blockEntity.getLevel() == null) return;

        if (!state.getValue(DemonCoreBlock.SCREWDRIVER)) {
            return;
        }

        ModelManager modelManager = this.dispatcher.getBlockModelShaper().getModelManager();
        BakedModel topModel = modelManager.getModel(TOP_MODEL_RL);
        BakedModel screwdriverModel = modelManager.getModel(SCREWDRIVER_MODEL_RL);

        if (topModel == modelManager.getMissingModel() || screwdriverModel == modelManager.getMissingModel()) return;

        VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutout());
        RandomSource random = RandomSource.create();
        long seed = state.getSeed(blockEntity.getBlockPos());

        // top render
        poseStack.pushPose();
        poseStack.translate(0.25, 0.375, 0.5);
        poseStack.mulPose(Axis.ZP.rotationDegrees(7.5f));
        poseStack.translate(-0.25, -0.375, -0.5);

        this.dispatcher.getModelRenderer().tesselateBlock(
                blockEntity.getLevel(), topModel, state, blockEntity.getBlockPos(),
                poseStack, buffer, true, random, seed, packedOverlay
        );
        poseStack.popPose();

        // screwdriver render
        poseStack.pushPose();
        poseStack.translate(0.75, 0.375, 0.5);
        poseStack.mulPose(Axis.ZP.rotationDegrees(5f));
        poseStack.translate(-0.75, -0.375, -0.5);

        this.dispatcher.getModelRenderer().tesselateBlock(
                blockEntity.getLevel(), screwdriverModel, state, blockEntity.getBlockPos(),
                poseStack, buffer, true, random, seed, packedOverlay
        );
        poseStack.popPose();
    }
}
