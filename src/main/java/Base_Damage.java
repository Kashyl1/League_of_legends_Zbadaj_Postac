
/**
 * Klasa odpowiedzialna za reprezentowanie poziomu umiejętności skilli, zawiera ona konstruktor oraz kilka metod instancyjnych
 * dodatkowo  gettery
 */
public class Base_Damage {
    private int lvl1;
    private int lvl2;
    private int lvl3;
    private int lvl4;
    private int lvl5;

    public Base_Damage(int lvl1, int lvl2, int lvl3, int lvl4, int lvl5) {
        this.lvl1 = lvl1;
        this.lvl2 = lvl2;
        this.lvl3 = lvl3;
        this.lvl4 = lvl4;
        this.lvl5 = lvl5;
    }

    public int getLvl1() {
        return lvl1;
    }


    public int getLvl2() {
        return lvl2;
    }


    public int getLvl3() {
        return lvl3;
    }
    public int getLvl4() {
        return lvl4;
    }
    public int getLvl5() {
        return lvl5;
    }

}
