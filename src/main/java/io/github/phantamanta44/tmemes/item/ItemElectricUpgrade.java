package io.github.phantamanta44.tmemes.item;

import io.github.phantamanta44.tmemes.item.base.ItemModSubs;

public class ItemElectricUpgrade extends ItemModSubs {

    public ItemElectricUpgrade() {
        super("electric_upgrade", 2);
    }

    @Override
    protected String getModelName(int variant) {
        switch (variant) {
            case 0:
                return "electric_upgrade";
            case 1:
                return "flux_field";
            default:
                return "missingno";
        }
    }

}
