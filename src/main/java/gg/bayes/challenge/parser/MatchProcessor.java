package gg.bayes.challenge.parser;

import gg.bayes.challenge.parser.impl.*;

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

    public void processMatchEvent(String matchEventLog) {
        buyEventParser.processMatchEvent(matchEventLog);
    }
}
