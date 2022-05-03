package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.BuyEvent;
import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyEventRepository extends CrudRepository<BuyEvent, Long> {

    @Query("SELECT new gg.bayes.challenge.rest.model.HeroItems(a.item, a.timestamp) " +
            "FROM BuyEvent a WHERE a.match.id=:matchId AND a.hero=:heroName")
    List<HeroItems> findByMatchIdAndHeroName(Long matchId, String heroName);
}
