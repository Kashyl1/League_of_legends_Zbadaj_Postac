public enum Informations {
    INTRODUCTION("""
            The program currently only works for magic characters PATCH: 13.5
            """),
    MAIN_OPTIONS("""
            Commands:
            "/show champs" - To display all the characters in the database                               
            "/show items" - To display all the items in the database                         
            "/hero" - To go to champ select
            "/item" - To go to item select
            "/exit" - To turn off Application"""),
    HERO_INFO("""
            Choose what you want to do, or enter:               
            "/champion option" - To show options for champion
            "/return" - To undo
            "/level" - To level up hero
            "/inventory" - To equip items
            "/duel" - To go to duel and check damage                  
            "/exit" - To turn off Application"""),
    DAMAGE_TEST("""
            Commands:
            "/enemy choice" - To choose an opponent (Not implemented yet)
            "/enemy random" - To random choose your opponent (Not implemented yet)
            "/training" - To create Dummy
            "/show champs" - To display all champions
            "/return" - To return
            "/exit" - To turn off Application"""),
    ABILITY_PATTERN("""
                The pattern for entering the skills we want to test:                    
                (letter number letter number letter number letter number)
                The string can be up to 8 characters long and must contain the same number of letters as numbers           
                The level is the number you enter after the skill letter               
                Letters can be: Q W E R, where:
                Q - First ability (max level 5)
                W - Second ability (max level 5)
                E - Third ability (max level 5)
                R - Fourth ability (max level 3)
                An example pattern when we want to check the damage of 3 skills:
                Q 4 R 2 W 3
                """),
    HERO_LEVEL("Pick hero level 1-18"),
    CHAMPION_PICK("Character selected: "),
    ITEM_PICK("Item selected: "),
    ITEMS_INFO("""
            Commands:
            "/show items" - To display all items
            "/equip" - To equip items
            "/show inventory" - To see your inventory          
            "/remove item" - To remove item (Uppercase and lowercase letters are distinguished!)
            "/return" - To return
            "/exit" - To turn off Application""");
    private final String message;

    Informations(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
