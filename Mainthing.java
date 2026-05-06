//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

public class Mainthing {
    public static void main(String[] args) {//I keep forgetting how to init, i js copy paste
        type("Hello, Java!", 100, "Red");
    }

    //func to make it typed out slowly
    public static void type(String text, int speed, String colour) {
        final String Red = "\u001B[31m";
        final String Green = "\u001B[32m";
        final String Blue = "\u001B[34m";
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