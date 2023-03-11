import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Klasa ta zawiera instancje reprezentujące naszą postać oraz wykorzystuje adnotacje Jacksona w konstruktorze
 */
public class Postac {
    private final String imie;
    private int poziom;
    private final Statystyki statystyki;
    private final Umiejetnosc umiejetnoscPierwsza;
    private final Umiejetnosc umiejetnoscDruga;
    private final Umiejetnosc umiejetnoscTrzecia;
    private final Umiejetnosc umiejetnoscCzwarta;
    private final Umiejetnosc umiejetnoscPasywna;
    private final Skalowanie skalowanie;





    /**
     * Konstruktor klasy, który zostaje wywoływany podczas deserializacji obiektu z JSONA,
     * dodatkowo każdy argument w konstruktorze jest oznaczony jako @JsonProperty, ponieważ jest on używany
     * w JSONie do identyfikacji wartości której odpowiada.
     */
    @JsonCreator
    public Postac(@JsonProperty("imie") String imie,
                  @JsonProperty("poziom") int poziom,
                  @JsonProperty("statystyki") Statystyki statystyki,
                  @JsonProperty("umiejetnoscPierwsza") Umiejetnosc umiejetnoscPierwsza,
                  @JsonProperty("umiejetnoscDruga") Umiejetnosc umiejetnoscDruga,
                  @JsonProperty("umiejetnoscTrzecia") Umiejetnosc umiejetnoscTrzecia,
                  @JsonProperty("umiejetnoscCzwarta") Umiejetnosc umiejetnoscCzwarta,
                  @JsonProperty("umiejetnoscPasywna") Umiejetnosc umiejetnoscPasywna,
                  @JsonProperty("skalowanie") Skalowanie skalowanie) {
        this.imie = imie;
        this.poziom = poziom;
        this.statystyki = statystyki;
        this.umiejetnoscPierwsza = umiejetnoscPierwsza;
        this.umiejetnoscDruga = umiejetnoscDruga;
        this.umiejetnoscTrzecia = umiejetnoscTrzecia;
        this.umiejetnoscCzwarta = umiejetnoscCzwarta;
        this.umiejetnoscPasywna = umiejetnoscPasywna;
        this.skalowanie = skalowanie;
    }



    public String getImie() {
        return imie;
    }


    public int getPoziom() {
        return poziom;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public Statystyki getStatystyki() {
        return statystyki;
    }


    public Umiejetnosc getUmiejetnoscPierwsza() {
        return umiejetnoscPierwsza;
    }

    public Umiejetnosc getUmiejetnoscDruga() {
        return umiejetnoscDruga;
    }

    public Umiejetnosc getUmiejetnoscTrzecia() {
        return umiejetnoscTrzecia;
    }

    public Umiejetnosc getUmiejetnoscCzwarta() {
        return umiejetnoscCzwarta;
    }

    public Umiejetnosc getUmiejetnoscPasywna() {
        return umiejetnoscPasywna;
    }

    public Skalowanie getSkalowanie() {
        return skalowanie;
    }


}