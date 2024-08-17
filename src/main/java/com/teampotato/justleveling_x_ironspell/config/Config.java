package com.teampotato.justleveling_x_ironspell.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec configSpec;
    public static final ForgeConfigSpec.IntValue attackDamageValue;

    static {
        builder.push("Justleveling x Ironspell");
        attackDamageValue = builder.defineInRange("attackDamageValue", 10000, 0, Integer.MAX_VALUE);
        configSpec = builder.build();
    }
}
