public class patties{
    private int[] order;//The array that holds the position of the safe and poisoned patties
    private int poisoned;//number of poisoned patties
    private int pattyNum;//numberf of patties

    public patties(int pattyNum, int poisoned){
        this.poisoned = poisoned;
        this.pattyNum = pattyNum;
        order = new int[pattyNum];

    }

    public void setPattyNum(int pattyNum) {
        this.pattyNum = pattyNum;
        order = new int[pattyNum];
    }

    public void setPoisoned(int poisoned) {
        this.poisoned = poisoned;
    }public int getPoisoned() {
        return poisoned;
    }

    //give the patties tray
    public int[] getPatties() {
        return order;
    }

    public boolean isPoisoned(int currentPatty){
        return order[Mainthing.currentPatty] != 0;
    }

    //set the order of the patties (by initial setup or rearrange powerup)
    public void setOrder() {
            java.util.Arrays.fill(order, 0); // re-initialize all array values
            int filled = 0;

            while (filled < poisoned) {
                int randomIndex = (int)(Math.random() * pattyNum); // Randomly select an index
                if (order[randomIndex] == 0) {
                    order[randomIndex] = 1;
                    // 1 means live, 0 means a blank
                    filled++; // Increment the count of live patties
                }
            }
    }

    public void flip(){//flip the patties lol (reverses order of array)
        // int first = Mainthing.currentPatty;
        // int last = order.length-1;

        // while(first < last){
        //     int test = order[first];
        //     order[first] = order[last];
        //     order[last] = test;
        //     first++;
        //     last++;
        // }
        for (int i = 0; i < order.length / 2; i++) {
            int temp = order[i];
            order[i] = order[order.length - 1 - i];
            order[order.length - 1 - i] = temp;
        }
    }
}