package io.github.phantamanta44.tmemes.item.base;

import io.github.phantamanta44.tmemes.MEMES;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Adapted from io.github.phantamanta44.libnine.item.L9ItemSubs
 * https://github.com/phantamanta44/libnine/blob/1.12.2/src/main/java/io/github/phantamanta44/libnine/item/L9ItemSubs.java
 */
public class ItemModSubs extends ItemMod {

    private final int variantCount;

    public ItemModSubs(String name, int variantCount) {
        super(name);
        setHasSubtypes(true);
        this.variantCount = variantCount;
    }

    /*
     * Initializers
     */

    @Override
    protected void initModel() {
        for (int i = 0; i < variantCount; i++) {
            MEMES.proxy.registerModel(this, i, getModelName(i));
        }
    }

    protected String getModelName(int variant) {
        return getInternalName();
    }

    /*
     * Properties
     */

    public int getVariantCount() {
        return variantCount;
    }

    /*
     * Behaviour
     */

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (int i = 0; i < variantCount; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + stack.getMetadata();
    }

}
