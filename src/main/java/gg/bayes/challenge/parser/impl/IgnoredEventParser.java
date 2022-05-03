package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.Match;
import gg.bayes.challenge.parser.MatchEventParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IgnoredEventParser implements MatchEventParser {

    @Override
    public void setNextChain(MatchEventParser nextChain) {
    }

    @Override
    public Event processMatchEvent(String matchEvent, Match match) {
        log.debug("Processing IGNORED event");
        return null;
    }
}
