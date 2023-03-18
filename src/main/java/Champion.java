import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Klasa ta zawiera instancje reprezentujące naszą postać oraz wykorzystuje adnotacje Jacksona w konstruktorze
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
     * Konstruktor klasy, który zostaje wywoływany podczas deserializacji obiektu z JSONA,
     * dodatkowo każdy argument w konstruktorze jest oznaczony jako @JsonProperty, ponieważ jest on używany
     * w JSONie do identyfikacji wartości której odpowiada.
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