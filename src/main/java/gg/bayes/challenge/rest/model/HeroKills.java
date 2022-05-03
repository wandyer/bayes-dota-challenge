package gg.bayes.challenge.rest.model;

import lombok.Data;

@Data
public class HeroKills {
    private String hero;
    private Integer kills;

    public HeroKills(String hero, Long kills) {
        this.hero = hero;
        this.kills = kills.intValue();
    }
}
