package io.github.phantamanta44.tmemes;

import io.github.phantamanta44.tmemes.capability.CapabilityEventHandler;
import io.github.phantamanta44.tmemes.item.MemeItems;
import io.github.phantamanta44.tmemes.trait.MemeTraits;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MEMES.MOD_ID, name = MEMES.NAME, version = MEMES.VERSION)
public class MEMES {

    public static final String MOD_ID = "tmemes";
    public static final String NAME = "MEMES";
    public static final String VERSION = "1.0.0";
    public static final String MOD_PREF = MOD_ID + ":";

    @Mod.Instance(MOD_ID)
    public static MEMES INSTANCE;

    @SidedProxy(
            clientSide = "io.github.phantamanta44.tmemes.client.ClientProxy",
            serverSide = "io.github.phantamanta44.tmemes.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MemeItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MemeTraits.init();
    }

}
