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
//all eating/ power ups should be handeled here in the player class!
    }

    public void setDoubleNextPatty(boolean doubleNextPatty) {
        this.doubleNextPatty = doubleNextPatty;
        if(doubleNextPatty){
            Mainthing.type("Your next patty will be a double patty!",100, "Green");
        }
    }

    public void powerUpsInit(int powerUps){
        int currentPowerups = 0;
        while(currentPowerups < powerUps){
            if(Math.random() < 0.33){
                this.setDoublePatty(this.getDoublePatty() + 1);
            } else if (Math.random() < 0.5) {
                this.setSkipTurn(this.getSkipTurn() + 1);
            } else {
                this.setRearange(this.getRearange() + 1);
            }
            currentPowerups++;
        }
    }

    public void livesInit(){

    }

    public void powerUps(patties p, boolean skipTurnOn){
        if(this.doublePatty +this.skipTurn + this.rearange == 0){
            Mainthing.type("You have no power-ups left!",100, "Red");
        }else{
            Mainthing.type("You have " + this.doublePatty + " double patties, ", 100, "Green");
            Mainthing.type(this.skipTurn + " skip turns, and ",100,"Green");
            Mainthing.type(this.rearange + " rearranges left.",100, "Green");
            Mainthing.type("Type DP(double patty), ST (skip turn), RA (rearrange)",100,"Green");
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
                        if(skipTurnOn){
                            Mainthing.type("You can only use that once per patty!", 100, "Red");
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

//This are the methods for the AI decisions
    public void decide(patties p, int currentPatty, player rival ){
        double probability = poisonProbability(p, currentPatty);
        //chance of patty being poisoned, 0.0 means low, 0.7-1 is high
        if(probability > 0.7){
            if(this.getRearange() > 0){
                this.setRearange(this.getRearange()-1);//advanced stuff lol
                p.flip();//flip dem patties boi
                Mainthing.type("IDK flip the patties or smth",100,"Green");
            }else{
                Mainthing.type("Ngl...",100,"Green");
                Mainthing.type("I think you should take the L",100,"Green");
                rival.eatPatty(currentPatty, p);
            }
        }
        else{
            //idk bro, the ai is lowk skeptical bout this
            if(probability > 0.6 ){
                if(Math.random()>0.65){
                    this.eatPatty(currentPatty, p);
                }else{
                    rival.eatPatty(currentPatty, p);
                }
            }
//The ai is more likely to give rather than eat at 50%
//This is an defensive tactic, protecting future safety by
//increasing the odds of the next patty being poisoned
            if(probability > 0.5 ){
                if(Math.random()>0.55){
                    Mainthing.type("Screw it, Ima risk it for a biscut",100,"Green");
                    this.eatPatty(currentPatty, p);
                }else{
                    Mainthing.type("Ngl...",100,"Green");
                    Mainthing.type("I think you should take the L",100,"Green");
                    rival.eatPatty(currentPatty, p);
                }
            }if(probability > 0.4 ){
                if(Math.random()>0.35){
                    Mainthing.type("Lets js get this over with...",100,"Green");
                    this.eatPatty(currentPatty, p);
                }else{
                    Mainthing.type("Ngl...",100,"Green");
                    Mainthing.type("I think you should take the L",100,"Green");
                    rival.eatPatty(currentPatty, p);
                }
            }if(probability > 0.3 ){
                if(Math.random()>0.15){
                    this.eatPatty(currentPatty, p);
                }else{
                    Mainthing.type("Nah this is too easy",100,"Green");
                    Mainthing.type("I think you should take the L",100,"Green");
                    rival.eatPatty(currentPatty, p);
                }
            }else{
                if(Math.random()>0.95){
                    Mainthing.type("Ngl...",100,"Green");
                    Mainthing.type("Ima give it for funsies lol",100,"Green");
                    rival.eatPatty(currentPatty, p);
                }else{
                    Mainthing.type("Pickles, lettuce, onions,...",100,"Green");
                    this.eatPatty(currentPatty, p);
                }
            }
        }
    }

    public double poisonProbability(patties p, int currentPatty){
        double remainingPatties = p.getPatties().length -currentPatty;
        double remainingPoison = 0;

        for(int i = currentPatty; i < p.getPatties().length; i++){
            if(p.isPoisoned(i)){
                remainingPoison++;
            }         
        }
        return remainingPoison / remainingPatties;
    }

}