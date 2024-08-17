package com.teampotato.justleveling_x_ironspell.event;

import com.teampotato.justleveling_x_ironspell.Jlis;
import com.teampotato.justleveling_x_ironspell.aptitudes.SkillsRegister;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Jlis.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEvent {
    @SubscribeEvent
    public static void killMob(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer && SkillsRegister.KILL_MANA_SKILL.get().isEnabled(serverPlayer)) {
            MagicData magicData = MagicData.getPlayerMagicData(serverPlayer);
            double mana = magicData.getMana();
            double maxMana = serverPlayer.getAttributeValue(AttributeRegistry.MAX_MANA.get());
            double manaPercentage = mana / maxMana;
            if (manaPercentage >= 0.6) return;

            CompoundTag compoundTag = serverPlayer.getPersistentData();
            long gameTime = serverPlayer.level().getGameTime();

            if(compoundTag.get("kill_mana") != null &&  gameTime - compoundTag.getLong("kill_mana") < 200) return;
            compoundTag.putLong("kill_mana", gameTime);

            int level = manaPercentage < 0.2 ? 3 : manaPercentage < 0.4 ? 2 : 1;

            MobEffectInstance instance = new MobEffectInstance(MobEffectRegistry.INSTANT_MANA.get(), 1, level);
            serverPlayer.addEffect(instance);
        }
    }
}
