package src.Model;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private ArrayList<Guest> guestList;

    // Class Constructor
    public Hotel() {
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        this.guestList = new ArrayList<Guest>();
    }

    // 1. Add Reservation to Array List
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    // 2. Add Guest to Array List
    public void addGuest(Guest guest) {
        guestList.add(guest);
    }

    // 6. Delete Guest from Array List
    public void removeGuest(Guest guest) {
        guestList.remove(guest);
    }

    // Add Room to Array List
    public void addRoom(Room room) {
        roomList.add(room);
    }

    // Get Guest Array List
    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    // Get Room List
    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    // Get Reservation List
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

}