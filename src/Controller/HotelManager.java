package src.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import src.Model.Guest;
import src.Model.Hotel;
import src.Model.Reservation;
import src.Model.Room;
import src.View.HotelView;

public class HotelManager {
    private Hotel hotel;
    private HotelView view;

    public HotelManager(Hotel hotel) {
        this.hotel = hotel;
    }

    // Initialize Hotel View
    public void setHotelView(HotelView view) {
        this.view = view;
    }

    /* ~ Management Methods ~ */
    // 1. Add a Reservation
    public void addReservation() {
        view.displayAddReservation();
    }

    // 1. Add a Reservation Helper
    public int addReservationHelper(int guestID, int guestNumber, String startDate, String endDate) {

        Guest guest = findGuestByID(guestID);
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
            Reservation reservation = new Reservation(guest, room.getRoomType(),
                    startDate, endDate, lengthOfStay, guestNumber);
            hotel.addReservation(reservation);
            return 1;
        }
    }

    // 2. Add a New Guest to Hotel
    public void addGuest() {
        view.displayAddGuest();
    }

    // 2. Add a Guest Helper
    public void addGuestHelper(String guestName) {
        // Create a new Guest object
        Guest guest = new Guest(guestName);

        // Add Guest to list in Hotel
        hotel.addGuest(guest);
    }

    // 3. Check-In Guest
    public void checkInGuest() {
        // View Display
        view.displayCheckInGuest();
    }

    // 3. Check-In Guest Helper
    public int checkInGuestHelper(int reservationID) {
        // Find Reservation using ID
        Reservation reservation = findReservationByID(reservationID);

        if (reservation != null) {
            Room room = findAvailableRoom(reservation.getNumberOfGuests());
            if (reservation.getStatus() == "Confirmed" && room != null)
                if (room != null) {
                    room.setAvailability(false);
                    reservation.setRoom(room);
                    reservation.setStatus("Active");
                    return 1;
                }
            return -1;
        } else {
            return -2;
        }
    }

    // 4. Check-Out Guest
    public void checkOutGuest() {
        view.displayCheckOutGuest();
    }

    // 4. Check-Out Guest Helper
    public int checkOutGuestHelper(int reservationID) {
        // Find Reservation using ID
        Reservation reservation = findReservationByID(reservationID);

        if (reservation != null) {
            Room room = reservation.getRoom();
            if (reservation.getStatus() == "Active" && room != null) {
                room.setAvailability(true);
                reservation.setStatus("Past");
                return 1;
            }
            // Cannot Find Room in Reservation
            return -1;
        } else {
            // Cannot Find Active Reservation
            return -2;
        }
    }

    // 5. Delete a Reservation
    public void deleteReservation() {
        view.displayDeleteReservation();
    }

    // 5. Delete a Reservation Helper
    public int deleteReservationHelper(int reservationID) {

        Reservation reservation = findReservationByID(reservationID);

        if (reservation != null) {
            hotel.removeReservation(reservation);
            return 1;
        } else {
            return -1;
        }
    }

    // 6. Delete an Existing Guest
    public void deleteGuest() {
        view.displayDeleteGuest();
    }

    // 6. Delete an Existing Guest Helper
    public int deleteGuestHelper(int guestID) {
        // Try to find the Guest using ID
        Guest guest = findGuestByID(guestID);

        // Remove only if the Guest is found
        if (guest != null) {
            hotel.removeGuest(guest);
            return 1;
        } else {
            return -1;
        }
    }

    // Add a Room
    public void addRoom(int roomNumber, int floorNumber, String roomType, int maxCapacity) {
        Room room = new Room(roomNumber, floorNumber, roomType, maxCapacity);
        hotel.addRoom(room);
    }

    /* ~ Getter Methods ~ */
    // 7. Get List of Reservations from Hotel
    public ArrayList<Reservation> getReservationList() {
        return hotel.getReservationList();
    }

    // 8. Get List of Guests from Hotel
    public ArrayList<Guest> getGuestList() {
        return hotel.getGuestList();
    }

    // 9. Get List of Rooms from Hotel
    public ArrayList<Room> getRoomList() {
        return hotel.getRoomList();
    }

    /* ~ Helper Methods ~ */
    // Find an Available Room by Number of Guests
    public Room findAvailableRoom(int numberOfGuests) {
        for (Room room : hotel.getRoomList()) {
            if (room.getAvailability() == true && room.getMaxCapacity() >= numberOfGuests) {
                return room;
            }
        }
        return null;
    }

    // Find Reservation by ID
    public Reservation findReservationByID(int reservationID) {
        for (Reservation reservation : hotel.getReservationList()) {
            if (reservation.getReservationID() == reservationID) {
                return reservation;
            }
        }
        return null;
    }

    // Find Guest by Guest ID
    public Guest findGuestByID(int guestID) {
        for (Guest guest : hotel.getGuestList()) {
            if (guest.getGuestID() == guestID) {
                return guest;
            }
        }
        return null;
    }

    // Generate Automatic Room Seeds
    public void generateRooms() {
        hotel.addRoom(new Room(101, 1, "Single", 1));
        hotel.addRoom(new Room(102, 2, "Double", 2));
        hotel.addRoom(new Room(103, 2, "Suite", 4));
    }

    /* ~ Utility Methods ~ */
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
