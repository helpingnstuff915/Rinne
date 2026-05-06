//should I use OOPS? (Object oriented programming stuff, yea I put the stuff in the end)

public class Mainthing {
    public static void main(String[] args) {//I keep forgetting how to init, i js copy paste
        type("Hello, Java!", 100);
        clearScreen();
    }

    //func to make it typed out slowly
    public static void type(String text, int speed) {
        for (char c : text.toCharArray()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }
            System.out.print(c);
        }
        System.out.println();
    }

    //Clears the console for a new game
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}