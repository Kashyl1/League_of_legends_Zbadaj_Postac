public class Skalowanie {
    private double skalowanieHP;
    private double skalowanieMany;
    private double skalowanieAD;
    private double skalowaniePancerz;
    private double skalowanieMR;
    private double skalowanieAS;


    public double getSkalowanieAD() {
        return skalowanieAD;
    }


    public double getSkalowaniePancerz() {
        return skalowaniePancerz;
    }

    public double getSkalowanieMR() {
        return skalowanieMR;
    }


    public double getSkalowanieAS() {
        return skalowanieAS;
    }


    public double getSkalowanieHP() {
        return skalowanieHP;
    }

    public double getSkalowanieMany() {
        return skalowanieMany;
    }

    public String toString() {
        return "Skalowanie z poziomem postaci: " +
                "\nSkalowanie HP: " + getSkalowanieHP() +
                "\nSkalowanie Many: " + getSkalowanieMany() +
                "\nSkalowanie AD: " + getSkalowanieAD() +
                "\nSkalowanie Pancerza: " + getSkalowaniePancerz() +
                "\nSkalowanie odporności na magię: " + getSkalowanieMR() +
                "\nSkalowanie Prędkości ataku: " + getSkalowanieAS();
    }
}
