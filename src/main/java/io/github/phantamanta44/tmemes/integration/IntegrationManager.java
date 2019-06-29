package io.github.phantamanta44.tmemes.integration;

import io.github.phantamanta44.tmemes.integration.conarm.MemeIntegrationConArm;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegrationManager {

    @Nullable
    private static List<MemeIntegration> INTEGRATIONS = null;

    private static List<MemeIntegration> getIntegrations() {
        if (INTEGRATIONS == null) {
            INTEGRATIONS = Stream.of(new Provider("conarm", MemeIntegrationConArm::new))
                    .filter(p -> Loader.isModLoaded(p.modId))
                    .map(p -> p.factory.get())
                    .collect(Collectors.toList());
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
        final Supplier<MemeIntegration> factory;

        Provider(String modId, Supplier<MemeIntegration> factory) {
            this.modId = modId;
            this.factory = factory;
        }

    }

}
