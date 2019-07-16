package io.github.phantamanta44.tmemes;

import net.minecraftforge.common.config.Config;

@Config(modid = MEMES.MOD_ID)
public class MemeConfig {

    @Config.Comment("Enable/disable the Electromechanical modifier.")
    @Config.RequiresMcRestart
    public static boolean enableElectromechanical = true;

    @Config.Comment("Enable/disable the Disassembling modifier.")
    @Config.RequiresMcRestart
    public static boolean enableDisassembling = true;

    @Config.Comment("Enable/disable the Directed Flux Field modifier.")
    @Config.RequiresMcRestart
    public static boolean enableDirectedFluxField = true;

    @Config.Comment("Enable/disable the Electromechanical modifier for Construct's Armoury equipment.")
    @Config.RequiresMcRestart
    public static boolean enableConArmElectromechanical = true;

    @Config.Comment("Configuration for the Electromechnical modifier.")
    public static ElectromechanicalConfig electromechanical = new ElectromechanicalConfig();

    @Config.Comment("Configuration for the Directed Flux Field modifier.")
    public static FluxFieldConfig directedFluxField = new FluxFieldConfig();

    @Config.Comment("Configuration for the Electromechanical modifier for Construct's Armoury equipment.")
    public static ArmouryConfig conarm = new ArmouryConfig();

    public static class ElectromechanicalConfig {

        @Config.Comment("The maximum level that Electromechanical can be stacked to.")
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int maxLevel = 5;

        @Config.Comment("The amount of energy to expand the energy buffer by per modifier level of Electromechanical.")
        @Config.RangeInt(min = 0)
        public int energyBufferPerLevel = 80000;

        @Config.Comment("The amount of energy to be consumed per durability point absorbed by Electromechanical.")
        @Config.RangeInt(min = 1)
        public int energyUse = 160;

        @Config.Comment("The chance for Electromechanical to absorb tool damage at level 1.")
        @Config.RangeDouble(min = 0, max = 1)
        public double baseProcChance = 1D;

        @Config.Comment("The added chance for Electromechanical to absorb tool damage per additional level.")
        @Config.RangeDouble(min = 0, max = 1)
        public double additionalProcChance = 0D;

    }

    public static class FluxFieldConfig {

        @Config.Comment("The maximum level that Directed Flux Field can be stacked to.")
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int maxLevel = 1;

        @Config.Comment("The amount of energy to be consumed per durability point absorbed by Directed Flux Field.")
        @Config.RangeInt(min = 1)
        public int energyUse = 160;

        @Config.Comment("The chance for Directed Flux Field to absorb tool damage at level 1.")
        @Config.RangeDouble(min = 0, max = 1)
        public double baseProcChance = 1D;

        @Config.Comment("The added chance for Directed Flux Field to absorb tool damage per additional level.")
        @Config.RangeDouble(min = 0, max = 1)
        public double additionalProcChance = 0D;

    }

    public static class ArmouryConfig {

        @Config.Comment("The maximum level that Electromechanical can be stacked to on Construct's Armoury equipment.")
        @Config.RangeInt(min = 1)
        @Config.RequiresMcRestart
        public int maxLevel = 5;

        @Config.Comment("The amount of energy to expand the energy buffer by per modifier level for Construct's Armoury equipment.")
        @Config.RangeInt(min = 0)
        public int energyBufferPerLevel = 80000;

        @Config.Comment("The amount of energy to be consumed per durability point absorbed on Construct's Armoury equipment.")
        @Config.RangeInt(min = 1)
        public int energyUse = 800;

        @Config.Comment("The chance to absorb tool damage at level 1 on Construct's Armoury equipment.")
        @Config.RangeDouble(min = 0, max = 1)
        public double baseProcChance = 1D;

        @Config.Comment("The added chance to absorb tool damage per additional level on Construct's Armoury equipment.")
        @Config.RangeDouble(min = 0, max = 1)
        public double additionalProcChance = 0D;

    }

}
