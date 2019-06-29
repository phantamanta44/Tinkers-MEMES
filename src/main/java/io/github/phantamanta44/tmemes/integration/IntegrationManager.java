package io.github.phantamanta44.tmemes.integration;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegrationManager {

    @Nullable
    private static List<MemeIntegration> INTEGRATIONS = null;

    private static List<MemeIntegration> getIntegrations() {
        if (INTEGRATIONS == null) {
            INTEGRATIONS = Stream.of(
                    new Provider("conarm", "MemeIntegrationConArm", "client.MemeIntegrationConArmClient")
            ).filter(p -> Loader.isModLoaded(p.modId)).map(Provider::loadIntegration).collect(Collectors.toList());
        }
        return INTEGRATIONS;
    }

    public static void preInit() {
        getIntegrations().forEach(MemeIntegration::preInit);
    }

    public static void postInit() {
        getIntegrations().forEach(MemeIntegration::postInit);
    }

    private static class Provider {

        final String modId;
        private final String serverClass, clientClass;

        Provider(String modId, String serverClass, String clientClass) {
            this.modId = modId;
            this.serverClass = serverClass;
            this.clientClass = clientClass;
        }

        MemeIntegration loadIntegration() {
            try {
                return (MemeIntegration)Class.forName(getSidedClass()).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException("Could not initialize integration for " + modId + "!", e);
            }
        }

        private String getSidedClass() {
            return "io.github.phantamanta44.tmemes.integration." + modId + "." +
                    (FMLCommonHandler.instance().getSide() == Side.CLIENT ? clientClass : serverClass);
        }

    }

}
