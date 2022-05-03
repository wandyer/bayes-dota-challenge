package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.KillEvent;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KillEventRepository extends CrudRepository<KillEvent, Long> {

    @Query("SELECT new gg.bayes.challenge.rest.model.HeroKills(a.hero, count(a)) " +
            "FROM KillEvent a WHERE a.match.id=:matchId GROUP BY a.hero")
    List<HeroKills> findByMatchId(Long matchId);
}
