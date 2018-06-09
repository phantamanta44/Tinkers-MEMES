package io.github.phantamanta44.tmemes.item;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.TinkerRegistry;

public class ItemElectricUpgrade extends Item {

    public static final String NAME = "electric_upgrade";

    public ItemElectricUpgrade() {
        setUnlocalizedName(MEMES.MOD_PREF + NAME);
        setRegistryName(new ResourceLocation(MEMES.MOD_ID, NAME));
        setCreativeTab(TinkerRegistry.tabGeneral);
    }

}
