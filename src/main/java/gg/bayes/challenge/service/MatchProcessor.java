package gg.bayes.challenge.service;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.Match;
import gg.bayes.challenge.parser.impl.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatchProcessor {

    private final BuyEventParser buyEventParser = new BuyEventParser();

    public MatchProcessor() {
        final KillEventParser killEventParser = new KillEventParser();
        final HitEventParser hitEventParser = new HitEventParser();
        final SpellCastEventParser spellCastEventParser = new SpellCastEventParser();
        final IgnoredEventParser ignoredEventParser = new IgnoredEventParser();

        this.buyEventParser.setNextChain(killEventParser);
        killEventParser.setNextChain(hitEventParser);
        hitEventParser.setNextChain(spellCastEventParser);
        spellCastEventParser.setNextChain(ignoredEventParser);
    }

    public List<Event> processLogs(String matchLogs, Match match) {
        return matchLogs.lines().map(line -> buyEventParser.processMatchEvent(line, match))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
