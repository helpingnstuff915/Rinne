public class player{
    private String name;
    private int health;
    private int doublePatty;
    private int bandage;
    private int mag;
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

    public int getBandage() {
        return bandage;
    }

    public void setBandage(int bandage) {
        this.bandage = bandage;
    }
    
    public int getMag() {
        return mag;
    }

    public void setMag(int mag) {
        this.mag = mag;
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
            Mainthing.type("Your next Burger will be a double patty!",100, "Green");
        }
    }

    public boolean hasPowerups(){
        return(this.doublePatty +this.skipTurn + this.rearange) > 0;
    }

    public void powerUpsInit(int powerUps){
        int currentPowerups = 0;
        while(currentPowerups < powerUps){
            if(Math.random() < 0.2){
                this.setDoublePatty(this.getDoublePatty() + 1);
            } else if (Math.random() < 0.4) {
                this.setSkipTurn(this.getSkipTurn() + 1);
            } else if (Math.random() < 0.6) {
                this.setRearange(this.getRearange() + 1);
            } else if (Math.random() < 0.8) {
                this.setBandage(this.getBandage() + 1);
            } else if (Math.random() < 1.0) {
                this.setMag(this.getMag() + 1);
            }
            currentPowerups++;
        }
    }

    public void powerUps(patties p, boolean skipTurnOn){
        if(this.doublePatty +this.skipTurn + this.rearange + this.bandage + this.mag == 0){
            Mainthing.type("You have no power-ups left!", 100, "Red");
        }else{
            if(Mainthing.turns < 3){
                Mainthing.type("You have " + this.doublePatty + " double patties, ", 40, "Green");
                Mainthing.type(this.skipTurn + " skip turns,", 40, "Green");
                Mainthing.type(this.rearange + " rearranges,", 40, "Green");
                Mainthing.type(this.bandage + " bandages, and", 40, "Green");
                Mainthing.type(this.mag + " magnifying glasses left.", 40, "Green");
                Mainthing.type("Type DP(double patty), ST (skip turn), RA (rearrange)", 30, "Green");
                Mainthing.type("BA(bandage), or MG (magnifying glass)", 30, "Green");
                Mainthing.type("Or just type 'none' to go back", 100, "Green");
                Mainthing.ask("Which power up do you want to use?", "DP,ST,RA,BA,MG,none");
            }else{
                Mainthing.type("You have " + this.doublePatty + " DP, " + this.skipTurn + 
                " ST, " + this.rearange + " RA, " + this.bandage + " BA, " + 
                this.mag + " MG, ", 40, "Green");

                Mainthing.type("type 'none' to go back", 15, "Green");
                Mainthing.ask("Choose an option", "DP,ST,RA,BA,MG,none");
            }
            switch (Mainthing.lastAnswer.toUpperCase()){
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
                            //hmm, idk it confuses the ai
                            Mainthing.type("You can't rearrange the patties on the first turn!", 100, "Red");
                        } else {
                            this.rearange -= 1;
                            Mainthing.type("The patties have been rearranged!", 100, "Green");
                            p.setOrder();
                        }
                    }
                }case "BA"->{
                    if(this.bandage <= 0){
                        Mainthing.type("You don't have any bandage power-ups left!", 100, "Red");
                    }else{
                        this.bandage -= 1;
                        this.setHealth(this.getHealth()+1);
                        Mainthing.type(this.getName() + " used a bandage and gained +1 HP!", 100, "Green");
                        Mainthing.type(this.getName() + " now has " + this.getHealth() + " HP!", 100, "Green");
                    }
                }case "MG"->{
                    if(this.mag <= 0){
                        Mainthing.type("You don't have any magnifying glass power-ups left!", 100, "Red");
                    }else{
                        this.mag -= 1;
                        Mainthing.type(this.getName() + " is inspecting the patty...", 100, "Green");
                        Mainthing.type("...", 500, "Red");
                        if(p.isPoisoned(Mainthing.currentPatty)){
                            Mainthing.type("(it's poisoned!)", 100, "Red");
                        }else{
                            Mainthing.type("(it's safe!)", 100, "Green");
                        }

                    }
                }
                // case "none"->{ // case"indecisive"->{
                //     //valid = true; //not necessary as we end the func, but makes it more readable

                // }
            }
        }
    }

    //we dont need the currentPatty val, as we can get this from the Mainthing
    public void eatPatty(patties p){
        if(p.isPoisoned(Mainthing.currentPatty)){
            double temp = Math.random();
            String rxn;
            if(temp > 0.75){
                rxn = "Ouch";
            }else if(temp > 0.5){
                rxn = "Bleh";
            }else if(temp > 0.25){
                rxn = "Yuck";
            }else{
                rxn = "Aw man";
            }
            Mainthing.type( rxn + ", that patty was poisoned",100,"Red");

            if(doubleNextPatty){
                this.health -= 2;
                //prevent from going nefative, for asthetics
                if(this.health<0){
                    this.health = 0;
                }
                doubleNextPatty = false;   
                Mainthing.type(this.getName() + " lost 2 HP",100,"Red");
                Mainthing.type(this.getName() + " has " + this.getHealth() + " HP remaining",100,"Red");
            } else {
                this.health -= 1;
                Mainthing.type(this.getName() + " lost 1 HP",100,"Red");
                Mainthing.type(this.getName() + " has " + this.getHealth() + " HP remaining",100,"Red");
            }
            this.doubleNextPatty = false;
        }else{
            Mainthing.type("Mmm... tasty!", 100, "Green");
            Mainthing.type("This patty is safe!", 100, "Green");
            if(Math.random()>0.6){
                this.health +=1;
                Mainthing.type(this.getName() + " gained +1 HP!", 100, "Green");
            }
            this.doubleNextPatty = false;
        }
        Mainthing.currentPatty++;
        if ((Mainthing.currentPatty-1) >= p.getPatties().length) {
            Mainthing.type("\n--- All patties finished! Ordering a new round... ---", 100, "Yellow");
            p.setOrder();//refill tray
            Mainthing.currentPatty = 0;//reset
        }
    }

    //make the human (playing against the ai) eat the burger
    public void takeL(patties p, player human){
        Mainthing.type("Ngl...",100,"CyanOnWhite");
        Mainthing.type("I think you should take the L",100,"CyanOnWhite");
        human.eatPatty(p);
    }

    public void aiEats(patties p, String mainText){
        Mainthing.type(mainText,100,"CyanOnWhite");
        Mainthing.type(this.getName() + " ate the burger",100,"Green");
        this.eatPatty(p);
    }

//This are the methods for the AI decisions
    public void decide(patties p, player rival ){
        double probability = poisonProbability(p);
        //chance of patty being poisoned, 0.0 means low, 0.7-1 is high
        double tempProb = Math.random();
        if(probability > 0.7){
            if((this.getRearange() > 0) && (tempProb < 0.3)){
                this.setRearange(this.getRearange()-1);//advanced stuff lol
                Mainthing.type("IDK flip the patties or smth",100,"CyanOnWhite");
                p.flip();//flip dem patties boi
            }else if( (tempProb<0.8) && (this.getBandage() > 0) ){
                this.setBandage(this.getBandage()-1);
                this.setHealth(this.getHealth()+1);
                Mainthing.type(this.getName() + " used a bandage and gained +1 HP!", 100, "Green");
                Mainthing.type(this.getName() + " now has " + this.getHealth() + " HP!", 100, "Green");
            }else{
                takeL(p,rival);
            }
        }
        else{
            //idk bro, the ai is lowk skeptical bout this
            if(probability > 0.6){
                if((tempProb < 0.5)&&(this.getMag() > 0)){
                    this.setMag(this.getMag()-1);
                    Mainthing.type(this.getName() + " is inspecting the patty...", 100, "Green");
                    Mainthing.type("...", 500, "Red");
                    if(p.isPoisoned(Mainthing.currentPatty)){
                        Mainthing.type("(it's poisoned!)", 100, "Red");
                        takeL(p,rival);
                    }else{
                        Mainthing.type("(it's safe!)", 100, "Green");
                        aiEats(p,"ight bet");
                    }
                }

                if(Math.random()>0.65){
                    this.eatPatty(p);
                }else{
                    rival.eatPatty(p);
                }
            }
//The ai is more likely to give rather than eat at 50%
//This is an defensive tactic, protecting future safety by
//increasing the odds of the next patty being poisoned
            if(probability > 0.5 ){
                if(Math.random()>0.55){
                    aiEats(p,"Screw it, Ima risk it for a biscut");
                }else{
                    takeL(p,rival);
                }
            }if(probability > 0.4 ){
                if(Math.random()>0.35){
                    aiEats(p,"Lets js get this over with...");
                }else{
                    takeL(p,rival);
                }
            }if(probability > 0.3 ){
                if(Math.random()>0.15){
                    aiEats(p,"Nah this is too easy");
                }else{
                    takeL(p,rival);
                }
            }else{
                if(Math.random()>0.95){
                    Mainthing.type("Ima give it for funsies lol",100,"CyanOnWhite");
                    rival.eatPatty(p);
                }else{
                    aiEats(p,"Pickles, lettuce, onions,...");
                }
            }
        }
    }

    public double poisonProbability(patties p){
        double remainingPatties = p.getPatties().length - Mainthing.currentPatty;
        double remainingPoison = 0;

        for(int i = Mainthing.currentPatty; i < p.getPatties().length; i++){
            if(p.isPoisoned(i)){
                remainingPoison++;
            }         
        }
        return remainingPoison / remainingPatties;
    }

}