import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

/**
 * Jest to klasa zdefiniowana jako record, służy ona do przechowywania danych
 * Zawiera ona wiele pól reprezentujących atrybuty przedmiotów.
 * @param nazwa
 * @param typ
 * @param pasywka
 * @param mocUmiejetnosci
 * @param skalowanieMocyUmiejetnosci
 * @param hp
 * @param przyspieszenieUmiejetnosci
 * @param pktPrzebiciaOdpornosciNaMagie
 * @param predkoscRuchu
 * @param pancerz
 * @param predkoscAtakuBonus
 * @param mana
 * @param obrazeniaOdAtaku
 * @param szansaNaTrafienieKrytyczne
 * @param odpornoscNaMagie
 */
public record Przedmioty(String nazwa, String typ, String pasywka, int mocUmiejetnosci,
                         double skalowanieMocyUmiejetnosci, int hp, double przyspieszenieUmiejetnosci,
                         int pktPrzebiciaOdpornosciNaMagie, int predkoscRuchu, int pancerz,
                         double predkoscAtakuBonus, int mana, int obrazeniaOdAtaku,
                         double szansaNaTrafienieKrytyczne, int odpornoscNaMagie) {

    /**
     * Konstruktor klasy, który zostaje wywoływany podczas deserializacji obiektu z JSONA,
     * dodatkowo każdy argument w konstruktorze jest oznaczony jako @JsonProperty, ponieważ jest on używany
     * w JSONie do identyfikacji wartości której odpowiada.
     */
    @JsonCreator
    public Przedmioty(@JsonProperty("nazwa") String nazwa,
                      @JsonProperty("typ") String typ,
                      @JsonProperty("pasywka") String pasywka,
                      @JsonProperty("mocUmiejetnosci") int mocUmiejetnosci,
                      @JsonProperty("skalowanieMocyUmiejetnosci") double skalowanieMocyUmiejetnosci,
                      @JsonProperty("hp") int hp,
                      @JsonProperty("przyspieszenieUmiejetnosci") double przyspieszenieUmiejetnosci,
                      @JsonProperty("pktPrzebiciaOdpornosciNaMagie") int pktPrzebiciaOdpornosciNaMagie,
                      @JsonProperty("predkoscRuchu") int predkoscRuchu,
                      @JsonProperty("pancerz") int pancerz,
                      @JsonProperty("predkoscAtakuBonus") double predkoscAtakuBonus,
                      @JsonProperty("mana") int mana,
                      @JsonProperty("obrazeniaOdAtaku") int obrazeniaOdAtaku,
                      @JsonProperty("szansaNaTrafienieKrytyczne") double szansaNaTrafienieKrytyczne,
                      @JsonProperty("odpornoscNaMagie") int odpornoscNaMagie) {
        this.nazwa = nazwa;
        this.typ = typ;
        this.pasywka = pasywka;
        this.mocUmiejetnosci = mocUmiejetnosci;
        this.skalowanieMocyUmiejetnosci = skalowanieMocyUmiejetnosci;
        this.hp = hp;
        this.przyspieszenieUmiejetnosci = przyspieszenieUmiejetnosci;
        this.pktPrzebiciaOdpornosciNaMagie = pktPrzebiciaOdpornosciNaMagie;
        this.predkoscRuchu = predkoscRuchu;
        this.pancerz = pancerz;
        this.predkoscAtakuBonus = predkoscAtakuBonus;
        this.mana = mana;
        this.obrazeniaOdAtaku = obrazeniaOdAtaku;
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
        this.odpornoscNaMagie = odpornoscNaMagie;
    }

    /**
     * Użycie refleksji, aby pobrać pola klasy i sprawdzić ich wartości, dodatkowo wykorzystuje klasę StringBuilder, aby
     * wypełnić napis z wartościami pól, które są wieksze od zera. W skrócie, metoda dzięki której po wyświetleniu informacji
     * o przedmiocie nie zostaniemy zaatakowani przez kilka pól atrybutów o wartości "0", których ten przedmiot nie ma
     *
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa: ").append(nazwa).append("\n");
        sb.append("Typ: ").append(typ).append("\n");
        sb.append("Pasywka: ").append(pasywka).append("\n");
        for (Field field : getClass().getDeclaredFields()) {
            try {
                Object value = field.get(this);
                if (value instanceof Number && ((Number) value).doubleValue() > 0) {
                    sb.append(field.getName()).append(": ").append(value).append("\n");
                }
            } catch (IllegalAccessException e) {
                // Ignore exception
            }
        }
        return sb.toString();
    }
}
