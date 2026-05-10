# Rinne
The repo for the "Rinne" text based game
"Rinne" means the cycle of life, death, and rebirth. I thought it would be a cool name for this version of russian roulette that I made.

In this game, you need to eat burgers and be the last one standing, as some of the patties are poisoned! Eating a good patty gives you +1 HP and eating a poisoned one loses -1 HP. However, you get to choose how many burgers there are and how many of the patties are poisoned. You can play against a friend or against the big brain computah

## Live Demo

You can try out the live demo here! 
--> (Insert link!!!) <--

## Setup

1. Download the java files
2. Run Mainthing.java (main file) in JDK version 17 or higher (Most stable java version)
3. If any errors occur, it usually becauce its looking for "main.java" class, but the main file here is named Mainthing. Please rename the files to fix this or run ```java Mainthing.java ```

# How to play

The player chooses their name and if they are playing single player or 2 player. Single player means playing against the computer. You then choose how many patties you want and how many of those are poisoned. Finally you get to choose how many powerups you get (given randomly of course).

# Rules
Rules are similar to the original russian roulette, with a few variations. A turn is only passed when a player eats or gives a double patty (except if they have a skip turn equipped). The game ends when a player is at 0 HP. (Note: the computer is VERY very good at playing, it has an experimental winrate of 72%, GOOD LUCK HAHA).

## Powerups

### Double Patty
Using a double patty will make the next patty deal double the damage (-2 HP) IF it is poisoned. If the patty is safe, it doesn't double the HP gained or the chance of getting +1 HP. Cannot be stacked.

### Skip Turn
Using a powerup does not count as a turn, your turn ends once you eat/give a Patty. Using a Skipturn skips the opponents turn once and cannot be stacked on the same turn. Cannot be stacked.

### Rearrange
Using a rearrange the patties order randomly. Replaces all patties in the tray, including previous patties that were eaten. Poisioned patties are the same number as initially chosen. Can be stacked.

### Bandage
Using a bandage heals +1 HP. Can be stacked.

### Magnifying Glasses
Can be used to check if the next patty is poisoned or safe. Can be stacked.

# Gameplay

## Setup Mode
Setup your name and game mode (player vs player OR player vs computer). Choose the number of patties in a tray (max of 20) and the number of poisoned patties (min of 1). Choose how many powerups each player is dealt at random. Finally choose the number of lives each player is dealt.

## Actual Gameplay
Take turns eating or giving the patty to the opponent until you either one of the players run out of health. Eat patties for a 60% chance of getting +1 HP if safe or 100% chance of losing -1HP if poisoned. After 3 turns have passed the text gets typed out faster and becomes more compact for faster gameplay.

## Questions or concerns
If you have any questions email me at slack (@insanitizer).

# Thank you for reading the readme.md file!
## Which you should read
### And you did :) <3