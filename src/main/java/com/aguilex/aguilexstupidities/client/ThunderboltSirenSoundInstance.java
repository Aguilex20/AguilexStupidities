package com.aguilex.aguilexstupidities.client;

import com.aguilex.aguilexstupidities.block.entity.ThunderboltSirenBlockEntity;
import com.aguilex.aguilexstupidities.sound.ModSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class ThunderboltSirenSoundInstance extends AbstractTickableSoundInstance {
    private final ThunderboltSirenBlockEntity blockEntity;

    public ThunderboltSirenSoundInstance(ThunderboltSirenBlockEntity be) {
        super(ModSounds.THUNDERBOLT_SIREN, SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.blockEntity = be;
        this.looping = true;
        this.delay = 0;
        this.volume = 10.0f;
        this.x = (float) be.getBlockPos().getX() + 0.5f;
        this.y = (float) be.getBlockPos().getY() + 0.5f;
        this.z = (float) be.getBlockPos().getZ() + 0.5f;
        this.relative = false;
    }

    @Override
    public void tick() {
        if (this.blockEntity.isRemoved() || this.blockEntity.currentSpeed <= 0.005f) {
            this.stop();
            return;
        }

        float progress = this.blockEntity.currentSpeed / 0.8f;

        float correctedProgress = (float) Math.pow(progress, 1.0);

        this.pitch = 0.5f + (correctedProgress * 1.5f);
    }
}
