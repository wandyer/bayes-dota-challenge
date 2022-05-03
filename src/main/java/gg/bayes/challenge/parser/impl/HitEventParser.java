package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.HitEvent;
import gg.bayes.challenge.entity.Match;
import gg.bayes.challenge.parser.MatchEventParser;
import gg.bayes.challenge.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HitEventParser implements MatchEventParser {

    private static final Pattern pattern = Pattern.compile("\\[(.+)\\] npc_dota_hero_(.*) hits npc_dota_hero_(.*) with (.*) for (\\d+) damage");

    private MatchEventParser nextChain;

    @Override
    public void setNextChain(MatchEventParser nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public Event processMatchEvent(String matchEvent, Match match) {
        Matcher matcher = pattern.matcher(matchEvent);
        if (!matcher.find()) return this.nextChain.processMatchEvent(matchEvent, match);

        log.debug("Processing HIT event");

        return HitEvent.builder()
                .hero(matcher.group(2))
                .target(matcher.group(3))
                .damage(Integer.parseInt(matcher.group(5)))
                .match(match)
                .timestamp(TimeUtil.convertTimestampStringToLong(matcher.group(1)))
                .build();
    }
}
