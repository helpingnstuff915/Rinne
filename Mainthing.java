//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

//i js want this to end bro 
//finally it ended smh
import java.util.Scanner;//for user input dangit

public class Mainthing {
    public static int turns = 0;
    public static boolean newGame = true;
    public static Scanner scammer = new Scanner(System.in);//heheh
    public static patties platter;
    public static String lastAnswer = "";
    public static int currentPatty = 0;
    player player1 = new player("placeholder", 0, 0, 0, 0);//name, health, attack, defense
    player player2 = new player("placeholder", 0, 0, 0, 0);//adding details later
    
    public static boolean CPU = false;//against computer
    public static void main(String[] args) {//I keep forgetting how to init, i js copy paste
        type("init", 100, "Red");//js letting you know that its running
        Mainthing game = new Mainthing();
        game.setupGame();
        game.playGame();
    }

    public static void intro () {
        clearScreen();
        type("Welcome to the game!", 100, "Yellow");
        //for funsies
        if (ask("Do you want to play?","yes,no").equals("no")) {
            System.exit(0);
        }
        ask("Skip intro?","yes,no");
        if(lastAnswer.equals("no")||lastAnswer.equals("o")||lastAnswer.equals("n")){
            type("This is a game about Burgers. ", 100, "Green");
            type("You have to eat them and be the last one standing to win.", 100, "Green");
            type("But be careful, some of them are poisoned and will kill you!", 100, "Green");
            type("You can also get power-ups like: ", 100, "Green");
            type("---", 40, "Green");
            type("Double patty: makes the next Burger deal twice the damaga", 40, "Green");
            type("Skip Turn: skips your rivals next turn", 40, "Green");
            type("Rearrange: flips the order of the patties", 40, "Green");
            type("Bandage: Gives you +1 HP", 40, "Green");
            type("Magnifying class: lets you see if the current patty is poisoned", 40, "Green");
            type("---", 40, "Green");
            type("You can play against a friend or the computer", 100, "Green");
            type("That's all for now, Good luck!", 100, "Green");
        }
    }
    public static void gameOver(player winner){
        newGame = false;
        type("Game Over! " + winner.getName() + " wins!", 100, "Yellow");
        if(ask("Do you want to play again?","yes,no").equals("no")) {
            type("Ight", 100, "Red");
            type("then,", 100, "Blue");
            type("Cya", 100, "Yellow");
            type("around,", 100, "Green");
            type(winner.getName()+"!",100,"CyanOnWhite");
            System.exit(0);
        }else{
            Mainthing nextGame = new Mainthing();
            nextGame.setupGame();
            nextGame.playGame();
        }

    }

    public void setupGame() {
        Mainthing.platter = new patties(0, 0);//num of patties, num of poisoned patties
        if(newGame){
            intro();
            player1.setName(ask("What is your name",null));
            type("Hello " + player1.getName() +"", 100, "Yellow");
        }
        if (ask("are you playing against a friend or the computer","friend,computer").equals("friend")) {
            player2.setName(ask("What is your friend's name",null));
        // } else if (lastAnswer.equals("computer")) { we dont actually need this, for this case!
        } else {
            player2.setName("The Computer");
            CPU = true;
        }
        while(true){
            //somehow, you can get 0 patties???
            int pattiesNum = Integer.parseInt(ask("How many Burgers are we ordering? (max 20, min 2)", "2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20"));
            if(pattiesNum <= 1){
                type("Oh noes, you need at least 2 Burgers!",100,"Red");
                continue;
            }
            platter.setPattyNum(pattiesNum);
            String poisonedOptions = "";
            for (int i = 1; i < pattiesNum; i++) {
                poisonedOptions += i + (i < pattiesNum - 1 ? "," : "");
            }
            ask("How many of them are poisoned? (max " + (pattiesNum - 1) + ")", poisonedOptions);
            if(Integer.parseInt(lastAnswer) < 1 || Integer.parseInt(lastAnswer) >= pattiesNum){
                type("Invalid poisoned patties count!",100,"Red");
                continue;
            }
            platter = new patties(pattiesNum, Integer.parseInt(lastAnswer));
            platter.setOrder();
            int powersCount = Integer.parseInt(ask("How many power-ups do you want? (max 9, Min 0)", "0,1,2,3,4,5,6,7,8,9"));
            
            player1.powerUpsInit(powersCount);
            player2.powerUpsInit(powersCount);
            while(true){
                player1.setHealth(Integer.parseInt(ask("How many lives is dealt to each player? (max 10, Min 0)", "1,2,3,4,5,6,7,8,9,10")));
                if(pattiesNum <= 1){
                    type("Oh noes, you need to get a life!",100,"Red");
                    //dang thats messed up lol
                    continue;
                }       
                break;       
            }
            player2.setHealth(Integer.parseInt(lastAnswer));
            break;//inorder to end the loop.
        }
    }
    public void playerOneTurn(player plays) {
        while(true){
            boolean success = false;
            if(turns < 3){
                type("It's " + plays.getName() + "'s turn!", 100, "Yellow");
                type("You have " + plays.getHealth() + " HP",100,"Yellow");
                type("Type eat to eat the burger", 40, "Yellow");
                type("Type powerup to use a power up", 40, "Yellow");
                type("Type give to give the burger to the opponent", 40, "Yellow");
                ask("What do you want to do?","eat,powerup,give");
            }else{
                String powerup = "";
                if(plays.hasPowerups()){
                    powerup ="or powerup";
                }
                type(plays.getName() + "'s turn : " + plays.getHealth() + " HP", 100, "Yellow");
                type("Options: Give, Eat, " + powerup, 100, "Yellow");
                ask("Choose an option","eat,powerup,give");
            }
            switch (lastAnswer.toLowerCase()) {
                case "eat"->{
                    type("You ate the patty!", 100, "Green");
                    plays.eatPatty(platter);
                    if (player1.getHealth() <= 0) {
                        type(player1.getName() + " was poisoned to death!", 100, "Red");
                        gameOver(player2);
                        break; // This exits the loop IMMEDIATELY
                    }if (player2.getHealth() <= 0) {
                        type(player2.getName() + " was poisoned to death!", 100, "Red");
                        gameOver(player1);
                        break; // This exits the loop IMMEDIATELY
                    }
                    if(player1.isSkipNextTurn()||player2.isSkipNextTurn()){
                        player1.setSkipNextTurn(false);
                        player2.setSkipNextTurn(false);
                        type(plays.getName() + " has an extra turn!", 100, "Red");
                    }else{
                        return;
                    }
                    success = true;
                }
                case "powerup"->{
                    plays.powerUps(platter, (player1.isSkipNextTurn() || player2.isSkipNextTurn()) );
                    success = true;
                }case "powerups"->{
                    plays.powerUps(platter, (player1.isSkipNextTurn() || player2.isSkipNextTurn()) );
                    success = true;
                }
                case "give"->{
                    type("You gave the patty to " + player2.getName() + "!", 100, "Green");
                    player2.eatPatty(platter);
                    if (player1.getHealth() <= 0) {
                        type(player1.getName() + " was poisoned to death!", 100, "Red");
                        gameOver(player2);
                        break; // This exits the loop IMMEDIATELY
                    }if (player2.getHealth() <= 0) {
                        type(player2.getName() + " was poisoned to death!", 100, "Red");
                        gameOver(player1);
                        break; // This exits the loop IMMEDIATELY
                    }
                    if(player1.isSkipNextTurn()||player2.isSkipNextTurn()){
                        player1.setSkipNextTurn(false);
                        player2.setSkipNextTurn(false);
                        type(plays.getName() + " has an extra turn!", 100, "Red");
                    }else{
                        return;
                    }
                    success = true;
                }
            }
            if(!success){
                type("Please choose an option " + plays.getName()+"!",100,"Red");
            }
        }
            // if (currentPatty >= platter.getPatties().length) {
            //     Mainthing.type("Tray empty! Ordering more...", 100, "Yellow");
            //     platter.setOrder(); //refills the tray array
            //     currentPatty = 0; //start over
            // }else{
            //     currentPatty++;
            // }
    }

    public void playGame() {
        while(player1.getHealth() >= 0 && player2.getHealth() >= 0){

            if (currentPatty >= platter.getPatties().length) {
                Mainthing.type("All Burgers finished! Ordering a new round...", 100, "Yellow");
                platter.setOrder();//redo the tray
                currentPatty = 0;//reset
            }
            playerOneTurn(player1);
            //we only need to check only p2, as p1 is checked in the if statement
            if(player2.getHealth() <= 0){
                break;
            }

            if(CPU){
                //the ai ALWAYS is the second player
                // so it always plays against p1
                player2.decide(platter, player1);
            }else {
                //player one func, but with p2 stats
                playerOneTurn(player2);
            }
        }
    }

    public static String ask(String question, String options) {
        boolean valid = false;
        question = question + "?";//Incase you forget to add a question mark, it adds it for you
        //if you already did then it makes it look more emphasized, so its alr
        while(!valid){
            type(question,30,"Blue");
            String answer = scammer.nextLine();//jaja, scammer lol
            //in case anything goes
            if(options == null) {
                lastAnswer = answer;
                return answer;
            } else if (options.contains(answer) && answer.length() > 0) {
                lastAnswer = answer;
                return answer;
            } else {
                type("Invalid option. the options are: " + options, 100, "Red");
            }
        }
        return null;//js to compile, but techincally dead code
    }

    //func to make it typed out slowly
        @SuppressWarnings("BusyWait")
    //also had to ask ai to help with the thread bit
    public static void type(String text, int speed, String colour) {
        final String Red = "\u001B[31m";
        final String Green = "\u001B[32m";
        final String Blue = "\u001B[34m";
        final String Yellow = "\u001B[33m";
        final String CyanOnWhite = "\u001B[36;47m";
        switch (colour) {
            case "Red"->{
                colour = Red;
            }
            case "Green"->{
                colour = Green;
            }
            case "Blue"->{
                colour = Blue;
            }
            case "Yellow"->{
                colour = Yellow;
            }case "CyanOnWhite"->{
                colour = CyanOnWhite;
            }
            default->{
                // No color
            }
        }

        for (char c : text.toCharArray()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.print("" + colour + c + "");
        }
        System.out.println();
    }
    //Clears the console for a new game
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");//this is some cool stuff, Ansi escape codes
        //Were also going to use ansi stuff for colors
        System.out.flush();//clear console
    }
}