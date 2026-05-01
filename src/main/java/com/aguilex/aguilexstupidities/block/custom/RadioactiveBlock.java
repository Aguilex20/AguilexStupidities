package com.aguilex.aguilexstupidities.block.custom;

import net.minecraft.world.level.block.Block;

public class RadioactiveBlock extends Block {
    private final float radiationAmount;
    private final double radius;

    public RadioactiveBlock(Properties properties, float μSvh, double radius) {
        super(properties);
        this.radiationAmount = μSvh;
        this.radius = radius;
    }

    public float getRadiationAmount() {
        return radiationAmount;
    }

    public double getRadius() {
        return radius;
    }
}
