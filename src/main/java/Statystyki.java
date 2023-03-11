

/**
 * Klasa odpowiedzialna za statystyki naszej postaci, zawiera ona prywatne pola, reprezentujące postać oraz gettery
 * i settery odpowiedzialne za pobieranie informacji na temat postaci
 */
public class Statystyki {
    private int hp;
    private int mana;
    private int obrazeniaOdAtaku;
    private int mocUmiejetnosci;
    private int pancerz;
    private int odpornoscNaMagie;
    private double predkoscAtaku;
    private double przyspieszenieUmiejetnosci;
    private double szansaNaTrafienieKrytyczne;
    private int predkoscRuchu;


    public Statystyki(int hp, int pancerz, int odpornoscNaMagie) {
        this.hp = hp;
        this.pancerz = pancerz;
        this.odpornoscNaMagie = odpornoscNaMagie;
    }

    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getObrazeniaOdAtaku() {
        return obrazeniaOdAtaku;
    }

    public void setObrazeniaOdAtaku(int obrazeniaOdAtaku) {
        this.obrazeniaOdAtaku = obrazeniaOdAtaku;
    }

    public int getMocUmiejetnosci() {
        return mocUmiejetnosci;
    }

    public void setMocUmiejetnosci(int mocUmiejetnosci) {
        this.mocUmiejetnosci = mocUmiejetnosci;
    }

    public int getPancerz() {
        return pancerz;
    }

    public void setPancerz(int pancerz) {
        this.pancerz = pancerz;
    }

    public int getOdpornoscNaMagie() {
        return odpornoscNaMagie;
    }

    public void setOdpornoscNaMagie(int odpornoscNaMagie) {
        this.odpornoscNaMagie = odpornoscNaMagie;
    }

    public double getPredkoscAtaku() {
        return predkoscAtaku;
    }

    public void setPredkoscAtaku(double predkoscAtaku) {
        this.predkoscAtaku = predkoscAtaku;
    }

    public double getPrzyspieszenieUmiejetnosci() {
        return przyspieszenieUmiejetnosci;
    }

    public void setPrzyspieszenieUmiejetnosci(double przyspieszenieUmiejetnosci) {
        this.przyspieszenieUmiejetnosci = przyspieszenieUmiejetnosci;
    }

    public double getSzansaNaTrafienieKrytyczne() {
        return szansaNaTrafienieKrytyczne;
    }

    public void setSzansaNaTrafienieKrytyczne(double szansaNaTrafienieKrytyczne) {
        this.szansaNaTrafienieKrytyczne = szansaNaTrafienieKrytyczne;
    }

    public int getPredkoscRuchu() {
        return predkoscRuchu;
    }

    public void setPredkoscRuchu(int predkoscRuchu) {
        this.predkoscRuchu = predkoscRuchu;
    }

    /**
     * Metoda toString zwracająca łańcuch znaków informacji o postaci
     */
    public String toString() {
        return "HP: " + getHp() +
                "\nMana: " + getMana() +
                "\nobrazenia od ataku: " + getObrazeniaOdAtaku() +
                "\nmoc umiejetnosci: " + getMocUmiejetnosci() +
                "\npredkosc ataku: " + getPredkoscAtaku() +
                "\nszansa na trafienie krytyczne: " + getSzansaNaTrafienieKrytyczne() +
                "\nodpornosc na magie: " + getOdpornoscNaMagie() +
                "\npancerz: " + getPancerz() +
                "\nprzyspieszenie umiejetnosci: " + getPrzyspieszenieUmiejetnosci() +
                "\npredkosc ruchu: " + getPredkoscRuchu();
    }
    public String toString1() {
        return "HP: " + getHp() +
                "\npancerz: " + getPancerz() +
                "\nodpornosc na magie: " + getOdpornoscNaMagie();
    }
}
