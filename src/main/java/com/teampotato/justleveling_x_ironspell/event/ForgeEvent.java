package com.teampotato.justleveling_x_ironspell.event;

import com.teampotato.justleveling_x_ironspell.Jlis;
import com.teampotato.justleveling_x_ironspell.aptitudes.SkillsRegister;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Jlis.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEvent {
    @SubscribeEvent
    public static void killMob(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player && SkillsRegister.KILL_MANA_SKILL.get().isEnabled(player)){
            MobEffectInstance instance = new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 100, 1);
            player.addEffect(instance);
        }
    }
}
