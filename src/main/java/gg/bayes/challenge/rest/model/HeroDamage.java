package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeroDamage {
    private String target;
    @JsonProperty("damage_instances")
    private Integer damageInstances;
    @JsonProperty("total_damage")
    private Integer totalDamage;

    public HeroDamage(String target, Long damageInstances, Long totalDamage) {
        this.target = target;
        this.damageInstances = damageInstances.intValue();
        this.totalDamage = totalDamage.intValue();
    }
}
