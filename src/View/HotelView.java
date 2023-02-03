package src.View;

import java.util.ArrayList;
import java.util.Scanner;

import src.Controller.HotelManager;
import src.Model.Guest;
import src.Model.Reservation;
import src.Model.Room;

public class HotelView {

    private Scanner input;
    private HotelManager manager;

    public HotelView(HotelManager manager) {
        this.manager = manager;
        this.input = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("**********");
        System.out.println("~ Hotel Management System ~");
        System.out.println("[0] Exit the Program");
        System.out.println("[1] Add a Reservation");
        System.out.println("[2] Add a New Guest");
        System.out.println("[3] Check-In a Guest");
        System.out.println("[4] Check-Out a Guest");
        System.out.println("[5] Delete a Reservation");
        System.out.println("[6] Delete a Guest");
        System.out.println("[7] List All Reservations");
        System.out.println("[8] List All Rooms");
        System.out.println("[9] List All Guests");

        System.out.print("\nEnter your choice: ");

        return this.input.nextInt();
    }

    public void displayAddReservation() {
        System.out.print("Enter Guest ID: ");
        int guestID = input.nextInt();
        System.out.print("Enter Number of Guests: ");
        int guestNumber = input.nextInt();
        System.out.print("Enter Start State [MM/DD/YY]: ");
        String startDate = input.next();
        System.out.print("Enter End Date [MM/DD/YY]: ");
        String endDate = input.next();

        int responseCode = manager.addReservationHelper(guestID, guestNumber, startDate, endDate);

        if (responseCode == -1) {
            System.out.println("ERROR: Cannot Add Reservation - Invalid Length of Stay.");
        } else if (responseCode == -2) {
            System.out.println("ERROR: Cannot Add Reservation - Invalid Date.");
        } else if (responseCode == -3) {
            System.out.println("ERROR: Cannot Add Reservation - Invalid Guest ID.");
        } else if (responseCode == -4) {
            System.out.println("ERROR: Cannot Add Reservation - No Rooms Avaiable.");
        } else {
            System.out.println("SUCCESS: Reservation Added.");
        }
    }

    public void displayAddGuest() {
        // Get the guest name
        System.out.print("Enter Guest Name: ");
        String guestName = input.nextLine();

        manager.addGuestHelper(guestName);
    }

    public void displayAllReservations() {
        ArrayList<Reservation> reservationList = manager.getReservationList();

        System.out.println("**********");
        System.out.println("~ All Reservations at Hotel ~");
        for (Reservation reservation : reservationList) {
            System.out.println("Guest Name: " + reservation.getGuest().getGuestName());
            System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
            System.out.println("Check-In Date: " + reservation.getStartDate());
            System.out.println("Check-Out Date: " + reservation.getEndDate());
            System.out.println("Length of Stay: " + reservation.getLengthOfStay());
            System.out.println("Total Payment: $" + reservation.getPaymentTotal());
        }
    }

    public void displayAllGuests() {
        ArrayList<Guest> guestList = manager.getGuestList();

        System.out.println("**********");
        System.out.println("~ All Guests at Hotel ~");
        for (Guest guest : guestList) {
            System.out.println("Guest Name: " + guest.getGuestName() + " ID: " + guest.getGuestID());
        }
    }

    public void displayAllRooms() {
        ArrayList<Room> roomList = manager.getRoomList();

        System.out.println("**********");
        System.out.println("~ All Reservations at Hotel ~");
        for (Room room : roomList) {
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Floor Number: " + room.getFloorNumber());
            System.out.println("Rate/Night: $" + room.getRate());
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Available?: " + room.getAvailability());
            System.out.println("Max Capacity?: " + room.getMaxCapacity());
            System.out.println("---");
        }
    }
}
