public class player{
    private String name;
    private int health;
    private int doublePatty;
    private int skipTurn;
    private int rearange;
    private boolean skipNextTurn;
    private boolean doubleNextPatty;
    private boolean initialized = false;

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
//all eating/ power ups should be handeled here in the player class!
    }

    public void setDoubleNextPatty(boolean doubleNextPatty) {
        this.doubleNextPatty = doubleNextPatty;
        if(doubleNextPatty){
            Mainthing.type("Your next patty will be a double patty!",100, "Green");
        }
    }

    public void powerUpsInit(player plays){
        Mainthing.ask("How many power-ups do you want? (max 5)", "0,1,2,3,4,5");
       if(!initialized){
            int powerUps = Integer.parseInt(Mainthing.lastAnswer);
            int currentPowerups = 0;
            while(currentPowerups < powerUps){
                if(Math.random() < 0.33){
                    plays.setDoublePatty(plays.getDoublePatty() + 1);
                } else if (Math.random() < 0.5) {
                    plays.setSkipTurn(plays.getSkipTurn() + 1);
                } else {
                    plays.setRearange(plays.getRearange() + 1);
                }
                currentPowerups++;
            }
        }
        initialized = true;
    }

    public void livesInit(){

    }

    public void powerUps(patties p){
        if(this.doublePatty +this.skipTurn + this.rearange == 0){
            Mainthing.type("You have no power-ups left!",100, "Red");
        }else{
            Mainthing.type("You have " + this.doublePatty + " double patties, ", 100, "Green");
            Mainthing.type(this.skipTurn + " skip turns, and ",100,"Green");
            Mainthing.type(this.rearange + " rearranges left.",100, "Green");
            Mainthing.ask("Which power up do you want to use?", "DP,ST,RA");
            switch (Mainthing.lastAnswer){
                case "DP"->{
                    if(this.doublePatty <= 0){
                        Mainthing.type("You don't have any double patty power-ups left!", 100, "Red");
                    }else{
                        this.doublePatty -= 1;
                        Mainthing.type("Yummers, a double patty!", 100, "Green");
                        this.setDoubleNextPatty(true);
                    }
                }
                case "ST"->{
                    if(this.skipTurn <= 0){
                        Mainthing.type("You don't have any skip turn power-ups left!", 100, "Red");
                    }else{
                        if(this.isSkipNextTurn()){
                            Mainthing.type("You can only use that once per round!", 100, "Red");
                        } else {
                            this.skipTurn -= 1;
                            this.setSkipNextTurn(true);
                            Mainthing.type("You skipped your opponent's next turn!", 100, "Green");
                        }
                    }
                }
                case "RA"->{
                    if(this.rearange <= 0){
                        Mainthing.type("You don't have any rearrange power-ups left!", 100, "Red");
                    }else{
                        if(Mainthing.currentPatty == 0){
                            Mainthing.type("You can't rearrange the patties on the first turn!", 100, "Red");
                        } else {
                            this.rearange -= 1;
                            Mainthing.type("The patties have been rearranged!", 100, "Green");
                            p.setOrder();
                        }
                    }
                }
            }
        }
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