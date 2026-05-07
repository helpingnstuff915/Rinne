public class player{
    private String name;
    private int health;
    private int doublePatty;
    private int skipTurn;
    private int rearange;
    private boolean skipNextTurn;
    private boolean doubleNextPatty;

    public player(String name, int health, int doublePatty, int skipTurn, int rearange) {
        this.name = name;
        this.health = health;
        this.doublePatty = doublePatty;
        this.skipTurn = skipTurn;
        this.rearange = rearange;
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

    public boolean isSkipNextTurn() {
        return skipNextTurn;
    }

    public void setSkipNextTurn(boolean skipNextTurn) {
        this.skipNextTurn = skipNextTurn;
    }

    public boolean isDoubleNextPatty() {
        return doubleNextPatty;
    }

    public void setDoubleNextPatty(boolean doubleNextPatty) {
        this.doubleNextPatty = doubleNextPatty;
    }

    public void eatPatty(int currentPatty, patties p){
        if(p.isPoisoned(currentPatty)==true){
             if(doubleNextPatty){
                health -= 2;
                doubleNextPatty = false;
            } else {
                health -= 1;
            }
        }
    }
}