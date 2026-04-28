package com.aguilex.aguilexstupidities.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ThunderboltSirenBlockEntity extends BlockEntity {
    public ThunderboltSirenBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.THUNDERBOLT_SIREN_BLOCK_ENTITY, blockPos, blockState);
    }
}
