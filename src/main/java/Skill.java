
/**
 * Klasa ta jest odpowiedzialna za umiejętności która nasza postać posiada,zawiera ona prywatne instancje oraz gettery i settery
 */
public class Skill {
    private String name;
    private String description;
    private double abilityPowerScaling;
    private Base_Damage baseDamage;
    private double abilityPowerScalingWithAniviaBoost;
    private double abilityPowerScalingWithoutAniviaBoost;
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAbilityPowerScaling() {
        return abilityPowerScaling;
    }

    public Base_Damage getBaseDamage() {
        return baseDamage;
    }

    public double getAbilityPowerScalingWithAniviaBoost() {
        return abilityPowerScalingWithAniviaBoost;
    }

    public double getAbilityPowerScalingWithoutAniviaBoost() {
        return abilityPowerScalingWithoutAniviaBoost;
    }

}
