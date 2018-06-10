package io.github.phantamanta44.tmemes.item.base;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.TinkerRegistry;

public class ItemMod extends Item {

    protected final String modItemName;

    public ItemMod(String name, CreativeTabs tab) {
        this.modItemName = name;
        setUnlocalizedName(MEMES.MOD_PREF + name);
        setRegistryName(new ResourceLocation(MEMES.MOD_ID, name));
        setCreativeTab(tab);
        MEMES.proxy.registerItem(this);
        queueModelRegistration();
    }

    public ItemMod(String name) {
        this(name, TinkerRegistry.tabGeneral);
    }

    protected void queueModelRegistration() {
        MEMES.proxy.registerModel(this, 0, modItemName);
    }

}
