public class player{
    private String name;
    private int health;

    //Power up counts for each player
    private int doublePatty;
    private int bandage;
    private int mag;
    private int skipTurn;
    private int rearange;

    private boolean skipNextTurn;//skip next turn?
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
    }public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }public void setHealth(int health) {
        this.health = health;
    }

    public int getDoublePatty() {
        return doublePatty;
    }public void setDoublePatty(int doublePatty) {
        this.doublePatty = doublePatty;
    }

    public int getBandage() {
        return bandage;
    }public void setBandage(int bandage) {
        this.bandage = bandage;
    }
    
    public int getMag() {
        return mag;
    }public void setMag(int mag) {
        this.mag = mag;
    }

    public int getSkipTurn() {
        return skipTurn;
    }public void setSkipTurn(int skipTurn) {
        this.skipTurn = skipTurn;
    }

    public int getRearange() {
        return rearange;
    }public void setRearange(int rearange) {
        this.rearange = rearange;
    }
    public boolean isSkipNextTurn() {//Check if the next turn is skipped
        return skipNextTurn;
    }
    public void setSkipNextTurn(boolean skipNextTurn) {//Set next players turn as skipped
        this.skipNextTurn = skipNextTurn;
    }

    public boolean hasPowerups(){//check if player has any powerups
        return(this.doublePatty +this.skipTurn + this.rearange + this.mag + this.bandage) > 0;
    }

    //initialize powerups
    public void powerUpsInit(int powerUps){
        //until currentPowerups == powerups(desired # of powerups), give a random powerup
        int currentPowerups = 0;
        while(currentPowerups < powerUps){
            //20% chance of getting each powerup, equally fair
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
            currentPowerups++;//powerup given
        }
    }

    //powerups menu
    public void powerUps(patties p, boolean skipTurnOn){
        //If player has powerups, show menu, else give an error message
        if(!this.hasPowerups()){
            Mainthing.type("You have no power-ups left!", 100, "Red");
        }else{
            //use compact mode if more than 3 turns have passed
            if(Mainthing.turnsPassed < 3){
                Mainthing.type("You have " + this.doublePatty + " double patties, ", 40, "Green");
                Mainthing.type(this.skipTurn + " skip turns,", 40, "Green");
                Mainthing.type(this.rearange + " rearranges,", 40, "Green");
                Mainthing.type(this.bandage + " bandages, and", 40, "Green");
                Mainthing.type(this.mag + " magnifying glasses left.", 40, "Green");
                Mainthing.type("Type DP (double patty), ST (skip turn), RA (rearrange)", 30, "Green");
                Mainthing.type("BA (bandage), or MG (magnifying glass)", 30, "Green");
                Mainthing.type("Or just type 'none' to go back", 100, "Green");
                Mainthing.ask("Which power up do you want to use?", "DP,dp,ST,st,RA,ra,BA,ba,MG,mg,none,NONE,give,GIVE");
            //otherwise use default mode
            }else{
                Mainthing.type("You have " + this.doublePatty + " DP, " + this.skipTurn + 
                " ST, " + this.rearange + " RA, " + this.bandage + " BA, " + 
                this.mag + " MG, ", 40, "Green");

                Mainthing.type("type 'none' to go back", 15, "Green");
                Mainthing.ask("Choose an option", "DP,ST,RA,BA,MG,none");
            }
            switch (Mainthing.lastAnswer.toUpperCase()){
                //Check if player has the powerup
                //if player has atleast 1 of the selected powerup, decrement by 1 and activate
                //if player has no more of the selected powerup, show error message

                case "DP"->{
                    if(this.doublePatty <= 0){
                        Mainthing.type("You don't have any double patty power-ups left!", 100, "Red");
                    }else if(Mainthing.doublePattyNextPatty){
                        Mainthing.type("The next patty is already a double patty!", 100, "Red");
                    }else{
                        this.doublePatty -= 1;
                        Mainthing.type("Yummers, a double patty!", 100, "Green");
                        Mainthing.type("Your next Burger will be a double patty!",100, "Green");
                        Mainthing.doublePattyNextPatty = true;
                    }
                }
                case "ST"->{
                    if(this.skipTurn <= 0){
                        Mainthing.type("You don't have any skip turn power-ups left!", 100, "Red");
                    }else{
                        if(skipTurnOn){
                            Mainthing.type("You can only use this powerup once per patty!", 100, "Red");
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
                            //It confuses the ai, but otherwise doesn't have any implications whatsoever
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
                    //incase, for some reason, you decide to use EAT in the powerup menu?
                }case "EAT"->{
                    eatPatty(p);
                }
            }
        }
    }

    public void eatPatty(patties p){
        if(p.isPoisoned(Mainthing.currentPatty)){
            double temp = Math.random();
            String rxn;
            //give a random dialogue if poisoned, each at 25%
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

            //check if double patty (only necessary if poisoned)
            if(Mainthing.doublePattyNextPatty){
                this.health -= 2;
                //prevent from going negative, for asthetics
                if(this.health<0){
                    this.health = 0;
                }
                Mainthing.type(this.getName() + " lost 2 HP",100,"Red");
                Mainthing.type(this.getName() + " has " + this.getHealth() + " HP remaining",100,"Red");
            } else {
                this.health -= 1;
                Mainthing.type(this.getName() + " lost 1 HP",100,"Red");
                Mainthing.type(this.getName() + " has " + this.getHealth() + " HP remaining",100,"Red");
            }
            
        }else{
            Mainthing.type("Mmm... tasty!", 100, "Green");
            Mainthing.type("This patty is safe!", 100, "Green");
            //if math.ranodm is above 0.4 (60% chance), give +1 HP
            if(Math.random()>0.4){
                this.health +=1;
                Mainthing.type(this.getName() + " gained +1 HP!", 100, "Green");
                Mainthing.type(this.getName() + " now has " + this.getHealth() + " HP!", 100, "Green");
            }
        }
        Mainthing.doublePattyNextPatty = false;//reset the next patty
        Mainthing.currentPatty++;//move on to the next patty

        //If out of bounds, reset currentPatty and the tray (platter)
        if ((Mainthing.currentPatty-1) >= p.getPatties().length) {
            Mainthing.type("\n--- All patties finished! Ordering a new round... ---", 100, "Yellow");
            p.setOrder();//refill tray
            Mainthing.currentPatty = 0;//reset
        }
    }

    //make the human (playing against the ai) eat the burger, with dialogue
    public void aiGive(patties p, player human){
        Mainthing.type("Ngl...",100,"CyanOnWhite");
        Mainthing.type("I think you should take the L",100,"CyanOnWhite");
        human.eatPatty(p);
    }

    public void aiEat(patties p, String mainText){
        Mainthing.type(mainText,100,"CyanOnWhite");
        Mainthing.type(this.getName() + " ate the burger",100,"Green");
        this.eatPatty(p);
    }

//This are the methods for the AI decisions
    public void aiTurn(patties p, player rival ){
        int pastPatty = Mainthing.currentPatty;//check if patty is eaten/given (end of turn)
        while(true){
            //check if player or self, is already at 0 HP to prevent "zombie" behaviour and cheating
            if( (this.getHealth() <= 0) || (rival.getHealth() <= 0)){
                return;//end func, the playGame loop will handle the death case
            }
            double probability = poisonProbability(p);
            //chance of patty being poisoned, 0.0 to 0.3 means low, 0.7 to 1.0 is high
            if( (probability > 0.8) && (!rival.isSkipNextTurn() && (this.getSkipTurn() > 0) ) ){
                //Check if next patty is likely to be poisoned, next turn isn't already skipped, and have a skip turn
                this.setSkipTurn(this.getSkipTurn()-1);
                rival.setSkipNextTurn(true);//skip humans turn
                Mainthing.type("Keep the bleachers warm for me rq", 100, "CyanOnWhite");
                //Note: technically this.getName is redundant, since only the AI can use this
                //But if the computer name is ever changed in the Mainthing class, it updates here
                Mainthing.type(this.getName() + " used a skip turn!", 100, "Yellow");
            }
            //This also uses a skip turn, but with different dialogue
            else if(probability > 0.7){
                if((Math.random() > 0.7) && (this.getSkipTurn() > 0) && (!rival.isSkipNextTurn())){
                    this.setSkipTurn(this.getSkipTurn()-1);
                    rival.setSkipNextTurn(true);
                    Mainthing.type("Hello " + rival.getName() + ", Goodbye " + rival.getName(), 100, "CyanOnWhite");
                    Mainthing.type(this.getName() + " used a skip turn!", 100, "Yellow");
                }else{
                    //rearrange the patties (to make it look human)
                    if((this.getRearange() > 0) && (Math.random() < 0.3)){
                        this.setRearange(this.getRearange()-1);//advanced stuff lol
                        Mainthing.type("IDK flip the patties or smth", 100, "CyanOnWhite");
                        Mainthing.type("The patties have been rearranged!", 100, "CyanOnWhite");
                        p.flip();//flip patties
                    }else if( (Math.random()<0.8) && (this.getBandage() > 0) ){
                        this.setBandage(this.getBandage()-1);
                        this.setHealth(this.getHealth()+1);
                        Mainthing.type(this.getName() + " used a bandage and gained +1 HP!", 100, "Green");
                        Mainthing.type(this.getName() + " now has " + this.getHealth() + " HP!", 100, "Green");
                    }else{
                        //give it to the player if no powerups are used
                        aiGive(p, rival);
                    }
                }
            } //if somewhat likely to be poisoned
            else if(probability > 0.6){
                if((Math.random() < 0.5)&&(this.getMag() > 0)){
                    this.setMag(this.getMag()-1);
                    Mainthing.type(this.getName() + " is inspecting the patty...", 100, "Green");
                    Mainthing.type("...", 500, "Red");
                    //check if poisoned and react respectively
                    if(p.isPoisoned(Mainthing.currentPatty)){
                        Mainthing.type("(it's poisoned!)", 100, "Red");
                        if(this.getDoublePatty() > 0){
                            this.setDoublePatty(this.getDoublePatty()-1);
                        }
                        this.
                        aiGive(p, rival);
                    }else{
                        Mainthing.type("(it's safe!)", 100, "Green");
                        aiEat(p, "ight bet");
                    }
                }
                
                //35% chance of eating, since its 50/50
                if(Math.random()>0.65){
                    this.eatPatty(p);
                }else{
                    rival.eatPatty(p);
                }
            }
            //random decisions with custom dialogue for each probability
            else if(probability > 0.5 ){
                if(Math.random()>0.55){
                    aiEat(p, "Screw it, Ima risk it for a biscut");
                }else{
                    aiGive(p, rival);
                }
            }else if(probability > 0.4 ){
                if(Math.random()>0.35){
                    aiEat(p, "Lets js get this over with...");
                }else{
                    aiGive(p, rival);
                }
            }else if(probability > 0.3 ){
                if(Math.random()>0.15){
                    aiEat(p, "Nah this is too easy");
                }else{
                    aiGive(p, rival);
                }
            }else{
                //random and incredibly rare, 5% chance of giving it
                //this is bad, since it is most likely safe and will give +1 HP
                if(Math.random()>0.95){
                    Mainthing.type("Ima give it for funsies lol",100,"CyanOnWhite");
                    rival.eatPatty(p);
                }else{
                    aiEat(p, "Pickles, lettuce, onions,...");
                }
            }
            //unless these two conditions are met, the ai still has a turn left
            if(rival.isSkipNextTurn() || this.isSkipNextTurn()){
                if(Mainthing.currentPatty != pastPatty){
                    rival.setSkipNextTurn(false);
                    this.setSkipNextTurn(false);
                    pastPatty = Mainthing.currentPatty;
                    continue;
                }
            }if(Mainthing.currentPatty == pastPatty){
                continue;
            }
            return;//end computers turn
        }
    }

    //probability of next patty being poisoned
    public double poisonProbability(patties p){
        //count the remaining patties
        double remainingPatties = p.getPatties().length - Mainthing.currentPatty;
        double remainingPoison = 0;

        //count the remaining poisoned patties
        for(int i = Mainthing.currentPatty; i < p.getPatties().length; i++){
            if(p.isPoisoned(i)){
                remainingPoison++;
            }
        }
        //return the raw chance of getting a poisoned patty
        return remainingPoison / remainingPatties;
    }

}