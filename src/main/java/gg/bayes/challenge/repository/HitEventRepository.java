package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.HitEvent;
import gg.bayes.challenge.rest.model.HeroDamage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HitEventRepository extends CrudRepository<HitEvent, Long> {

    @Query("SELECT new gg.bayes.challenge.rest.model.HeroDamage(a.target, COUNT(a), SUM(a.damage)) " +
            "FROM HitEvent a WHERE a.match.id=:matchId AND a.hero=:heroName GROUP BY a.target")
    List<HeroDamage> findByMatchIdAndHeroName(Long matchId, String heroName);
}
