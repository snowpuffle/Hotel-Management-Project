package src.Model;

import java.util.Random;

public class Reservation {
    private static Random random = new Random();
    private Guest guest;
    private Room room;
    private String startDate;
    private String endDate;
    private long lengthOfStay;
    private double paymentTotal;
    private int reservationID;

    public Reservation(Guest guest, Room room, String startDate, String endDate, long lengthOfStay) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lengthOfStay = lengthOfStay;
        this.paymentTotal = room.getRate() * lengthOfStay;
        this.reservationID = random.nextInt(1000);
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getPaymentTotal() {
        return paymentTotal;
    }

    public int getReservationID() {
        return reservationID;
    }

    public long getLengthOfStay() {
        return lengthOfStay;
    }
}
