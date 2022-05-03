package gg.bayes.challenge.service;

import gg.bayes.challenge.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchProcessorTest {

    private final MatchProcessor matchProcessor = new MatchProcessor();

    @Test
    void processBuyEvent() {
        String input = "[00:08:50.891] npc_dota_hero_rubick buys item item_sobi_mask";
        Event event = matchProcessor.processMatchEvent(input, new Match());
        assertTrue(event instanceof BuyEvent);
        assertEquals("rubick", ((BuyEvent) event).getHero());
        assertEquals("sobi_mask", ((BuyEvent) event).getItem());
    }

    @Test
    void processHitEvent() {
        String input = "[00:26:55.360] npc_dota_hero_bloodseeker hits npc_dota_hero_abyssal_underlord with bloodseeker_rupture for 17 damage (922->905)";
        Event event = matchProcessor.processMatchEvent(input, new Match());
        assertTrue(event instanceof HitEvent);
        assertEquals("bloodseeker", ((HitEvent) event).getHero());
        assertEquals("abyssal_underlord", ((HitEvent) event).getTarget());
        assertEquals(17, ((HitEvent) event).getDamage());
    }

    @Test
    void processKillEvent() {
        String input = "[00:27:00.126] npc_dota_hero_abyssal_underlord is killed by npc_dota_hero_bloodseeker";
        Event event = matchProcessor.processMatchEvent(input, new Match());
        assertTrue(event instanceof KillEvent);
        assertEquals("bloodseeker", ((KillEvent) event).getHero());
        assertEquals("abyssal_underlord", ((KillEvent) event).getTarget());
    }

    @Test
    void processSpellEvent() {
        String input = "[00:27:02.792] npc_dota_hero_dragon_knight casts ability dragon_knight_dragon_tail (lvl 2) on npc_dota_hero_mars";
        Event event = matchProcessor.processMatchEvent(input, new Match());
        assertTrue(event instanceof SpellEvent);
        assertEquals("dragon_knight", ((SpellEvent) event).getHero());
        assertEquals("dragon_knight_dragon_tail", ((SpellEvent) event).getSpell());
        assertEquals(2, ((SpellEvent) event).getLevel());
    }

    @Test
    void processUnknownEvent() {
        String input = "[00:27:04.058] npc_dota_hero_pangolier uses item_magic_wand";
        Event event = matchProcessor.processMatchEvent(input, new Match());
        assertNull(event);
    }

}