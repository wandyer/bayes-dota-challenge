package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.parser.MatchProcessor;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchProcessor matchProcessor;

    public MatchServiceImpl() {
        this.matchProcessor = new MatchProcessor();
    }

    @Override
    public Long ingestMatch(String payload) {
        payload.lines().forEach(matchProcessor::processMatchEvent);
        return 1L;
    }
}
