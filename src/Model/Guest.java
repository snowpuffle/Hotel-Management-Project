package src.Model;

import java.util.Random;

public class Guest {
    private static Random random = new Random();
    private String guestStatus;
    private String guestName;
    private int guestID;

    public Guest(String guestName, String guestStatus) {
        this.guestName = guestName;
        this.guestStatus = guestStatus;
        this.guestID = random.nextInt(1000);
    }

    public String getGuestStatus() {
        return guestStatus;
    }

    public void setGuestStatus(String guestStatus) {
        this.guestStatus = guestStatus;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getGuestID() {
        return guestID;
    }

}
