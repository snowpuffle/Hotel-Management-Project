package src.Model;

import java.util.Random;

public class Reservation {
    private static Random random = new Random();
    private Guest guest;
    private Room room;
    private String roomType;
    private String startDate;
    private String endDate;
    private long lengthOfStay;
    private double paymentTotal;
    private int reservationID;
    private String status;
    private int numberOfGuests;

    public Reservation(Guest guest, String roomType, String startDate, String endDate,
            long lengthOfStay, int numberOfGuests) {

        this.guest = guest;
        this.roomType = roomType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lengthOfStay = lengthOfStay;
        this.numberOfGuests = numberOfGuests;

        this.room = null;
        this.status = "Confirmed";
        this.paymentTotal = Payment.calculatePayment(roomType, numberOfGuests);
        this.reservationID = random.nextInt(1000);
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
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
