package gg.bayes.challenge.parser;

import gg.bayes.challenge.entity.Event;
import gg.bayes.challenge.entity.Match;

public interface MatchEventParser {

    void setNextChain(MatchEventParser nextChain);

    Event processMatchEvent(String matchEvent, Match match);
}
