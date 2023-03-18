

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<Champion> champions;
    List<Items> items;
    HashMap<String, HashMap<String, String>> championInventory = new HashMap<>();

    Champion heroFound = null;
    Items itemFound = null;

    public App() {

        Gson gson = new Gson();
        Reader readerChampions;
        Reader readerItems;
        InputStream inputStreamChampions = getClass().getResourceAsStream("/Postacie.json");
        InputStream inputStreamItems = getClass().getResourceAsStream("/Postacie.json");
        assert inputStreamChampions != null;
        readerChampions = new InputStreamReader(inputStreamChampions);
        assert inputStreamItems != null;
        readerItems = new InputStreamReader(inputStreamItems);

        JsonObject json = gson.fromJson(readerChampions, JsonObject.class);
        JsonArray championsJson = json.getAsJsonArray("postacie");
        champions = new ArrayList<>();
        for (JsonElement postacJson : championsJson) {
            Champion champion = gson.fromJson(postacJson, Champion.class);
            champions.add(champion);
        }

        JsonObject jsonItems = gson.fromJson(readerItems, JsonObject.class);
        JsonArray ItemsJson = jsonItems.getAsJsonArray("itemy");
        items = new ArrayList<>();

        for (JsonElement ItemJson : ItemsJson) {
            Items items1 = gson.fromJson(ItemJson, Items.class);
            items.add(items1);
        }
        System.out.println(Informations.INTRODUCTION.getMessage());
        System.out.println(Informations.MAIN_OPTIONS.getMessage());
        String itemOrHeroChoice = scanner.nextLine();
        Choice choice = Choice.get(itemOrHeroChoice);
        while (choice != Choice.EXIT) {
            boolean petlaNr2 = true;
            try {
                switch (choice) {
                    case CHAMPIONS:
                        heroShow();
                    case UNKNOWN_COMMAND:
                        System.out.println(choice.getMessage());
                        break;
                    case ITEMS:
                        itemShow();
                        break;
                    case HERO_CHOICE:
                        System.out.println(choice.getMessage());
                        itemOrHeroChoice = scanner.nextLine();
                        while (petlaNr2) {
                            heroFound = heroFind(itemOrHeroChoice);
                            if (heroFound != null) {
                                System.out.println(Informations.HERO_INFO.getMessage());
                                String pickOption = scanner.nextLine();
                                choice = Choice.get(pickOption);
                                switch (choice) {
                                    case OPTIONS_FOR_HERO, UNKNOWN_COMMAND:
                                        System.out.println(choice.getMessage());
                                        break;
                                    case CONTINUE:
                                        showHeroInfo(pickOption, heroFound);
                                        break;
                                    case LEVEL_CHOICE:
                                        heroLevel(heroFound);
                                        break;
                                    case INVENTORY:
                                        heroInventory(heroFound);
                                        break;
                                    case DUEL:
                                        abilityTest(heroFound);
                                    case RETURN:
                                        petlaNr2 = false;
                                        break;
                                    case EXIT:
                                        System.out.println(choice.getMessage());
                                        System.exit(1);
                                }
                            } else {
                                petlaNr2 = false;
                            }
                        }
                        break;
                    case ITEM_CHOICE:
                        System.out.println(choice.getMessage());
                        itemOrHeroChoice = scanner.nextLine();
                        itemFound = findItem(itemOrHeroChoice);
                        if (itemFound != null) {
                            itemFound(itemFound);
                            System.out.println(itemFound);
                        }
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Informations.MAIN_OPTIONS.getMessage());
            itemOrHeroChoice = scanner.nextLine();
            choice = Choice.get(itemOrHeroChoice);

        }
        System.out.println(choice.getMessage());
    }

    public Items findItem(String itemChoice) {
        Items itemFound;
        for (Items items1 : items) {
            if (items1.name().equalsIgnoreCase(itemChoice)) {
                itemFound = items1;
                return itemFound;
            }
        }
        System.out.println(ERROR.INVALID_ITEM.getMessage() + itemChoice);
        return null;
    }

    public void itemFound(Items item) {
        System.out.println(Informations.ITEM_PICK.getMessage() + item.name());
    }

    public void heroShow() {
        System.out.println();
        champions.stream()
                .collect(Collectors.groupingBy(i -> champions.indexOf(i) / 4))
                .forEach((key, value) -> System.out.println(value.stream()
                        .map(Champion::getName)
                        .collect(Collectors.joining(", "))));
        System.out.println();
    }

    public Champion heroFind(String hero) {
        Champion heroFound;
        for (Champion champion : champions) {
            if (champion.getName().equalsIgnoreCase(hero)) {
                heroFound = champion;
                System.out.println(Informations.CHAMPION_PICK.getMessage() + hero);
                return heroFound;
            }
        }
        System.out.println(ERROR.INVALID_CHARACTER.getMessage() + hero);
        return null;
    }

    public void itemShow() {
        System.out.println();
        items.stream()
                .collect(Collectors.groupingBy(i -> items.indexOf(i) / 4))
                .forEach((key, value) -> System.out.println(value.stream()
                        .map(Items::name)
                        .collect(Collectors.joining(", "))));
        System.out.println();
    }

    public void showHeroInfo(String option, Champion foundHero) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Choice choice = Choice.get(option);
        assert foundHero != null;
        switch (option.toLowerCase()) {
            case "/opcje" -> System.out.println(choice.getMessage());
            case "name" -> System.out.println("Imię: " + foundHero.getName());
            case "level" -> System.out.println("Poziom: " + foundHero.getLevel());
            case "hp" -> System.out.println("HP: " + foundHero.getStatystyki().getHp());
            case "mana" -> System.out.println("Mana: " + foundHero.getStatystyki().getMana());
            case "physical damage" -> System.out.println("physicalDamage: " + foundHero.getStatystyki().getPhysicalDamage());
            case "ability power" -> System.out.println("Ability power: " + foundHero.getStatystyki().getPowerAbility());
            case "attack speed" -> System.out.println("Attack speed: " + foundHero.getStatystyki().getAttackSpeed());
            case "crit chance" -> System.out.println("Crit chance: " + foundHero.getStatystyki().getCritChance());
            case "magic resistance" -> System.out.println("Magic resistance: " + foundHero.getStatystyki().getMagicRes());
            case "armor" -> System.out.println("Armor: " + foundHero.getStatystyki().getArmor());
            case "cdr" -> System.out.println("Cooldown reduction: " + foundHero.getStatystyki().getCooldownReduction());
            case "movement speed" -> System.out.println("Movement speed: " + foundHero.getStatystyki().getMovementSpeed());
            case "q" -> abilityQ(foundHero);
            case "w" -> abilityW(foundHero);
            case "e" -> abilityE(foundHero);
            case "r" -> abilityR(foundHero);
            case "passive" -> passiveAbility(foundHero);
            case "statistics" -> System.out.println(foundHero.getStatystyki().toString());
            case "skills" -> {
                abilityQ(foundHero);
                abilityW(foundHero);
                abilityE(foundHero);
                abilityR(foundHero);
                passiveAbility(foundHero);
            }
            case "scaling" -> System.out.println(foundHero.getSkalowanie().toString());
            case "all" -> {
                System.out.println("Name: " + foundHero.getName());
                System.out.println("Level: " + foundHero.getLevel());
                System.out.println(foundHero.getStatystyki().toString());
                System.out.println(foundHero.getSkalowanie().toString());
                ability(foundHero);
            }
            default -> {
                if (option.equalsIgnoreCase("/champion option")) {
                    System.out.println(choice.getMessage());
                } else {
                    System.out.println(ERROR.UNKNOWN_COMMAND.getMessage());
                }
            }
        }
    }

    public void ability(Champion foundHero) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        abilityQ(foundHero);
        abilityW(foundHero);
        abilityE(foundHero);
        abilityR(foundHero);
        passiveAbility(foundHero);
    }

    public void showStatsAndSkills(Champion foundHero) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        abilityQ(foundHero);
        abilityW(foundHero);
        abilityE(foundHero);
        abilityR(foundHero);
        passiveAbility(foundHero);
        System.out.println("Level: " + foundHero.getLevel());
        System.out.println(foundHero.getStatystyki().toString());
    }

    public void heroLevel(Champion foundHero) {
        System.out.println(Informations.HERO_LEVEL.getMessage());
        int level;
        while (true) {
            System.out.println("Input \"/return\" to return or" +
                    "\nEnter a number from 1 to 18:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("/return")) {
                break;
            } else {
                try {
                    level = Integer.parseInt(input);
                    if (level < 1 || level > 18) {
                        throw new NumberFormatException();
                    } else {
                        statisticsReset(foundHero, foundHero.getLevel());
                        System.out.println("\nYou have successfully changed your character's level! The current level is: " + level + "\n");
                        foundHero.setLevel(level);
                        if (foundHero.getLevel() == 1) {
                            level = foundHero.getLevel();
                        } else {
                            level = foundHero.getLevel() - 1;
                        }
                        foundHero.getStatystyki().setHp((int) (foundHero.getStatystyki().getHp() + (foundHero.getSkalowanie().getHpScaling() * level)));
                        foundHero.getStatystyki().setMana((int) (foundHero.getStatystyki().getMana() + (foundHero.getSkalowanie().getManaScaling() * level)));
                        foundHero.getStatystyki().setPhysicalDamage((int) (foundHero.getStatystyki().getPhysicalDamage() + (foundHero.getSkalowanie().getAdScaling() * level)));
                        foundHero.getStatystyki().setArmor((int) (foundHero.getStatystyki().getArmor() + (foundHero.getSkalowanie().getArmorScaling() * level)));
                        foundHero.getStatystyki().setMagicRes((int) (foundHero.getStatystyki().getMagicRes() + (foundHero.getSkalowanie().getMrScaling() * level)));
                        foundHero.getStatystyki().setAttackSpeed((foundHero.getStatystyki().getAttackSpeed() + (foundHero.getSkalowanie().getAsScaling() * level)));
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong number entered! Remember that the hero's level can be 1-18");
                }
            }
        }
    }

    public void statisticsReset(Champion heroFound, int level) {
        level -= 1;
        heroFound.getStatystyki().setHp((int) (heroFound.getStatystyki().getHp() - (heroFound.getSkalowanie().getHpScaling() * level)));
        heroFound.getStatystyki().setMana((int) (heroFound.getStatystyki().getMana() - (heroFound.getSkalowanie().getManaScaling() * level)));
        heroFound.getStatystyki().setPhysicalDamage((int) (heroFound.getStatystyki().getPhysicalDamage() - (heroFound.getSkalowanie().getAdScaling() * level)));
        heroFound.getStatystyki().setArmor((int) (heroFound.getStatystyki().getArmor() - (heroFound.getSkalowanie().getArmorScaling() * level)));
        heroFound.getStatystyki().setMagicRes((int) (heroFound.getStatystyki().getMagicRes() - (heroFound.getSkalowanie().getMrScaling() * level)));
        heroFound.getStatystyki().setAttackSpeed((heroFound.getStatystyki().getAttackSpeed() - (heroFound.getSkalowanie().getAsScaling() * level)));
    }


    public void abilityQ(Champion heroFound) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("First Ability: " + heroFound.getUmiejetnoscPierwsza().getName());
        System.out.println("Description: " + heroFound.getUmiejetnoscPierwsza().getDescription());
        Base_Damage Q = heroFound.getUmiejetnoscPierwsza().getBaseDamage();
        System.out.println("Base damage:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) Q.getClass().getMethod("getLvl" + i).invoke(Q);
            System.out.println("Lvl " + i + " without scaling: " + dmg);
            System.out.println("Lvl " + i + " with scaling: " + (dmg + (heroFound.getUmiejetnoscPierwsza().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility())));
        }
    }

    public void abilityW(Champion heroFound) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Second Ability: " + heroFound.getUmiejetnoscDruga().getName());
        System.out.println("Description: " + heroFound.getUmiejetnoscDruga().getDescription());
        Base_Damage W = heroFound.getUmiejetnoscDruga().getBaseDamage();
        System.out.println("Base damage:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) W.getClass().getMethod("getLvl" + i).invoke(W);
            System.out.println("Lvl " + i + " without scaling: " + dmg);
            System.out.println("Lvl " + i + " with scaling: " + (dmg + (heroFound.getUmiejetnoscDruga().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility())));
        }
    }

    public void abilityE(Champion heroFound) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Third Ability: " + heroFound.getUmiejetnoscTrzecia().getName());
        System.out.println("Description: " + heroFound.getUmiejetnoscTrzecia().getDescription());
        Base_Damage E = heroFound.getUmiejetnoscTrzecia().getBaseDamage();
        System.out.println("Base damage:");
        for (int i = 1; i <= 5; i++) {
            Integer dmg = (Integer) E.getClass().getMethod("getLvl" + i).invoke(E);
            System.out.println("Lvl " + i + " without scaling: " + dmg);
            if (heroFound.getUmiejetnoscTrzecia().getAbilityPowerScaling() == 0) {
                System.out.println("Lvl " + i + " with scaling(without frozen): " + (dmg + (heroFound.getUmiejetnoscTrzecia().getAbilityPowerScalingWithAniviaBoost() * heroFound.getStatystyki().getPowerAbility())));
                System.out.println("Lvl " + i + " with scaling(frozen): " + (dmg + (heroFound.getUmiejetnoscTrzecia().getAbilityPowerScalingWithoutAniviaBoost() * heroFound.getStatystyki().getPowerAbility())));

            } else {
                System.out.println("Lvl " + i + " with scaling: " + (dmg + (heroFound.getUmiejetnoscTrzecia().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility())));
            }
        }
    }

    public void abilityR(Champion heroFound) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println();
        System.out.println("Fourth Ability: " + heroFound.getUmiejetnoscCzwarta().getName());
        System.out.println("Description: " + heroFound.getUmiejetnoscCzwarta().getDescription());
        Base_Damage R = heroFound.getUmiejetnoscCzwarta().getBaseDamage();
        System.out.println("Base damage:");
        for (int i = 1; i <= 3; i++) {
            Integer dmg = (Integer) R.getClass().getMethod("getLvl" + i).invoke(R);
            System.out.println("Lvl " + i + " without scaling " + dmg);
            System.out.println("Lvl " + i + " with scaling " + (dmg + (heroFound.getUmiejetnoscCzwarta().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility())));
        }
        System.out.println();
    }

    public void passiveAbility(Champion heroFound) {
        System.out.println("Passive Ability " + heroFound.getUmiejetnoscPasywna().getName());
        System.out.println("Passive Ability bonus " + heroFound.getUmiejetnoscPasywna().getDescription());
    }

    public void heroInventory(Champion heroFound) {
        System.out.println(Informations.ITEMS_INFO.getMessage());
        String itemChoice = scanner.nextLine();
        Choice choice = Choice.get(itemChoice);
        while (choice != Choice.RETURN) {
            switch (choice) {
                case ITEMS:
                    itemShow();
                    System.out.println(choice.getMessage());
                    break;
                case OPEN_INVENTORY:
                    showInventory();
                    break;
                case EQUIP_ITEMS:
                    equipInventory(heroFound);
                    break;
                case REMOVE_ITEM:
                    itemDelete(heroFound);
                    break;
                case EXIT:
                    System.out.println(choice.getMessage());
                    System.exit(1);
            }
            System.out.println(Informations.ITEMS_INFO.getMessage());
            itemChoice = scanner.nextLine();
            choice = Choice.get(itemChoice);
        }
    }

    public void equipInventory(Champion heroFound) {
        System.out.println("Input item name!");
        String itemChoice = scanner.nextLine();
        Items itemFound = findItem(itemChoice);
        if (itemFound != null) {
            itemFound(itemFound);
            System.out.println(itemFound);
            HashMap<String, String> ekwipunek = championInventory.getOrDefault(heroFound.getName(), new HashMap<>());
            ekwipunek.put(itemFound.name(), itemFound.type());
            championInventory.put(heroFound.getName(), ekwipunek);
            addingStatistics(heroFound, itemFound);
        }
    }

    public void addingStatistics(Champion heroFound, Items itemFound) {
        heroFound.getStatystyki().setHp((heroFound.getStatystyki().getHp() + itemFound.hp()));
        heroFound.getStatystyki().setAttackSpeed((heroFound.getStatystyki().getAttackSpeed() + itemFound.attackSpeed()));
        heroFound.getStatystyki().setMovementSpeed((heroFound.getStatystyki().getMovementSpeed() + itemFound.movementSpeed()));
        heroFound.getStatystyki().setPowerAbility((heroFound.getStatystyki().getPowerAbility() + itemFound.abilityPower()));
        heroFound.getStatystyki().setMana((heroFound.getStatystyki().getMana() + itemFound.mana()));
        heroFound.getStatystyki().setCooldownReduction(heroFound.getStatystyki().getCooldownReduction() + itemFound.cooldownReduction());
        heroFound.getStatystyki().setCritChance(heroFound.getStatystyki().getCritChance() + itemFound.critChance());
        heroFound.getStatystyki().setPhysicalDamage((heroFound.getStatystyki().getPhysicalDamage() + itemFound.physicalDamage()));
        heroFound.getStatystyki().setMagicRes((heroFound.getStatystyki().getMagicRes() + itemFound.magicResistance()));
    }

    public void showInventory() {
        System.out.println("Champion inventory " + heroFound.getName() + ":");
        // KOD DO WYŚWIETLENIA POSTACI
        HashMap<String, String> inventory = championInventory.get(heroFound.getName());
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        for (String itemName : inventory.keySet()) {
            String itemType = inventory.get(itemName);
            System.out.println("Name: " + itemName);
            System.out.println("Type: " + itemType);
        }
    }

    public void itemDelete(Champion heroFound) {
        System.out.println("Which item you want to remove?");
        String itemName = scanner.nextLine().toLowerCase();
        Items itemFound = findItem(itemName);
        //Szukamy przedmiotu w ekwipunku bohatera
        HashMap<String, String> inventory = championInventory.get(heroFound.getName());
        if (inventory == null || !inventory.containsKey(itemName)) {
            System.out.println("You do not have such an item in your inventory.");
            return;
        }
        String itemType = inventory.get(itemName);
        inventory.remove(itemName);
        championInventory.put(heroFound.getName(), inventory);
        System.out.println("Item removed:");
        System.out.println("Name: " + itemName);
        System.out.println("Type: " + itemType);
        removeStatistics(heroFound, itemFound);
    }

    public void removeStatistics(Champion heroFound, Items itemFound) {
        heroFound.getStatystyki().setHp((heroFound.getStatystyki().getHp() - itemFound.hp()));
        heroFound.getStatystyki().setAttackSpeed((heroFound.getStatystyki().getAttackSpeed() - itemFound.attackSpeed()));
        heroFound.getStatystyki().setMovementSpeed((heroFound.getStatystyki().getMovementSpeed() - itemFound.movementSpeed()));
        heroFound.getStatystyki().setPowerAbility((heroFound.getStatystyki().getPowerAbility() - itemFound.abilityPower()));
        heroFound.getStatystyki().setMana((heroFound.getStatystyki().getMana() + itemFound.mana()));
        heroFound.getStatystyki().setCooldownReduction(heroFound.getStatystyki().getCooldownReduction() - itemFound.cooldownReduction());
        heroFound.getStatystyki().setCritChance(heroFound.getStatystyki().getCritChance() - itemFound.critChance());
        heroFound.getStatystyki().setPhysicalDamage((heroFound.getStatystyki().getPhysicalDamage() - itemFound.physicalDamage()));
        heroFound.getStatystyki().setMagicRes((heroFound.getStatystyki().getMagicRes() - itemFound.magicResistance()));
    }

    public void abilityTest(Champion heroFound) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println(Informations.DAMAGE_TEST.getMessage());
        String enemyChoice = scanner.nextLine();
        Champion enemy;
        String ability;
        HashMap<String, Integer> skill;
        Choice choice = Choice.get(enemyChoice);
        while (choice != Choice.RETURN) {
            switch (choice) {
                case CHAMPIONS -> heroShow();
                case ENEMY_CHOICE -> {
                    enemy = heroChoice(heroFound);
                    showStatsAndSkills(enemy);
                    duelInfo(heroFound, enemy);
                }
                //METODA POJEDYNKU
                case RANDOM_OPPONENT -> {
                    enemy = randomOpponent(heroFound);
                    showStatsAndSkills(enemy);
                    duelInfo(heroFound, enemy);
                }

                //METODA POJEDYNKU
                case TRAINING_DUMMY -> {
                    enemy = createTrainingDummy(heroFound);
                    System.out.println(enemy.getStatystyki().toString1());
                    ability = abilityChoice();
                    if (ability != null) {
                        damageCalculation(ability);
                        skill = damageCalculation(ability);
                        abilityDamage(skill, heroFound, enemy);
                    }
                }
                case EXIT -> {
                    System.out.println(choice.getMessage());
                    System.exit(1);
                }
                case UNKNOWN_COMMAND -> System.out.println(choice.getMessage());
            }


            System.out.println(Informations.DAMAGE_TEST.getMessage());
            enemyChoice = scanner.nextLine();
            choice = Choice.get(enemyChoice);
        }
    }
    public void duelInfo(Champion heroFound, Champion enemy) {
        System.out.println("Duel: " +
                "\n" + heroFound.getName() + " vs " + enemy.getName());
        System.out.println("Teraz jak powinien wyglądać pojedynek?");
    }

    public Champion randomOpponent(Champion heroFound) {
        List<Champion> champions = new ArrayList<>();
        for (Champion champion : this.champions) {
            if (!champion.getName().equals(heroFound.getName())) {
                champions.add(champion);
            }
        }
        Random random = new Random();
        return champions.get(random.nextInt(champions.size()));
    }

    public Champion heroChoice(Champion heroFound) {
        while (true) {
            System.out.println("\nChoose the name of the character you want to fight!");
            String heroChoice = scanner.nextLine().toLowerCase();

            for (Champion champion : champions) {
                if (champion.getName().toLowerCase().equals(heroChoice) && !champion.equals(heroFound)) {
                    return champion;
                }
            }
            System.out.println("\nYou entered the wrong name of the character, or the same as yours!\nTry again!");
        }
    }
    public Champion createTrainingDummy(Champion heroFound) {
        Statistics statisticsDummy = new Statistics(heroFound.getStatystyki().getHp(),
                heroFound.getStatystyki().getArmor(),
                heroFound.getStatystyki().getMagicRes());
        Champion dummy = new Champion(
                "Dummy",
                1,
                statisticsDummy,
                heroFound.getUmiejetnoscPierwsza(),
                heroFound.getUmiejetnoscDruga(),
                heroFound.getUmiejetnoscTrzecia(),
                heroFound.getUmiejetnoscCzwarta(),
                heroFound.getUmiejetnoscPasywna(),
                heroFound.getSkalowanie()
        );
        while (true) {
            try {
                dummy.getStatystyki().setHp(15);
                dummy.getStatystyki().setArmor(15);
                System.out.println("Enter an integer representing the dummy's magic resistance");
                int statystyka = scanner.nextInt();
                dummy.getStatystyki().setMagicRes(statystyka);
                scanner.nextLine();
                return dummy;
            } catch (InputMismatchException e) {
                System.out.println("Invalid number format! try again");
                scanner.next();
            }
        }
    }
    public String abilityChoice() {
        Scanner scanner = new Scanner(System.in);
        String skills;
            System.out.println(Informations.ABILITY_PATTERN.getMessage());
            skills = scanner.nextLine();
            // Sprawdzanie minimalnej długości ciągu
            if (skills.length() < 3) {
                System.out.println("The minimum length of the skill string is 2 characters (1 letter and 1 number separated by a space).");
                return null;
            }
            // Sprawdzanie maksymalnej długości ciągu
            if (skills.length() > 15) {
                System.out.println("The maximum length of the skill string is 8 characters (4 letters and 4 numbers separated by a space).");
                return null;
            }
            // Sprawdzanie unikalności liter
            for (int i = 0; i < skills.length(); i += 4) {
                char litera = skills.charAt(i);
                if (skills.indexOf(litera) != skills.lastIndexOf(litera)) {
                    System.out.println("Letter " + litera + " occurs more than once!");
                    return null;
                }
            }
            // Sprawdzanie liter i cyfr
            String[] abilityArray = skills.split(" ");
            String letters = "QWER";
            String numbersQWE = "12345";
            String numbersR = "123";
            for (int i = 0; i < abilityArray.length; i += 2) {
                String letter = abilityArray[i];
                String number = abilityArray[i+1];
                if (!letters.contains(letter)) {
                    System.out.println("Wrong letter " + letter);
                    return null;
                }
                if (letter.equals("R")) {
                    if (!numbersR.contains(number)) {
                        System.out.println("Wrong number: " + number + " after letter: " + letter);
                        return null;
                    }
                } else {
                    if (!numbersQWE.contains(number)) {
                        System.out.println("Wrong number: " + number + " after letter: " + letter);
                        return null;
                    }
                }
            }
            System.out.println("A valid string has been entered: " + skills);
            return skills;
    }
    public HashMap<String, Integer> damageCalculation(String ability) {
        String[] skille = ability.split(" ");
        HashMap<String, Integer> skill = new HashMap<>();
        for (int i = 0; i < skille.length; i += 2) {
            skill.put(skille[i], Integer.valueOf(skille[i + 1]));
        }
        return skill;
    }
    public void abilityDamage(HashMap<String, Integer> skill, Champion heroFound, Champion enemy) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        int totalDamage = 0;
        Base_Damage Q = heroFound.getUmiejetnoscPierwsza().getBaseDamage();
        Base_Damage W = heroFound.getUmiejetnoscDruga().getBaseDamage();
        Base_Damage E = heroFound.getUmiejetnoscTrzecia().getBaseDamage();
        Base_Damage R = heroFound.getUmiejetnoscCzwarta().getBaseDamage();
        System.out.println("Enemy magic resistance: " + enemy.getStatystyki().getMagicRes());

        for (String key: skill.keySet()) {
            if (key.equalsIgnoreCase("Q")) {
                System.out.println("Ability: " + heroFound.getUmiejetnoscPierwsza().getName() + " (Q)");
                Integer dmg = (Integer) Q.getClass().getMethod("getLvl" + skill.get(key)).invoke(Q);
                int damage = (int) (dmg + (heroFound.getUmiejetnoscPierwsza().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility()));
                if (enemy.getStatystyki().getMagicRes() > 0) {
                    damage = damage / (1 + enemy.getStatystyki().getMagicRes() / 100);
                }
                System.out.println("Lvl " + skill.get(key) + " Damage: " + damage);
                totalDamage += damage;

            }
            if (key.equalsIgnoreCase("W")) {
                System.out.println("Ability: " + heroFound.getUmiejetnoscDruga().getName() + " (W)");

                Integer dmg = (Integer) W.getClass().getMethod("getLvl" + skill.get(key)).invoke(W);
                int damage = (int) (dmg + (heroFound.getUmiejetnoscDruga().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility()));
                if (enemy.getStatystyki().getMagicRes() > 0) {
                    damage = damage / (1 + enemy.getStatystyki().getMagicRes() / 100);
                }
                System.out.println("Lvl " + skill.get(key) + " Damage: " + damage);
                totalDamage += damage;
            }
            if (key.equalsIgnoreCase("E")) {
                System.out.println("Ability: " + heroFound.getUmiejetnoscTrzecia().getName() + " (E)");

                Integer dmg = (Integer) E.getClass().getMethod("getLvl" + skill.get(key)).invoke(E);
                int damage = (int) (dmg + (heroFound.getUmiejetnoscTrzecia().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility()));
                if (enemy.getStatystyki().getMagicRes() > 0) {
                    damage = damage / (1 + enemy.getStatystyki().getMagicRes() / 100);
                }
                System.out.println("Lvl " + skill.get(key) + " Damage: " + damage);
                totalDamage += damage;
            }
            if (key.equalsIgnoreCase("R")) {
                System.out.println("Ability: " + heroFound.getUmiejetnoscCzwarta().getName() + " (R)");
                Integer dmg = (Integer) R.getClass().getMethod("getLvl" + skill.get(key)).invoke(R);
                int damage = (int) (dmg + (heroFound.getUmiejetnoscCzwarta().getAbilityPowerScaling() * heroFound.getStatystyki().getPowerAbility()));
                if (enemy.getStatystyki().getMagicRes() > 0) {
                    damage = damage / (1 + enemy.getStatystyki().getMagicRes() / 100);
                }
                System.out.println("Lvl " + skill.get(key) + " Damage: " + damage);
                totalDamage += damage;
            }
        }
        System.out.println("Combined damage: " + totalDamage);
    }
}


