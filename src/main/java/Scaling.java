public class Scaling {
    private double hpScaling;
    private double manaScaling;
    private double adScaling;
    private double armorScaling;
    private double mrScaling;
    private double asScaling;


    public double getAdScaling() {
        return adScaling;
    }


    public double getArmorScaling() {
        return armorScaling;
    }

    public double getMrScaling() {
        return mrScaling;
    }


    public double getAsScaling() {
        return asScaling;
    }


    public double getHpScaling() {
        return hpScaling;
    }

    public double getManaScaling() {
        return manaScaling;
    }

    public String toString() {
        return "Scaling with champion level: " +
                "\nHP scaling: " + getHpScaling() +
                "\nMana scaling: " + getManaScaling() +
                "\nAD scaling: " + getAdScaling() +
                "\nArmor scaling: " + getArmorScaling() +
                "\nMagic resistance scaling: " + getMrScaling() +
                "\nAttack speed scaling: " + getAsScaling();
    }
}
