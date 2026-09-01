package com.teampotato.justleveling_x_ironspell.regression;

import com.mojang.authlib.GameProfile;
import com.teampotato.justleveling_x_ironspell.aptitudes.PassivesRegister;
import com.teampotato.justleveling_x_ironspell.event.ForgeEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import java.util.UUID;

@Mod("justleveling_x_ironspell_tests")
@GameTestHolder("justleveling_x_ironspell")
@PrefixGameTestTemplate(false)
public class RegressionTests {
    @GameTest(template = "empty")
    public static void echoChantChangesCastSpeedAttribute(GameTestHelper helper) {
        helper.assertTrue(PassivesRegister.CAST_PASSIVE.get().attribute == AttributeRegistry.CAST_TIME_REDUCTION.get(),
                "Echo Chant must bind to cast time reduction");
        helper.assertTrue(PassivesRegister.MANA_PASSIVE.get().attribute == AttributeRegistry.MAX_MANA.get(),
                "Mana passive must retain the maximum mana attribute");
        helper.assertTrue(PassivesRegister.COOLDOWN_PASSIVE.get().attribute == AttributeRegistry.COOLDOWN_REDUCTION.get(),
                "Cooldown passive must retain the cooldown reduction attribute");
        helper.succeed();
    }

    @GameTest(template = "empty")
    public static void loginRemovesOnlyLegacyCastManaBonus(GameTestHelper helper) {
        FakePlayer oldPlayer = new FakePlayer(helper.getLevel(), new GameProfile(UUID.randomUUID(), "Legacy"));
        UUID castId = UUID.fromString(PassivesRegister.CAST_PASSIVE.get().attributeUuid);
        UUID manaId = UUID.fromString(PassivesRegister.MANA_PASSIVE.get().attributeUuid);
        UUID otherId = UUID.randomUUID();
        var oldMana = oldPlayer.getAttribute(AttributeRegistry.MAX_MANA.get());
        oldMana.addPermanentModifier(new AttributeModifier(castId, "justlevelingfork", 200, AttributeModifier.Operation.ADDITION));
        oldMana.addPermanentModifier(new AttributeModifier(manaId, "justlevelingfork", 300, AttributeModifier.Operation.ADDITION));
        oldMana.addPermanentModifier(new AttributeModifier(otherId, "other_mod", 50, AttributeModifier.Operation.ADDITION));

        FakePlayer player = new FakePlayer(helper.getLevel(), new GameProfile(UUID.randomUUID(), "Reloaded"));
        player.getAttributes().load(oldPlayer.getAttributes().save());
        var mana = player.getAttribute(AttributeRegistry.MAX_MANA.get());
        helper.assertTrue(mana.getModifier(castId) != null, "Fixture must reproduce the saved legacy modifier");
        // FakePlayer has no network channel for unrelated mods' login synchronization.
        var events = BusBuilder.builder().build();
        events.register(ForgeEvent.class);
        events.post(new PlayerEvent.PlayerLoggedInEvent(player));
        helper.assertTrue(mana.getModifier(castId) == null, "Login must remove the old cast passive's mana bonus");
        helper.assertTrue(mana.getModifier(manaId) != null && mana.getModifier(manaId).getAmount() == 300,
                "The legitimate mana passive must remain intact");
        helper.assertTrue(mana.getModifier(otherId) != null && mana.getModifier(otherId).getAmount() == 50,
                "Other mods' mana modifiers must remain intact");
        events.post(new PlayerEvent.PlayerLoggedInEvent(player));
        helper.assertTrue(mana.getModifier(castId) == null && mana.getModifier(manaId) != null && mana.getModifier(otherId) != null,
                "Legacy migration must be safe to repeat");
        helper.succeed();
    }
}
