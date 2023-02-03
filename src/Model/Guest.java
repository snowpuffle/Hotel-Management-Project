package src.Model;

import java.util.Random;

public class Guest {
    private static Random random = new Random();
    private String guestName;
    private int guestID;

    public Guest(String guestName) {
        this.guestName = guestName;
        this.guestID = random.nextInt(1000);
    }

    public String getGuestName() {
        return guestName;
    }

    public int getGuestID() {
        return guestID;
    }

}
