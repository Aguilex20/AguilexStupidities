package com.aguilex.aguilexstupidities.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DuracellBatteryBlockEntity extends BlockEntity {
    private int tickCount = 0;
    private boolean firstPhase = true;

    public DuracellBatteryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.DURACELL_BATTERY_BLOCK_ENTITY, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DuracellBatteryBlockEntity blockEntity) {
        if (level.isClientSide) return;

        long time = level.getGameTime();

        if (time % 10 == 0) {
            level.updateNeighborsAt(pos, state.getBlock());
        }
    }
}
