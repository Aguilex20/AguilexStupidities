package com.aguilex.aguilexstupidities.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DemonCoreBlockEntity extends BlockEntity {
    public DemonCoreBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.DEMON_CORE_BLOCK_ENTITY, blockPos, blockState);
    }
}
