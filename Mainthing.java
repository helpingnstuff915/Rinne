//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

//i js want this to end bro 
import java.util.Scanner;//for user input dangit

public class Mainthing {
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
        if(ask("Skip intro?","yes,no").equals("no")){
            type("This is a game about patties. ", 100, "Green");
            type("You have to eat them to win.", 100, "Green");
            type("But be careful, some of them are poisoned and will kill you.", 100, "Green");
            type("You can also get power-ups like double patty, skip turn, and rearrange.", 100, "Green");
            type("Good luck!", 100, "Green");
        }
    }

    public static void gameOver(player winner){
        newGame = false;
        type("Game Over! " + winner.getName() + " wins!", 100, "Yellow");
        if(ask("Do you want to play again?","yes,no").equals("no")) {
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
        }
        player1.setName(ask("What is your name",null));
        type("Hello " + player1.getName() +"", 100, "Yellow");
        if (ask("are you playing against a friend or the computer","friend,computer").equals("friend")) {
            player2.setName(ask("What is your friend's name",null));
        // } else if (lastAnswer.equals("computer")) { we dont actually need this, for this case!
        } else {
            player2.setName("Computer");
        }
        while(true){
            int pattiesNum = Integer.parseInt(ask("How many patties are we ordering? (max 20)", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20"));
            String poisonedOptions = "";
            for (int i = 1; i < pattiesNum; i++) {
                poisonedOptions += i + (i < pattiesNum - 1 ? "," : "");
            }
            ask("How many of them are poisoned? (max " + (pattiesNum - 1) + ")", poisonedOptions);
            platter = new patties(pattiesNum, Integer.parseInt(lastAnswer));
            platter.setOrder();

            player1.powerUpsInit(player1);
            player2.powerUpsInit(player2);
            player1.livesInit();
            player1.setHealth(Integer.parseInt(ask("How many lives is dealt to each player? (max 10)", "1,2,3,4,5,6,7,8,9,10")));
            player2.setHealth(Integer.parseInt(lastAnswer));
            break;//inorder to end the loop.
        }
    }
    public void playerOneTurn(player plays) {
        type("It's " + plays.getName() + "'s turn!", 100, "Yellow");
        //show power-ups
        type("You have " + plays.getDoublePatty() + " double patty, " + plays.getSkipTurn() + " skip turn, and " + plays.getRearange() + " rearrange power-ups!", 100, "Green");
        //ask if they want to use a power-up
        type("Type eat to eat a patty", 100, "Yellow");
        type("Type powerup to use a power-up", 100, "Yellow");
        type("Type give to give the patty to the opponent", 100, "Yellow");
        ask("What do you want to do?","eat,powerup,give");
        switch (lastAnswer) {
            case "eat"->{
                type("You ate the patty!", 100, "Green");
                plays.eatPatty(currentPatty, platter);
            }
            case "powerup"->{
                plays.powerUps(platter);
            }
            case "give"->{
                type("You gave the patty to " + player2.getName() + "!", 100, "Green");
                player2.eatPatty(currentPatty, platter);
            }
        }
        currentPatty++;
    }

    public void playerTwoTurn(player plays) {
        if(!CPU){
            playerOneTurn(plays);
        }else {
            //code for AI
            currentPatty++;
        }
    }

    public void playGame() {
        while(player1.getHealth() > 0 && player2.getHealth() > 0){
            playerOneTurn(player1);
            if(player2.getHealth() <= 0){
                break;
            }
            playerTwoTurn(player2);
        }
    }

    public static String ask(String question, String options) {
        boolean valid = false;
        question = question + "?";//Incase you forget to add a question mark, it adds it for you
        //if you already did then it makes it look more emphasized, so its alr
        while(!valid){
            System.out.println(question);
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
            System.out.print(""+colour + c+ "");
        }
        System.out.println();
    }
    //Clears the console for a new game
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");//this is some cool stuff, Ansi escape codes
        //Were also going to use ansi stuff for colors
        System.out.flush();
    }
}