package src.Model;

public class Room {
    private int roomNumber;
    private int floorNumber;
    private String roomType;
    private double rate;
    private int maxCapacity;
    private Reservation reservation;
    private boolean isAvailable;

    public Room(int roomNumber, int floorNumber, String roomType, int maxCapacity) {
        this.isAvailable = true;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.roomType = roomType;
        this.rate = Payment.getRoomCost(roomType);
        this.maxCapacity = maxCapacity;
        this.reservation = null;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public void setAvailability(boolean setValue) {
        isAvailable = setValue;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRate() {
        return rate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
