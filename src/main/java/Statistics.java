

/**
 * Klasa odpowiedzialna za statystyki naszej postaci, zawiera ona prywatne pola, reprezentujące postać oraz gettery
 * i settery odpowiedzialne za pobieranie informacji na temat postaci
 */
public class Statistics {
    private int hp;
    private int mana;
    private int physicalDamage;
    private int powerAbility;
    private int armor;
    private int magicRes;
    private double attackSpeed;
    private double cooldownReduction;
    private double critChance;
    private int movementSpeed;


    public Statistics(int hp, int armor, int magicRes) {
        this.hp = hp;
        this.armor = armor;
        this.magicRes = magicRes;
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

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getPowerAbility() {
        return powerAbility;
    }

    public void setPowerAbility(int powerAbility) {
        this.powerAbility = powerAbility;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMagicRes() {
        return magicRes;
    }

    public void setMagicRes(int magicRes) {
        this.magicRes = magicRes;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getCooldownReduction() {
        return cooldownReduction;
    }

    public void setCooldownReduction(double cooldownReduction) {
        this.cooldownReduction = cooldownReduction;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Metoda toString zwracająca łańcuch znaków informacji o postaci
     */
    public String toString() {
        return "HP: " + getHp() +
                "\nMana: " + getMana() +
                "\nPhysical Damage: " + getPhysicalDamage() +
                "\nPower ability: " + getPowerAbility() +
                "\nAttack speed: " + getAttackSpeed() +
                "\nCrit chance: " + getCritChance() +
                "\nMagic resistance: " + getMagicRes() +
                "\nArmor: " + getArmor() +
                "\nCooldown reduction: " + getCooldownReduction() +
                "\nMovement speed: " + getMovementSpeed();
    }
    public String toString1() {
        return "HP: " + getHp() +
                "\nArmor: " + getArmor() +
                "\nMagic resistance: " + getMagicRes();
    }
}
