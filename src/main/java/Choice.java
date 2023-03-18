/**
 * Enum responsible for the commands that the user using the application will use
 */
public enum Choice {
    CONTINUE(null, null),
    LEVEL_CHOICE("/level", "Choose hero level!"),
    HERO_CHOICE("/hero", "Choose your champion!"),
    ITEM_CHOICE("/item", "Choose item!"),
    UNKNOWN_COMMAND(null, ERROR.UNKNOWN_COMMAND.getMessage()),
    INVENTORY("/inventory", null),
    DUEL("/duel", null),
    OPEN_INVENTORY("/show inventory", "Your hero inventory"),
    EQUIP_ITEMS("/equip", null),
    REMOVE_ITEM("/remove item", null),
    OPTIONS_FOR_HERO("/champion option", """
            You can display:
            All general information about the hero's stats and skills by typing:   
            ------------------------------------------------------------------------------------------------------------
            -all
            -name
            -Level
            -Statistics
            -HP
            -Mana
            -Physical damage
            -Ability power
            -Attack speed
            -Crit chance
            -Magic resistance
            -Armor
            -CDR             (Cooldown reduction)
            -Movement speed
            -Skills
            -Q
            -W
            -E
            -R
            -passive
            -scaling
            ------------------------------------------------------------------------------------------------------------
            Small and capital letters do not matter
            I.E.:\s
            hp
            passive
            Physical damage
            statistics"""),
    CHAMPIONS("/show champs", "Above are the characters from the database you can access"),
    ITEMS("/show items", "Above are items from the database that you can access"),
    EXIT("/exit", "Goodbye Summoner!"),
    ENEMY_CHOICE("/enemy choice", "Choose your opponent from the characters available in the database"),
    RANDOM_OPPONENT("/enemy random", "That's the chosen opponent!"),
    TRAINING_DUMMY("/training", "Create a dummy!"),
    RETURN("/return", null);

    final String option;
    final String message;


    Choice(String option, String message) {
        this.option = option;
        this.message = message;
    }

    public static Choice get(String input) {
        for (Choice r : Choice.values()) {
            if (input.equalsIgnoreCase(r.option)) {
                return r;
            }
        }
        return input.startsWith("/") ? UNKNOWN_COMMAND : CONTINUE;
    }

    public String getMessage() {
        return message;
    }
}
