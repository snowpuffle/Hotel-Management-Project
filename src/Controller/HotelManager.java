package src.Controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import src.Model.Guest;
import src.Model.Hotel;
import src.Model.Reservation;
import src.Model.Room;
import src.View.HotelView;

public class HotelManager {
    private Scanner input;
    private Hotel hotel;
    private HotelView view;

    public HotelManager(Hotel hotel) {
        this.hotel = hotel;
        this.input = new Scanner(System.in);
    }

    // Initialize Hotel View
    public void setHotelView(HotelView view) {
        this.view = view;
    }

    // Add a Reservation
    public void addReservation() {
        view.displayAddReservation();
    }

    // Add a Reservation Helper
    public int addReservationHelper(int guestID, int guestNumber, String startDate, String endDate) {

        Guest guest = getGuestByID(guestID);
        Room room = findAvailableRoom(guestNumber);
        boolean validDate = isValidDateFormat(startDate) && isValidDateFormat(endDate);
        long lengthOfStay = calculateLengthOfStay(startDate, endDate);

        if (lengthOfStay <= 0) {
            return -1;
        } else if (!validDate) {
            return -2;
        } else if (guest == null) {
            return -3;
        } else if (room == null) {
            return -4;
        } else {
            Reservation reservation = new Reservation(guest, room, startDate, endDate, lengthOfStay);
            hotel.addReservation(reservation);
            room.setAvailability(false);
            return 1;
        }
    }

    // Add a Room
    public void addRoom(int roomNumber, int floorNumber, String roomType, double rate, int maxCapacity) {
        Room room = new Room(roomNumber, floorNumber, roomType, rate, maxCapacity);
        hotel.addRoom(room);
    }

    // Find an Available Room
    public Room findAvailableRoom(int numberOfGuests) {
        for (Room room : hotel.getRoomList()) {
            if (room.getAvailability() == true && room.getMaxCapacity() >= numberOfGuests) {
                return room;
            }
        }
        return null;
    }

    // Add a New Guest to Hotel
    public void addGuest() {
        view.displayAddGuest();
    }

    // Add a Guest Helper
    public void addGuestHelper(String guestName) {
        // Create a new Guest object
        Guest guest = new Guest(guestName);

        // Add Guest to list in Hotel
        hotel.addGuest(guest);
    }

    // Remove an Existing Guest from Hotel
    public void removeGuest() {

        // Get the Guest ID
        System.out.print("Enter Guest ID: ");
        if (input.hasNextInt()) {
            int guestID = input.nextInt();

            // Try to find the Guest using ID
            Guest guest = getGuestByID(guestID);

            // Remove only if the Guest is found
            if (guest == null) {
                System.out.println("ERROR: Cannot Find Guest with ID " + guestID + ".");
            } else {
                hotel.removeGuest(guest);
                System.out.println("SUCCESS: Removed Guest with ID " + guestID + ".");
            }
        } else {
            System.out.println("ERROR: ID is Not Valid.");
        }
    }

    // Get Guest by Guest ID
    public Guest getGuestByID(int guestID) {
        for (Guest guest : hotel.getGuestList()) {
            if (guest.getGuestID() == guestID) {
                return guest;
            }
        }
        return null;
    }

    // Generate Automatic Room Seeds
    public void generateRooms() {
        hotel.addRoom(new Room(101, 1, "Single", 75.0, 1));
        hotel.addRoom(new Room(102, 2, "Double", 100.0, 2));
        hotel.addRoom(new Room(103, 2, "Suite", 200.0, 4));
    }

    // Get List of Reservations from Hotel
    public ArrayList<Reservation> getReservationList() {
        return hotel.getReservationList();
    }

    // Get List of Guests from Hotel
    public ArrayList<Guest> getGuestList() {
        return hotel.getGuestList();
    }

    // Get List of Rooms from Hotel
    public ArrayList<Room> getRoomList() {
        return hotel.getRoomList();
    }

    // ~ Utility Methods ~ //

    // Check if Input is a Valid Date
    public boolean isValidDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Calculate the Length of Stay for a Reservation;
    public long calculateLengthOfStay(String startDate, String endDate) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        try {
            java.util.Date start = sdf.parse(startDate);
            java.util.Date end = sdf.parse(endDate);
            long diff = end.getTime() - start.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (ParseException e) {
            System.out.println("ERROR: Invalid Dates.");
        }
        return 0;
    }

}
