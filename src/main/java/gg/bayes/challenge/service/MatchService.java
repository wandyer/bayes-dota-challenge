package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

import java.util.List;

public interface MatchService {
    Long ingestMatch(String payload);

    List<HeroItems> getItemsByMatchIdAndHeroName(Long matchId, String heroName);

    List<HeroKills> getKillByMatchId(Long matchId);

    List<HeroDamage> getDamagesByMatchIdAndHeroName(Long matchId, String heroName);

    List<HeroSpells> getSpellsByMatchIdAndHeroName(Long matchId, String heroName);
}
