package io.github.phantamanta44.tmemes.item;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MemeItems {

    public static ItemElectricUpgrade ELECTRIC_UPGRADE;

    public static void init() {
        ELECTRIC_UPGRADE = new ItemElectricUpgrade();
        MinecraftForge.EVENT_BUS.register(new MemeItems());
    }

    @SubscribeEvent
    public void doRegistration(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ELECTRIC_UPGRADE);
    }

}
