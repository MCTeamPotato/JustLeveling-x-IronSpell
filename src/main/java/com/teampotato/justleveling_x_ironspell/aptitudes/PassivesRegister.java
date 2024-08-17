package com.teampotato.justleveling_x_ironspell.aptitudes;

import com.dplayend.justleveling.registry.RegistryAptitudes;
import com.dplayend.justleveling.registry.RegistryPassives;
import com.dplayend.justleveling.registry.passive.Passive;
import com.teampotato.justleveling_x_ironspell.Jlis;
import com.teampotato.justleveling_x_ironspell.config.Config;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PassivesRegister {
    public static final DeferredRegister<Passive> REGISTER = DeferredRegister.create(RegistryPassives.PASSIVES_KEY, Jlis.MODID);

    public static final RegistryObject<Passive> MANA_PASSIVE = REGISTER.register("mana_passive", () -> new Passive(
            new ResourceLocation(Jlis.MODID, "mana_passive"),
            RegistryAptitudes.MAGIC.get(),
            new ResourceLocation(Jlis.MODID, "textures/skills/magic/mana_passive.png"),
            AttributeRegistry.MAX_MANA.get(),
            "A361E604-9547-E8AB-E743-62273EF1DFCA",
            Config.MANA_PASSIVE,
            3, 6, 9, 12, 15, 18, 21, 24, 27, 30
    ));

    public static final RegistryObject<Passive> COOLDOWN_PASSIVE = REGISTER.register("cooldown_passive", () -> new Passive(
            new ResourceLocation(Jlis.MODID, "cooldown_passive"),
            RegistryAptitudes.MAGIC.get(),
            new ResourceLocation(Jlis.MODID, "textures/skills/magic/cooldown_passive.png"),
            AttributeRegistry.COOLDOWN_REDUCTION.get(),
            "C1CF34A5-DC32-B815-81C3-01AB00612506",
            Config.COOLDOWN_PASSIVE,
            3, 6, 9, 12, 15, 18, 21, 24, 27, 30
    ));

    public static final RegistryObject<Passive> CAST_PASSIVE = REGISTER.register("cast_passive", () -> new Passive(
            new ResourceLocation(Jlis.MODID, "cast_passive"),
            RegistryAptitudes.MAGIC.get(),
            new ResourceLocation(Jlis.MODID, "textures/skills/magic/cast_passive.png"),
            AttributeRegistry.MAX_MANA.get(),
            "A713DF17-941C-EBBD-C7FD-338882B87398",
            Config.CAST_PASSIVE,
            3, 5, 7, 8, 9, 11, 12, 13, 15, 17, 18, 19, 21, 23, 24, 25, 26, 27, 29, 30
    ));

    public static void load(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
