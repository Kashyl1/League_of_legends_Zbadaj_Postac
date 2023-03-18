/**
 * Enum odpowiedzialny za komendy, których użytkownik korzystający z aplikacji będzie używać
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
            Małe i duże litery nie mają znaczenia, NIE UŻYWAJ POLSKICH ZNAKÓW!
            Przyklad:\s
            hp
            passive
            Obrazenia od ataku
            statistics"""),
    CHAMPIONS("/show champs", "Powyżej znajdują się postacie z bazy danych, do których możesz uzyskać dostęp"),
    ITEMS("/show items", "Powyżej znajdują się przedmoty z bazy danych, do których możesz uzyskać dostęp"),
    EXIT("/exit", "Żegnaj przywoływaczu!"),
    ENEMY_CHOICE("/enemy choice", "Wybierz swojego przeciwnika z postaci dostępnych w bazie danych"),
    RANDOM_OPPONENT("/enemy random", "O to wylosowany przeciwnik!"),
    TRAINING_DUMMY("/training", "Stwórz kukłe!"),
    RETURN("/return", "");

    final String option;
    final String message;

    /**
     * Konstruktor klasy
     */
    Choice(String option, String message) {
        this.option = option;
        this.message = message;
    }

    /**
     * Metoda sprawdzająca czy użytkownik użył dobrej komendy
     * @return
     */
    public static Choice get(String input) {
        for (Choice r : Choice.values()) {
            if (input.equalsIgnoreCase(r.option)) {
                return r;
            }
        }
        return input.startsWith("/") ? UNKNOWN_COMMAND : CONTINUE;
    }

    /**
     * Metoda zwracająca wiadomość w zależnosci od opcji którą użytkownik wybrał
     * @return
     */
    public String getMessage() {
        return message;
    }
}
