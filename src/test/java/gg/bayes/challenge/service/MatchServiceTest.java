package gg.bayes.challenge.service;

import gg.bayes.challenge.repository.*;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.impl.MatchServiceImpl;
import gg.bayes.challenge.util.ResourceReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @InjectMocks
    private MatchServiceImpl matchService;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private BuyEventRepository buyEventRepository;
    @Mock
    private KillEventRepository killEventRepository;
    @Mock
    private HitEventRepository hitEventRepository;
    @Mock
    private SpellEventRepository spellEventRepository;

    @Test
    void ingestMatch() throws IOException {
        String input = ResourceReader.readFileToString("classpath:combatlog_1.txt");
        Mockito.when(matchRepository.save(Mockito.any())).thenReturn(Mockito.any());
        matchService.ingestMatch(input);
        Mockito.verify(matchRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void getItems() {
        HeroItems heroItems1 = new HeroItems("Item 1", Long.parseLong("12345"));
        HeroItems heroItems2 = new HeroItems("Item 2", Long.parseLong("56789"));
        List<HeroItems> heroItemsListReturn = List.of(heroItems1, heroItems2);
        Mockito.when(buyEventRepository.findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(heroItemsListReturn);

        List<HeroItems> heroItemsList = matchService
                .getItemsByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        Mockito.verify(buyEventRepository).findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        assertNotNull(heroItemsList);
    }

    @Test
    void getKills() {
        HeroKills heroKills1 = new HeroKills("Hero 1", Long.parseLong("5"));
        HeroKills heroKills2 = new HeroKills("Hero 2", Long.parseLong("7"));
        List<HeroKills> heroKillsListReturn = List.of(heroKills1, heroKills2);
        Mockito.when(killEventRepository.findByMatchId(Mockito.anyLong()))
                .thenReturn(heroKillsListReturn);

        List<HeroKills> heroKillsList = matchService.getKillByMatchId(Mockito.anyLong());
        Mockito.verify(killEventRepository).findByMatchId(Mockito.anyLong());
        assertNotNull(heroKillsList);
    }

    @Test
    void getDamage() {
        HeroDamage heroDamage1 = new HeroDamage("Hero Target 1",
                Long.parseLong("12"), Long.parseLong("5000"));
        HeroDamage heroDamage2 = new HeroDamage("Hero Target 2",
                Long.parseLong("7"), Long.parseLong("1200"));
        List<HeroDamage> heroDamageListReturn = List.of(heroDamage1, heroDamage2);
        Mockito.when(hitEventRepository.findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(heroDamageListReturn);

        List<HeroDamage> heroDamageList = matchService
                .getDamagesByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        Mockito.verify(hitEventRepository).findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        assertNotNull(heroDamageList);
    }

    @Test
    void getSpells() {
        HeroSpells heroSpells1 = new HeroSpells("Spell 1", Long.parseLong("31"));
        HeroSpells heroSpells2 = new HeroSpells("Spell 2", Long.parseLong("15"));
        List<HeroSpells> heroSpellsListReturn = List.of(heroSpells1, heroSpells2);
        Mockito.when(spellEventRepository.findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(heroSpellsListReturn);

        List<HeroSpells> heroSpellsList = matchService
                .getSpellsByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        Mockito.verify(spellEventRepository).findByMatchIdAndHeroName(Mockito.anyLong(), Mockito.anyString());
        assertNotNull(heroSpellsList);
    }
}