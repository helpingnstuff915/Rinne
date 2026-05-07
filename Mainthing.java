//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

import java.util.Scanner;//for user input dangit

public class Mainthing {
    public static String lastAnswer = "";
    public static int currentPatty = 0;
    public static boolean CPU = false;//against computer
    public static void main(String[] args) {//I keep forgetting how to init, i js copy paste
        type("init", 100, "Red");//js letting you know that its running
        startGame();
    }

    public static void powerUp(player plays, patties p){
        type("You currently have " + plays.getDoublePatty() + " double patties, ",100, "Green");
        type(plays.getSkipTurn() + " skip turns ",100, "Green");
        type("and "+ plays.getRearange() + " rearrange power-ups!", 100, "Green");
        ask("Which power-up do you want to use?","double patty,skip turn,rearrange");
        if(plays.getDoublePatty()+plays.getSkipTurn()+plays.getRearange() <= 0){
            type("You don't have any power-ups left!", 100, "Red");
            return;
        }
        switch (lastAnswer) {
            case "double patty":
                if(plays.getDoublePatty() <= 0){
                    type("You don't have any double patty power-ups left!", 100, "Red");
                }else{
                    if(plays.isDoubleNextPatty()){
                        type("It's already a double patty!", 100, "Red");
                        type("You can't eat a quadruple patty!", 100, "Red");
                    }else{
                        plays.setDoublePatty(plays.getDoublePatty() - 1);
                        type("Yummers, a double patty!", 100, "Green");
                        plays.setDoubleNextPatty(true);
                    }
                }
                break;
                //bro this code is lagging HARD, things are getting discoulred
            case "skip turn":
                if(plays.getSkipTurn() <= 0){
                    type("You don't have any skip turn power-ups left!", 100, "Red");
                }else{
                    if(plays.isSkipNextTurn()){
                        type("You can only use that once per round!", 100, "Red");
                    } else {
                        plays.setSkipTurn(plays.getSkipTurn() - 1);
                        plays.setSkipNextTurn(true);
                        type("You skipped your opponent's next turn!", 100, "Green");
                    }
                }
                break;
            case "rearrange":
                if(plays.getDoublePatty() <= 0){
                    type("You don't have any double patty power-ups left!", 100, "Red");
                }else{
                    //We dont need to check this, since it js wastes it
                    plays.setRearange(plays.getRearange() - 1);
                    p.flip();
                    type("You reversed the order of the patties!", 100, "Green");
                }
                break;
        }

    }

    public static void startGame() {
        //Initialization (search 111 to see the end)
        player player1 = new player("placeholder", 0, 0, 0, 0);//name, health, attack, defense
        player player2 = new player("placeholder", 0, 0, 0, 0);//adding details later
        String p2name = "placeholder";
        patties platter = new patties(0, 0);//num of patties, num of poisoned patties
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
        String name = ask("What is your name",null);
        player1.setName(name);
        type("Hello " + player1.getName() +"", 100, "Yellow");
        if (ask("are you playing against a friend or the computer","friend,computer").equals("friend")) {
            // Handle friend gameplay
            System.out.println("hollup, ima add that rq.");
            String name2 = ask("What is your friend's name",null);
            p2name = lastAnswer;
        // } else if (lastAnswer.equals("computer")) { we dont actually need this, for this case!
        } else {
            System.out.println("hollup, ima add that rq.");
            CPU = true;
        }
        while(true){
            int pattiesNum = 0;
            int poisonedNum = 0;
            ask("How many patties are we ordering? (max 20)", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20");
            if(Integer.parseInt(lastAnswer) > 20 || Integer.parseInt(lastAnswer) < 1){
                type("Please enter a number between 1 and 20.", 100, "Red");
                continue;
            }else {
                pattiesNum = Integer.parseInt(lastAnswer);
                ask("How many of them are poisoned? (max " + (pattiesNum - 1) + ")", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19");
                if(Integer.parseInt(lastAnswer) > pattiesNum - 1 || Integer.parseInt(lastAnswer) < 1){
                    type("Please enter a number between 1 and " + (pattiesNum - 1) + ".", 100, "Red");
                    continue;
                }
            }
            platter = new patties(pattiesNum, poisonedNum);
            platter.setOrder();

            ask("How many power-ups do you want? (max 3)", "0,1,2,3");
            if(Integer.parseInt(lastAnswer) > 3 || Integer.parseInt(lastAnswer) < 0){
                type("Please enter a number between 0 and 3.", 100, "Red");
            }else{
                int powerUps = Integer.parseInt(lastAnswer);
                int currentPowerups = 0;
                    while(currentPowerups < powerUps){
                        if(Math.random() < 0.33){
                            player1.setDoublePatty(player1.getDoublePatty() + 1);
                        } else if (Math.random() < 0.5) {
                            player1.setSkipTurn(player1.getSkipTurn() + 1);
                        } else {
                            player1.setRearange(player1.getRearange() + 1);
                        }
                        currentPowerups++;
                    }
                        //Initial tesing for valid distribution of power ups
                        // its meant to be a secret, but only you can see it
                        // The reason its not shown here is bc its shown later on
                    // System.out.println("You got " + player1.getDoublePatty() + " double patty, " + player1.getSkipTurn() + " skip turn, and " + player1.getRearange() + " rearrange power-ups!");
                ask("How many lives is dealt to each player? (max 10)", "1,2,3,4,5,6,7,8,9,10");
                if(Integer.parseInt(lastAnswer) > 10 || Integer.parseInt(lastAnswer) < 1){
                    type("Please enter a number between 1 and 10.", 100, "Red");
                    continue;
                }else{
                    player1.setHealth(Integer.parseInt(lastAnswer));
                    if(CPU){
                        player2.setName("Computer"); 
                        currentPowerups = 0;
                    while(currentPowerups < powerUps){
                        if(Math.random() < 0.33){
                            player2.setDoublePatty(player2.getDoublePatty() + 1);
                        } else if (Math.random() < 0.5) {
                            player2.setSkipTurn(player2.getSkipTurn() + 1);
                        } else {
                            player2.setRearange(player2.getRearange() + 1);
                        }
                        currentPowerups++;
                    }
                    // System.out.println("The computer got " + player2.getDoublePatty() + " double patty, " + player2.getSkipTurn() + " skip turn, and " + player2.getRearange() + " rearrange power-ups!");
                    }else{
                        player2.setName(p2name);
                        player2.setHealth(Integer.parseInt(lastAnswer));
                        currentPowerups = 0;
                        while(currentPowerups < powerUps){
                            if(Math.random() < 0.33){
                                player2.setDoublePatty(player2.getDoublePatty() + 1);
                            } else if (Math.random() < 0.5) {
                                player2.setSkipTurn(player2.getSkipTurn() + 1);
                            } else {
                                player2.setRearange(player2.getRearange() + 1);
                            }
                            currentPowerups++;
                        }
                        // System.out.println(player2.getName() + " got " + player2.getDoublePatty() + " double patty, " + player2.getSkipTurn() + " skip turn, and " + player2.getRearange() + " rearrange power-ups!");
                    }
                    // This is the original code for the power ups distribution.
                    // currentPowerups = 0;
                    // while(currentPowerups < powerUps){
                    //     if(Math.random() < 0.33){
                    //         player2.setDoublePatty(player2.getDoublePatty() + 1);
                    //     } else if (Math.random() < 0.5) {
                    //         player2.setSkipTurn(player2.getSkipTurn() + 1);
                    //     } else {
                    //         player2.setRearange(player2.getRearange() + 1);
                    //     }
                    //     currentPowerups++;
                    // }

                }
                break;

            }
        }
        //111, end of initialization, starting game
        while(player1.getHealth() > 0 && (player2.getHealth() > 0)){
            //player 1 turn

            type("It's " + player1.getName() + "'s turn!", 100, "Yellow");
            //show power-ups
            type("You have " + player1.getDoublePatty() + " double patty, " + player1.getSkipTurn() + " skip turn, and " + player1.getRearange() + " rearrange power-ups!", 100, "Green");
            //ask if they want to use a power-up
            type("Type eat to eat a patty", 100, "Yellow");
            type("Type powerup to use a power-up", 100, "Yellow");
            type("Type give to give the patty to the opponent", 100, "Yellow");
            ask("What do you want to do?","eat,powerup,give");
            switch (lastAnswer) {
                case "eat":
                    //eating part (not fully implemented yet)
                    //you would ask which patty they want to eat and then check if its poisoned or not and then update health accordingly
                    break;
                case "powerup":
                    ask("Which power-up do you want to use?","double patty,skip turn,rearrange");
                    switch (lastAnswer) {
                        case "double patty":
                            if(player1.getDoublePatty() > 0){
                                player1.setDoublePatty(player1.getDoublePatty() - 1);
                                //effect of double patty is handled in the eating part
                            } else {
                                type("You don't have any double patty power-ups left!", 100, "Red");
                            }
                            break;
                        case "skip turn":
                            if(player1.getSkipTurn() > 0){
                                player1.setSkipTurn(player1.getSkipTurn() - 1);
                                type("You skipped your turn!", 100, "Green");
                                continue;//skips the rest of the loop and goes to the next iteration (player 2's turn)
                            } else {
                                type("You don't have any skip turn power-ups left!", 100, "Red");
                            }
                            break;
                        case "rearrange":
                            if(player1.getRearange() > 0){
                                player1.setRearange(player1.getRearange() - 1);
                                platter.flip();
                                type("You reversed the order of the patties!", 100, "Green");
                            } else {
                                type("You don't have any rearrange power-ups left!", 100, "Red");
                            }
                            break;
                    }
                        break;
                case "give":
                    type("You gave the patty to " + player2.getName() + "!", 100, "Green");
                    break;
            }
            }
    }

    public static String ask(String question, String options) {
        boolean valid = false;
        Scanner scammer = new Scanner(System.in);
        question = question + "?";//Incase you forget to add a question mark, it adds it for you
        //if you already did then it makes it look more emphasized, so its alr
        while(!valid){
            System.out.println(question);
            String answer = scammer.nextLine();//jaja, scammer lol
            //in case anything goes
            if(options == null) {
                valid = true;
                lastAnswer = answer;
                return answer;
            } else
            if (options.contains(answer)) {
                valid = true;
                lastAnswer = answer;
                return answer;
            } else {
                type("Invalid option. the options are: " + options, 100, "Red");
            }
        }
        return null;//js to compile, but techincally dead code
    }

    //func to make it typed out slowly
    public static void type(String text, int speed, String colour) {
        final String Red = "\u001B[31m";
        final String Green = "\u001B[32m";
        final String Blue = "\u001B[34m";
        final String Yellow = "\u001B[33m";
        switch (colour) {
            case "Red":
                colour = Red;
                break;
            case "Green":
                colour = Green;
                break;
            case "Blue":
                colour = Blue;
                break;
            case "Yellow":
                colour = Yellow;
                break;
            default:
                // No color
                break;
        }

        for (char c : text.toCharArray()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
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