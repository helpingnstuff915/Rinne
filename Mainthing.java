//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

import java.util.Scanner;//for user input dangit

public class Mainthing {
    public static String lastAnswer = "";
    public static void main(String[] args) {//I keep forgetting how to init, i js copy paste
        type("init", 100, "Red");//js letting you know that its running
        startGame();
    }

    public static void startGame() {
        player player1 = new player("Player 1", 100, 10, 5,1,1,1);//name, health, attack, defense
        clearScreen();
        type("Welcome to the game!", 100, "Yellow");
        String name = ask("What is your name","");
        player1.setName(name);
        type("Hello " + player1.getName() +"", 100, "Yellow");
        if (ask("are you playing against a friend or the computer","friend,computer").equals("friend")) {
            // Handle friend gameplay
            System.out.println("hollup, ima add that rq.");
        } else if (lastAnswer.equals("computer")) {
            System.out.println("hollup, ima add that rq.");
        }
    }

    public static String ask(String question, String options) {
        boolean valid = false;
        Scanner scammer = new Scanner(System.in);
        question = question + "?";//Incase you forget to add a question mark, it adds it for you
        //if you already did then it makes it look more emphasized, so its alr
        while(!valid){ 
            System.out.println(question);
            String answer = scammer.nextLine();
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
    @SuppressWarnings("CallToPrintStackTrace")
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