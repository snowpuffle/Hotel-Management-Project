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

    /* ~ Class Constructor ~ */
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
    public String addReservationHelper(int guestID, int guestNumber, String startDate, String endDate,
            int roomTypeInt) {

        // Find the Guest by ID
        Guest guest = findGuestByID(guestID);

        // Find the String for Room type
        String roomTypeString = getRoomType(roomTypeInt);

        // Find an available Room with matching number of guests and room type
        Room room = findAvailableRoom(guestNumber, roomTypeString);

        // Check if the dates are valid
        boolean validDate = isValidDateFormat(startDate) && isValidDateFormat(endDate);

        // Calculate the length of stay
        long lengthOfStay = calculateLengthOfStay(startDate, endDate);

        // Continue if all variables are valid
        if (lengthOfStay <= 0 || !validDate) {
            return "ERROR: Invalid Dates.";
        } else if (guest == null) {
            return "ERROR: Invalid Guest ID.";
        } else if (guest.getGuestStatus() == "Deleted") {
            return "ERROR: Cannot Add Reservation to a Deleted Guest.";
        } else if (room == null) {
            return "ERROR: Cannot Add Reservation - No Rooms Avaiable.";
        } else {
            // Create a new Reservation and add to Hotel list
            Reservation reservation = new Reservation(guest, room.getRoomType(),
                    startDate, endDate, lengthOfStay, guestNumber);
            hotel.addReservation(reservation);
            return "SUCCESS: Reservation Added.";
        }
    }

    // 2. Add a New Guest to Hotel
    public void addGuest() {
        view.displayAddGuest();
    }

    // 2. Add a Guest Helper
    public String addGuestHelper(String guestName) {

        // Create a new Guest object
        Guest guest = new Guest(guestName, "Future Guest");

        // Add Guest to the Hotel list
        hotel.addGuest(guest);
        return "SUCCESS: Added Guest to Hotel.";
    }

    // 3. Check-In a Guest
    public void checkInGuest() {
        // View Display
        view.displayCheckInGuest();
    }

    // 3. Check-In a Guest Helper
    public String checkInGuestHelper(int reservationID) {

        // Find Reservation using ID
        Reservation reservation = findReservationByID(reservationID);

        // Continue if a Reservation is found
        if (reservation != null && reservation.getStatus() == "Confirmed") {
            // Get Guest from Reservation
            Guest guest = reservation.getGuest();
            Room room = findAvailableRoom(reservation.getNumberOfGuests(), reservation.getRoomType());
            if (room != null && guest != null) {
                guest.setGuestStatus("Active");
                room.setAvailability(false);
                reservation.setRoom(room);
                reservation.setStatus("Active");
                return "SUCCESS: Guest is Checked-In.";
            }
            return "ERROR: No Rooms Available.";
        } else {
            return "ERROR: Problem with Reservation ID.";
        }
    }

    // 4. Check-Out a Guest
    public void checkOutGuest() {
        view.displayCheckOutGuest();
    }

    // 4. Check-Out a Guest Helper
    public String checkOutGuestHelper(int reservationID) {
        // Find Reservation using ID
        Reservation reservation = findReservationByID(reservationID);

        if (reservation != null) {
            Room room = reservation.getRoom();
            Guest guest = reservation.getGuest();
            if (reservation.getStatus() == "Active" && room != null && guest != null) {
                guest.setGuestStatus("Past Guest");
                room.setAvailability(true);
                reservation.setStatus("Past");
                return "SUCCESS: Guest is Checked-Out.";
            }
            // Cannot Find Room in Reservation
            return "ERROR: Problem with Room.";
        } else {
            // Cannot Find Active Reservation
            return "ERROR: Cannot Find Active Reservation.";
        }
    }

    // 5. Delete a Reservation
    public void cancelReservation() {
        view.displayCancelReservation();
    }

    // 5. Delete a Reservation Helper
    public String cancelReservationHelper(int reservationID) {

        // Find the Reservation by ID
        Reservation reservation = findReservationByID(reservationID);

        // Continue if Reservation exists
        if (reservation == null) {
            return "ERROR: Cannot Find Reservation.";
        } else if (reservation.getStatus() == "Active") {
            return "ERROR: Cannot Cancel an Active Reservation.";
        } else if (reservation.getStatus() == "Cancelled") {
            return "ERROR: Reservation Already Cancelled.";
        } else {
            reservation.setStatus("Cancelled");
            return "SUCCESS: Reservation is Cancelled.";
        }
    }

    // 6. Delete an Existing Guest
    public void deleteGuest() {
        view.displayDeleteGuest();
    }

    // 6. Delete an Existing Guest Helper
    public String deleteGuestHelper(int guestID) {
        // Try to find the Guest using ID
        Guest guest = findGuestByID(guestID);

        // Remove only if the Guest is found
        if (guest == null) {
            return "ERROR: Cannot Find Guest with ID " + guestID + ".";
        } else if (guest.getGuestStatus() == "Active") {
            return "ERROR: Cannot Delete an Active Guest.";
        } else {
            guest.setGuestStatus("Deleted");
            return "SUCCESS: Removed Guest with ID " + guestID + ".";
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
    public Room findAvailableRoom(int numberOfGuests, String roomTypeString) {

        if (roomTypeString != null) {
            for (Room room : hotel.getRoomList()) {
                if (room.getAvailability() == true && room.getMaxCapacity() >= numberOfGuests
                        && room.getRoomType() == roomTypeString) {
                    return room;
                }
            }
            return null;
        } else {
            return null;
        }
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

    // Get Room Type
    public String getRoomType(int roomTypeInt) {
        if (roomTypeInt == 1) {
            return "Single";
        } else if (roomTypeInt == 2) {
            return "Double";
        } else if (roomTypeInt == 3) {
            return "Suite";
        } else {
            return null;
        }
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
