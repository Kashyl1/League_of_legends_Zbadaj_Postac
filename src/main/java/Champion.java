import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains instances representing our character and uses Jackson's annotations in the constructor
 */
public class Champion {
    private final String name;
    private int level;
    private final Statistics statistics;
    private final Skill firstAbility;
    private final Skill secondAbility;
    private final Skill thirdAbility;
    private final Skill fourthAbility;
    private final Skill passiveAbility;
    private final Scaling scaling;





    /**
     * Class constructor, which is called when deserializing an object from JSON,
     * additionally, each argument in the constructor is marked with @JsonProperty as it is used
     * in JSON to identify the value it corresponds to.
     */
    @JsonCreator
    public Champion(@JsonProperty("name") String name,
                    @JsonProperty("level") int level,
                    @JsonProperty("statistics") Statistics statistics,
                    @JsonProperty("firstAbility") Skill firstAbility,
                    @JsonProperty("secondAbility") Skill secondAbility,
                    @JsonProperty("thirdAbility") Skill thirdAbility,
                    @JsonProperty("fourthAbility") Skill fourthAbility,
                    @JsonProperty("passiveAbility") Skill passiveAbility,
                    @JsonProperty("scaling") Scaling scaling) {
        this.name = name;
        this.level = level;
        this.statistics = statistics;
        this.firstAbility = firstAbility;
        this.secondAbility = secondAbility;
        this.thirdAbility = thirdAbility;
        this.fourthAbility = fourthAbility;
        this.passiveAbility = passiveAbility;
        this.scaling = scaling;
    }



    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Statistics getStatystyki() {
        return statistics;
    }


    public Skill getUmiejetnoscPierwsza() {
        return firstAbility;
    }

    public Skill getUmiejetnoscDruga() {
        return secondAbility;
    }

    public Skill getUmiejetnoscTrzecia() {
        return thirdAbility;
    }

    public Skill getUmiejetnoscCzwarta() {
        return fourthAbility;
    }

    public Skill getUmiejetnoscPasywna() {
        return passiveAbility;
    }

    public Scaling getSkalowanie() {
        return scaling;
    }


}