
/**
 * Klasa ta jest odpowiedzialna za umiejętności która nasza postać posiada,zawiera ona prywatne instancje oraz gettery i settery
 */
public class Umiejetnosc {
    private String nazwa;
    private String opis;
    private double skalowanieMocyUmiejetnosci;
    private BazoweObrazenia bazoweObrazenia;
    private double skalowanieMocyUmiejetnosciBezOblodzenia;
    private BazoweObrazenia bazoweObrazeniaBezUmiejetnosciPierwszej;
    private double skalowanieMocyUmiejetnosciZOblodzeniem;
    private BazoweObrazenia bazoweObrazeniaZUmiejetnosciaPierwsza;

    public Umiejetnosc(String nazwa) {
        this.nazwa = nazwa;
    }

    public Umiejetnosc(Umiejetnosc umiejetnoscPierwsza, Umiejetnosc umiejetnoscDruga, Umiejetnosc umiejetnoscTrzecia, Umiejetnosc umiejetnoscCzwarta, Umiejetnosc umiejetnoscPasywna) {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getSkalowanieMocyUmiejetnosci() {
        return skalowanieMocyUmiejetnosci;
    }

    public void setSkalowanieMocyUmiejetnosci(double skalowanieMocyUmiejetnosci) {
        this.skalowanieMocyUmiejetnosci = skalowanieMocyUmiejetnosci;
    }

    public BazoweObrazenia getBazoweObrazenia() {
        return bazoweObrazenia;
    }

    public void setBazoweObrazenia(BazoweObrazenia bazoweObrazenia) {
        this.bazoweObrazenia = bazoweObrazenia;
    }

    public double getSkalowanieMocyUmiejetnosciBezOblodzenia() {
        return skalowanieMocyUmiejetnosciBezOblodzenia;
    }

    public void setSkalowanieMocyUmiejetnosciBezOblodzenia(double skalowanieMocyUmiejetnosciBezOblodzenia) {
        this.skalowanieMocyUmiejetnosciBezOblodzenia = skalowanieMocyUmiejetnosciBezOblodzenia;
    }

    public BazoweObrazenia getBazoweObrazeniaBezUmiejetnosciPierwszej() {
        return bazoweObrazeniaBezUmiejetnosciPierwszej;
    }

    public void setBazoweObrazeniaBezUmiejetnosciPierwszej(BazoweObrazenia bazoweObrazeniaBezUmiejetnosciPierwszej) {
        this.bazoweObrazeniaBezUmiejetnosciPierwszej = bazoweObrazeniaBezUmiejetnosciPierwszej;
    }

    public double getSkalowanieMocyUmiejetnosciZOblodzeniem() {
        return skalowanieMocyUmiejetnosciZOblodzeniem;
    }

    public void setSkalowanieMocyUmiejetnosciZOblodzeniem(double skalowanieMocyUmiejetnosciZOblodzeniem) {
        this.skalowanieMocyUmiejetnosciZOblodzeniem = skalowanieMocyUmiejetnosciZOblodzeniem;
    }

    public BazoweObrazenia getBazoweObrazeniaZUmiejetnosciaPierwsza() {
        return bazoweObrazeniaZUmiejetnosciaPierwsza;
    }

    public void setBazoweObrazeniaZUmiejetnosciaPierwsza(BazoweObrazenia bazoweObrazeniaZUmiejetnosciaPierwsza) {
        this.bazoweObrazeniaZUmiejetnosciaPierwsza = bazoweObrazeniaZUmiejetnosciaPierwsza;
    }
}
