# League_of_legends_Zbadaj_Postac

A Java console application.
This program allows you to view a character or items from the game League of legends, and then for example examine the damage of the hero's skills depending on the opponent's magic resistance.
At the moment there are 12 magical characters, the user can view their stats at level 1-18, and equip/remove items of which there are 25 to increase
character stats.

# Technologies Used
-Java


# Sample application session:
```
The program currently only works for magic characters PATCH: 13.5

Commands:
"/show champs" - To display all the characters in the database
"/show items" - To display all the items in the database
"/hero" - To go to champ select
"/item" - To go to item select
"/exit" - To turn off Application

> /show champs

Diana, Vex, Xerath, Evelynn
Lissandra, Annie, Ryze, Ahri
Veigar, Orianna, Kassadin, Anivia

Above are the characters from the database you can access
Commands:
"/show champs" - To display all the characters in the database
"/show items" - To display all the items in the database
"/hero" - To go to champ select
"/item" - To go to item select
"/exit" - To turn off Application

> /hero

Choose your champion!

> Diana
Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> /champion option

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
I.E.: 
hp
passive
Physical damage
Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> statistics

HP: 640
Mana: 375
Physical Damage: 57
Power ability: 0
Attack speed: 0.72
Crit chance: 0.0
Magic resistance: 0
Armor: 31
Cooldown reduction: 0.0
Movement speed: 345
Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> /level

Pick hero level 1-18
Input "/return" to return or
Enter a number from 1 to 18:

> 18

You have successfully changed your character's level! The current level is: 18

Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> statistics

HP: 2493
Mana: 800
Physical Damage: 108
Power ability: 0
Attack speed: 1.0889
Crit chance: 0.0
Magic resistance: 34
Armor: 104
Cooldown reduction: 0.0
Movement speed: 345
Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> /inventory

Commands:
"/show items" - To display all items
"/equip" - To equip items
"/show inventory" - To see your inventory
"/remove item" - To remove item (Uppercase and lowercase letters are distinguished!)
"/return" - To return
"/exit" - To turn off Application

> /show items

hextech rocketbelt, rylai crystal scepter, crown of the shattered queen, everfrost
archangel staff, zhonya hourglass, morello, horizon focus
demonic embrace, night harvester, riftmaker, blasting wand
boots of speed, boots of swiftness, ionian boots of lucidity, boots of mobility
ninja tabi, sorcerer shoes, berserker greaves, shadowflame
cosmic drive, luden tempest, infinity edge, banshee veil

Above are items from the database that you can access
Commands:
"/show items" - To display all items
"/equip" - To equip items
"/show inventory" - To see your inventory
"/remove item" - To remove item (Uppercase and lowercase letters are distinguished!)
"/return" - To return
"/exit" - To turn off Application

> /equip

Input item name!

> riftmaker

Item selected: riftmaker
Name: riftmaker
Type: Legendary
Passive: For each second in combat with champions, deal 3% increased damage, stacking up to 3 times for a maximum of 9%. 
While this effect is fully stacked, convert 100% of the increased damage into true damage.
abilityPower: 80
cooldownReduction: 20.0
mana: 600

Commands:
"/show items" - To display all items
"/equip" - To equip items
"/show inventory" - To see your inventory
"/remove item" - To remove item (Uppercase and lowercase letters are distinguished!)
"/return" - To return
"/exit" - To turn off Application

> /show inventory

Champion inventory Diana:
Name: riftmaker
Type: Legendary
Commands:
"/show items" - To display all items
"/equip" - To equip items
"/show inventory" - To see your inventory
"/remove item" - To remove item (Uppercase and lowercase letters are distinguished!)
"/return" - To return
"/exit" - To turn off Application

> /return

Character selected: Diana
Choose what you want to do, or enter:
"/champion option" - To show options for champion
"/return" - To undo
"/level" - To level up hero
"/inventory" - To equip items
"/duel" - To go to duel and check damage
"/exit" - To turn off Application

> /duel

Commands:
"/enemy choice" - To choose an opponent (Not implemented yet)
"/enemy random" - To random choose your opponent (Not implemented yet)
"/training" - To create Dummy
"/show champs" - To display all champions
"/return" - To return
"/exit" - To turn off Application

> /training

Enter an integer representing the dummy's magic resistance

> 50

HP: 15
Armor: 15
Magic resistance: 50
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

> Q 5 W 5 E 5 R 3

A valid string has been entered: Q 5 W 5 E 5 R 3
Enemy magic resistance: 50
Ability: Crescent Strike (Q)
Lvl 5 Damage: 200
Ability: Moonfall (R)
Lvl 3 Damage: 400
Ability: Lunar Rush (E)
Lvl 5 Damage: 130
Ability: Pale Cascade (W)
Lvl 5 Damage: 198
Combined damage: 928
Commands:
"/enemy choice" - To choose an opponent (Not implemented yet)
"/enemy random" - To random choose your opponent (Not implemented yet)
"/training" - To create Dummy
"/show champs" - To display all champions
"/return" - To return
"/exit" - To turn off Application

> /return

Commands:
"/show champs" - To display all the characters in the database
"/show items" - To display all the items in the database
"/hero" - To go to champ select
"/item" - To go to item select
"/exit" - To turn off Application

> /exit

Goodbye Summoner!
```





