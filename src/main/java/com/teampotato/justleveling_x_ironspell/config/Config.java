package com.teampotato.justleveling_x_ironspell.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec configSpec;
    public static final ForgeConfigSpec.IntValue COOLDOWN_PASSIVE, MANA_PASSIVE, CAST_PASSIVE;

    static {
        builder.push("Justleveling x Ironspell");
        MANA_PASSIVE = builder.defineInRange("mana passive", 300, 0, Integer.MAX_VALUE);
        COOLDOWN_PASSIVE = builder.defineInRange("cooldown passive", 10, 0, Integer.MAX_VALUE);
        CAST_PASSIVE = builder.defineInRange("cast passive", 200, 0, Integer.MAX_VALUE);
        configSpec = builder.build();
    }
}
