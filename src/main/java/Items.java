import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public record Items(String name, String type, String passive, int abilityPower,
                    double abilityPowerScaling, int hp, double cooldownReduction,
                    int magicResistancePenetration, int movementSpeed, int armor,
                    double attackSpeed, int mana, int physicalDamage,
                    double critChance, int magicResistance) {

    @JsonCreator
    public Items(@JsonProperty("name") String name,
                 @JsonProperty("type") String type,
                 @JsonProperty("passive") String passive,
                 @JsonProperty("abilityPower") int abilityPower,
                 @JsonProperty("abilityPowerScaling") double abilityPowerScaling,
                 @JsonProperty("hp") int hp,
                 @JsonProperty("cooldownReduction") double cooldownReduction,
                 @JsonProperty("magicResistancePenetration") int magicResistancePenetration,
                 @JsonProperty("movementSpeed") int movementSpeed,
                 @JsonProperty("armor") int armor,
                 @JsonProperty("attackSpeed") double attackSpeed,
                 @JsonProperty("mana") int mana,
                 @JsonProperty("physicalDamage") int physicalDamage,
                 @JsonProperty("critChance") double critChance,
                 @JsonProperty("magicResistance") int magicResistance) {
        this.name = name;
        this.type = type;
        this.passive = passive;
        this.abilityPower = abilityPower;
        this.abilityPowerScaling = abilityPowerScaling;
        this.hp = hp;
        this.cooldownReduction = cooldownReduction;
        this.magicResistancePenetration = magicResistancePenetration;
        this.movementSpeed = movementSpeed;
        this.armor = armor;
        this.attackSpeed = attackSpeed;
        this.mana = mana;
        this.physicalDamage = physicalDamage;
        this.critChance = critChance;
        this.magicResistance = magicResistance;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Passive: ").append(passive).append("\n");
        for (Field field : getClass().getDeclaredFields()) {
            try {
                Object value = field.get(this);
                if (value instanceof Number && ((Number) value).doubleValue() > 0) {
                    sb.append(field.getName()).append(": ").append(value).append("\n");
                }
            } catch (IllegalAccessException e) {
            }
        }
        return sb.toString();
    }
}
