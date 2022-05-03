package gg.bayes.challenge.parser;

public interface MatchEventParser {

    void setNextChain(MatchEventParser nextChain);

    void processMatchEvent(String matchEvent);
}
