package gg.bayes.challenge.parser.impl;

import gg.bayes.challenge.parser.MatchEventParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyEventParser implements MatchEventParser {

    private MatchEventParser nextChain;

    @Override
    public void setNextChain(MatchEventParser nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void processMatchEvent(String matchEvent) {
        if (matchEvent.contains("buy")) this.nextChain.processMatchEvent(matchEvent);

        log.debug("Processing BUY event");
    }
}
