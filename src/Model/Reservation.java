package src.Model;

import java.util.Random;

public class Reservation {
    private static Random random = new Random();
    private Guest reservationGuest;
    private Room reservationRoom;
    private String reservationRoomType;
    private String reservationStartDate;
    private String reservationEndDate;
    private long reservationLength;
    private double reservationPaymentTotal;
    private int reservationID;
    private String reservationStatus;
    private int numberOfGuests;

    public Reservation(Guest guest, String roomType, String startDate, String endDate,
            long lengthOfStay, int numberOfGuests) {

        this.reservationGuest = guest;
        this.reservationRoomType = roomType;
        this.reservationStartDate = startDate;
        this.reservationEndDate = endDate;
        this.reservationLength = lengthOfStay;
        this.numberOfGuests = numberOfGuests;

        this.reservationRoom = null;
        this.reservationStatus = "Confirmed";
        this.reservationPaymentTotal = Payment.calculatePayment(roomType, lengthOfStay);
        this.reservationID = random.nextInt(1000);
    }

    public Guest getGuest() {
        return reservationGuest;
    }

    public Room getRoom() {
        return reservationRoom;
    }

    public void setRoom(Room room) {
        this.reservationRoom = room;
    }

    public String getStatus() {
        return reservationStatus;
    }

    public void setStatus(String status) {
        this.reservationStatus = status;
    }

    public String getRoomType() {
        return reservationRoomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getStartDate() {
        return reservationStartDate;
    }

    public String getEndDate() {
        return reservationEndDate;
    }

    public double getPaymentTotal() {
        return reservationPaymentTotal;
    }

    public int getReservationID() {
        return reservationID;
    }

    public long getLengthOfStay() {
        return reservationLength;
    }
}
