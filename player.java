public class player{
    private String name;
    private int health;
    private int doublePatty;
    private int skipTurn;
    private int rearange;

    public player(String name, int health, int attack, int defense, int doublePatty, int skipTurn, int rearange) {
        this.name = name;
        this.health = health;
        this.doublePatty = doublePatty;
        this.skipTurn = skipTurn;
        this.rearange = rearange;
        // this.attack = attack;
        // this.defense = defense;
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDoublePatty() {
        return doublePatty;
    }

    public void setDoublePatty(int doublePatty) {
        this.doublePatty = doublePatty;
    }

    public int getSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(int skipTurn) {
        this.skipTurn = skipTurn;
    }

    public int getRearange() {
        return rearange;
    }

    public void setRearange(int rearange) {
        this.rearange = rearange;
    }
}