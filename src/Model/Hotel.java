package src.Model;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private ArrayList<Guest> guestList;

    public Hotel() {
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        this.guestList = new ArrayList<Guest>();

        // generateRooms();
    }

    // Add Room to Array List
    public void addRoom(Room room) {
        roomList.add(room);
    }

    // Add Guest to Array List
    public void addGuest(Guest guest) {
        guestList.add(guest);
    }

    // Remove Guest from Array List
    public void removeGuest(Guest guest) {
        guestList.remove(guest);
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

    // public void addRoom(Room room) {
    // rooms.add(room);
    // }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    // public void printRooms() {
    // System.out.println("Rooms in hotel:");
    // for (Room room : rooms) {
    // System.out.println("Room Number: " + room.getRoomNumber());
    // System.out.println("Room Type: " + room.getRoomType());
    // System.out.println("Room Rate: $" + room.getRate());
    // System.out.println();
    // }
    // }

    // public void printReservations() {
    // System.out.println("Reservations in hotel:");
    // 
    // }

}