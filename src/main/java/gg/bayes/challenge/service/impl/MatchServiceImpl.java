package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.Match;
import gg.bayes.challenge.repository.*;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchProcessor;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchProcessor matchProcessor;
    private final MatchRepository matchRepository;
    private final BuyEventRepository buyEventRepository;
    private final KillEventRepository killEventRepository;
    private final HitEventRepository hitEventRepository;
    private final SpellEventRepository spellEventRepository;

    public MatchServiceImpl(MatchRepository matchRepository, BuyEventRepository buyEventRepository,
                            KillEventRepository killEventRepository, HitEventRepository hitEventRepository,
                            SpellEventRepository spellEventRepository) {
        this.matchProcessor = new MatchProcessor();
        this.matchRepository = matchRepository;
        this.buyEventRepository = buyEventRepository;
        this.killEventRepository = killEventRepository;
        this.hitEventRepository = hitEventRepository;
        this.spellEventRepository = spellEventRepository;
    }

    @Override
    public Long ingestMatch(String payload) {
        Match match = new Match();
        List<Event> matchEvents = payload.lines().map(line -> matchProcessor.processMatchEvent(line, match))
                .filter(Objects::nonNull).collect(Collectors.toList());
        match.setEvents(matchEvents);
        matchRepository.save(match);
        return match.getId();
    }

    @Override
    public List<HeroItems> getItemsByMatchIdAndHeroName(Long matchId, String heroName) {
        return buyEventRepository.findByMatchIdAndHeroName(matchId, heroName);
    }

    @Override
    public List<HeroKills> getKillByMatchId(Long matchId) {
        return killEventRepository.findByMatchId(matchId);
    }

    @Override
    public List<HeroDamage> getDamagesByMatchIdAndHeroName(Long matchId, String heroName) {
        return hitEventRepository.findByMatchIdAndHeroName(matchId, heroName);
    }

    @Override
    public List<HeroSpells> getSpellsByMatchIdAndHeroName(Long matchId, String heroName) {
        return spellEventRepository.findByMatchIdAndHeroName(matchId, heroName);
    }
}
