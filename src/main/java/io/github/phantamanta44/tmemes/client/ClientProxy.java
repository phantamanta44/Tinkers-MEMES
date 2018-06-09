package io.github.phantamanta44.tmemes.client;

import io.github.phantamanta44.tmemes.CommonProxy;
import io.github.phantamanta44.tmemes.MEMES;
import io.github.phantamanta44.tmemes.item.ItemElectricUpgrade;
import io.github.phantamanta44.tmemes.item.MemeItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders() {
        ModelLoader.setCustomModelResourceLocation(MemeItems.ELECTRIC_UPGRADE, 0,
                new ModelResourceLocation(MEMES.MOD_PREF + ItemElectricUpgrade.NAME, "inventory"));
    }

}
