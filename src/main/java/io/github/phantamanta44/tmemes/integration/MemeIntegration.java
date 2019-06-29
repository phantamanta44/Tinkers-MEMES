package io.github.phantamanta44.tmemes.integration;

public interface MemeIntegration {

    default void preInit() {
        // NO-OP
    }

    default void postInit() {
        // NO-OP
    }

}
