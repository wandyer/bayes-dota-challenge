package gg.bayes.challenge.repository;

import gg.bayes.challenge.entity.SpellEvent;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpellEventRepository extends CrudRepository<SpellEvent, Long> {

    @Query("SELECT new gg.bayes.challenge.rest.model.HeroSpells(a.spell, COUNT(a)) " +
            "FROM SpellEvent a WHERE a.match.id=:matchId AND a.hero=:heroName GROUP BY a.spell")
    List<HeroSpells> findByMatchIdAndHeroName(Long matchId, String heroName);
}
