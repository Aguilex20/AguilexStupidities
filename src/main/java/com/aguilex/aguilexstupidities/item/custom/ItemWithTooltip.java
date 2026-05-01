package com.aguilex.aguilexstupidities.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemWithTooltip extends Item {
    private final String fullTranslationKey;
    private final int maxChars;

    public ItemWithTooltip(Properties properties, String subName, int maxChars) {
        super(properties);
        this.fullTranslationKey = "item.aguilexstupidities." + subName + ".tooltip";
        this.maxChars = maxChars;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        String fullText = Component.translatable(this.fullTranslationKey).getString();
        String[] lines = WordUtils.wrap(fullText, this.maxChars).split(System.lineSeparator());

        for (String line : lines) {
            tooltip.add(Component.literal(line).withStyle(ChatFormatting.GRAY));
        }
    }
}
