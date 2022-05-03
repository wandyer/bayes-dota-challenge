package gg.bayes.challenge.service;

import gg.bayes.challenge.entity.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchProcessorTest {

    private final MatchProcessor matchProcessor = new MatchProcessor();

    @Test
    void processBuyEvent() {
        String input = "[00:08:50.891] npc_dota_hero_rubick buys item item_sobi_mask";
        List<Event> events = matchProcessor.processLogs(input, new Match());
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof BuyEvent);
        assertEquals("rubick", ((BuyEvent) events.get(0)).getHero());
        assertEquals("sobi_mask", ((BuyEvent) events.get(0)).getItem());
    }

    @Test
    void processHitEvent() {
        String input = "[00:26:55.360] npc_dota_hero_bloodseeker hits npc_dota_hero_abyssal_underlord with bloodseeker_rupture for 17 damage (922->905)";
        List<Event> events = matchProcessor.processLogs(input, new Match());
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof HitEvent);
        assertEquals("bloodseeker", ((HitEvent) events.get(0)).getHero());
        assertEquals("abyssal_underlord", ((HitEvent) events.get(0)).getTarget());
        assertEquals(17, ((HitEvent) events.get(0)).getDamage());
    }

    @Test
    void processKillEvent() {
        String input = "[00:27:00.126] npc_dota_hero_abyssal_underlord is killed by npc_dota_hero_bloodseeker";
        List<Event> events = matchProcessor.processLogs(input, new Match());
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof KillEvent);
        assertEquals("bloodseeker", ((KillEvent) events.get(0)).getHero());
        assertEquals("abyssal_underlord", ((KillEvent) events.get(0)).getTarget());
    }

    @Test
    void processSpellEvent() {
        String input = "[00:27:02.792] npc_dota_hero_dragon_knight casts ability dragon_knight_dragon_tail (lvl 2) on npc_dota_hero_mars";
        List<Event> events = matchProcessor.processLogs(input, new Match());
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof SpellEvent);
        assertEquals("dragon_knight", ((SpellEvent) events.get(0)).getHero());
        assertEquals("dragon_knight_dragon_tail", ((SpellEvent) events.get(0)).getSpell());
        assertEquals(2, ((SpellEvent) events.get(0)).getLevel());
    }

    @Test
    void processUnknownEvent() {
        String input = "[00:27:04.058] npc_dota_hero_pangolier uses item_magic_wand";
        List<Event> events = matchProcessor.processLogs(input, new Match());
        assertEquals(0, events.size());
    }

}