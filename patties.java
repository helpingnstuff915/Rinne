public class patties{
    private int[] order;
    public patties(int pattyNum, int liveNum){
        this.order = new int[pattyNum]; // Assuming pattyNum patties in the order
            int live = 0;
            while (live < liveNum) {
                int randomIndex = (int)(Math.random() * pattyNum); // Randomly select an index
                if (order[randomIndex] == 0) {
                    order[randomIndex] = 1;
                    // 1 means live, 0 means a blank
                    live++; // Increment the count of live patties
                }
            }
            // for (int patty : order) {
            //     if(live >= liveNum){
            //         order[patty] = 0; // Mark as dead
            //     } else {
            //         order[patty] = 1; // Mark as alive
            //         live++;
            //     }
            //     order[i] = (int)(Math.random() * liveNum) + 1; // Randomly assign a live number to each patty
            // }
    }

    public int[] getPatties() {
        return order;
    }
}