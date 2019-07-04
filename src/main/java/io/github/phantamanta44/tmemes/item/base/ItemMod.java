package io.github.phantamanta44.tmemes.item.base;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.TinkerRegistry;

/**
 * Adapted from io.github.phantamanta44.libnine.item.L9Item
 * (which, ironically enough, was derived from an earlier version of ItemMod)
 * https://github.com/phantamanta44/libnine/blob/1.12.2/src/main/java/io/github/phantamanta44/libnine/item/L9Item.java
 */
public class ItemMod extends Item {

    private final String internalName;

    public ItemMod(String name, CreativeTabs tab) {
        this.internalName = name;
        initName();
        initRegistration();
        setCreativeTab(tab);
    }

    public ItemMod(String name) {
        this(name, TinkerRegistry.tabGeneral);
    }

    public void postInit() {
        initModel();
    }

    /*
     * Initializers
     */

    protected void initName() {
        setUnlocalizedName(MEMES.MOD_PREF + getInternalName());
    }

    protected void initRegistration() {
        setRegistryName(new ResourceLocation(MEMES.MOD_ID, getInternalName()));
        MEMES.proxy.registerItem(this);
    }

    protected void initModel() {
        MEMES.proxy.registerModel(this, 0, getInternalName());
    }

    /*
     * Properties
     */

    public String getInternalName() {
        return internalName;
    }

}
