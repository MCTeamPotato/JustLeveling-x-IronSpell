package com.teampotato.justleveling_x_ironspell.aptitudes;

import com.dplayend.justleveling.registry.RegistryAptitudes;
import com.dplayend.justleveling.registry.RegistrySkills;
import com.dplayend.justleveling.registry.skills.Skill;
import com.teampotato.justleveling_x_ironspell.Jlis;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SkillsRegister {
    public static final DeferredRegister<Skill> REGISTER = DeferredRegister.create(RegistrySkills.SKILLS_KEY, Jlis.MODID);
    public static final RegistryObject<Skill> KILL_MANA_SKILL = REGISTER.register("kill_mana_skill", () -> new Skill(
            new ResourceLocation(Jlis.MODID, "kill_mana_skill"),
            RegistryAptitudes.MAGIC.get(),
            32,
            new ResourceLocation(Jlis.MODID, "textures/skills/magic/kill_mana_skill.png")
    ));

    public static void load(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
