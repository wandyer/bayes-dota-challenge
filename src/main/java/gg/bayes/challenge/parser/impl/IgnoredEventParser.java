package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.parser.MatchEventParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IgnoredEventParser implements MatchEventParser {

    @Override
    public void setNextChain(MatchEventParser nextChain) {
    }

    @Override
    public void processMatchEvent(String matchEvent) {
        log.debug("Processing IGNORED event");
    }
}
