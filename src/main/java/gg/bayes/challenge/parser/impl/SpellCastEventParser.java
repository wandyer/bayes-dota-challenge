package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.parser.MatchEventParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpellCastEventParser implements MatchEventParser {

    private MatchEventParser nextChain;

    @Override
    public void setNextChain(MatchEventParser nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void processMatchEvent(String matchEvent) {
        if (matchEvent.contains("casts")) this.nextChain.processMatchEvent(matchEvent);

        log.debug("Processing SPELL CAST event");
    }
}