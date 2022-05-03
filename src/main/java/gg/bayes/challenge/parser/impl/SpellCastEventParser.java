package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.Match;
import gg.bayes.challenge.entity.SpellEvent;
import gg.bayes.challenge.parser.MatchEventParser;
import gg.bayes.challenge.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SpellCastEventParser implements MatchEventParser {

    private static final Pattern pattern = Pattern.compile("\\[(.+)\\] npc_dota_hero_(.*) casts ability (.*) \\(lvl (\\d+)\\) on (.*)");

    private MatchEventParser nextChain;

    @Override
    public void setNextChain(MatchEventParser nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public Event processMatchEvent(String matchEvent, Match match) {
        Matcher matcher = pattern.matcher(matchEvent);
        if (!matcher.find()) return this.nextChain.processMatchEvent(matchEvent, match);

        log.debug("Processing SPELL CAST event");

        return SpellEvent.builder()
                .hero(matcher.group(2))
                .spell(matcher.group(3))
                .level(Integer.parseInt(matcher.group(4)))
                .match(match)
                .timestamp(TimeUtil.convertTimestampStringToLong(matcher.group(1)))
                .build();
    }
}
