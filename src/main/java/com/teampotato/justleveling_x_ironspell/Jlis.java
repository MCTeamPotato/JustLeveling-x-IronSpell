package com.teampotato.justleveling_x_ironspell;

import com.teampotato.justleveling_x_ironspell.aptitudes.PassivesRegister;
import com.teampotato.justleveling_x_ironspell.aptitudes.SkillsRegister;
import com.teampotato.justleveling_x_ironspell.config.Config;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("justleveling_x_ironspell")
public class Jlis {
    public static final String MODID = "justleveling_x_ironspell";
    private static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public Jlis() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.configSpec);
        PassivesRegister.load(bus);
        SkillsRegister.load(bus);
    }
}
