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

    // Display Main Menu
    public int displayMainMenu() {
        System.out.println("\n**********");
        System.out.println("~ Hotel Management System ~");
        System.out.println("[0] Exit the Program");
        System.out.println("[1] Add a Reservation");
        System.out.println("[2] Add a New Guest");
        System.out.println("[3] Check-In a Guest");
        System.out.println("[4] Check-Out a Guest");
        System.out.println("[5] Cancel an Existing Reservation");
        System.out.println("[6] Delete an Existing Guest");
        System.out.println("[7] List All Reservations");
        System.out.println("[8] List All Guests");
        System.out.println("[9] List All Rooms");
        System.out.println();
        System.out.print("Enter Option [#]: ");

        return this.input.nextInt();
    }

    // 1. Display Add Reservation
    public void displayAddReservation() {

        // Catch any exceptions from user input
        try {
            // Display and get Reservation details
            System.out.println(" ~ Add a Reservation ~ ");
            System.out.print("Enter Guest ID: ");
            int guestID = input.nextInt();
            System.out.print("Enter Number of Guests: ");
            int guestNumber = input.nextInt();
            System.out.print("Enter Start State [MM/DD/YY]: ");
            String startDate = input.next();
            System.out.print("Enter End Date [MM/DD/YY]: ");
            String endDate = input.next();
            System.out.println("Types of Rooms: ");
            System.out.println("[1] Single");
            System.out.println("[2] Double");
            System.out.println("[3] Suite");
            System.out.print("Enter Room Type Choice [1/2/3]: ");
            int roomTypeInt = input.nextInt();

            // Display the response from Hotel Manager
            String response = manager.addReservationHelper(guestID, guestNumber, startDate, endDate, roomTypeInt);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }
    }

    // 2. Display Add Guest
    public void displayAddGuest() {

        // Catch any exceptions from user input
        try {
            // Display and get Guest name from the user
            System.out.print("Enter Guest Name: ");
            String guestName = input.next();

            // Display the response from Hotel Manager
            String response = manager.addGuestHelper(guestName);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }
    }

    // 3. Display Check-In Guest
    public void displayCheckInGuest() {

        // Catch any exceptions from user input
        try {
            // Display and get Reservation ID from the user
            System.out.print("Enter Reservation ID: ");
            int reservationID = input.nextInt();

            // Display the response from Hotel Manager
            String response = manager.checkInGuestHelper(reservationID);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }
    }

    // 4. Display Check-Out Guest
    public void displayCheckOutGuest() {

        // Catch any exceptions from user input
        try {
            // Display and get Reservation ID from the user
            System.out.print("Enter Reservation ID: ");
            int reservationID = input.nextInt();

            // Display the response from Hotel Manager
            String response = manager.checkOutGuestHelper(reservationID);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }
    }

    // 5. Display Delete Reservation
    public void displayCancelReservation() {
        // Catch any exceptions from user input
        try {
            // Display and get Reservation ID from the user
            System.out.print("Enter Reservation ID: ");
            int reservationID = input.nextInt();

            // Display the response from Hotel Manager
            String response = manager.cancelReservationHelper(reservationID);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }
    }

    // 6. Display Delete Guest
    public void displayDeleteGuest() {
        // Catch any exceptions from user input
        try {
            // Display and get Guest ID from the user
            System.out.print("Enter Guest ID: ");
            int guestID = input.nextInt();

            // Display the response from Hotel Manager
            String response = manager.deleteGuestHelper(guestID);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Input.");
        }

    }

    public void displayAllReservations() {
        ArrayList<Reservation> reservationList = manager.getReservationList();

        System.out.println("\n**********");
        System.out.println("~ All Reservations at Hotel ~");
        for (Reservation reservation : reservationList) {
            System.out.println("Guest Name: " + reservation.getGuest().getGuestName());
            System.out.println("Reservation ID: " + reservation.getReservationID());
            System.out.println("Check-In Date: " + reservation.getStartDate());
            System.out.println("Check-Out Date: " + reservation.getEndDate());
            System.out.println("Length of Stay: " + reservation.getLengthOfStay());
            System.out.println("Room Type: " + reservation.getRoomType());
            System.out.println("Status: " + reservation.getStatus());
            System.out.println("Total Payment: $" + reservation.getPaymentTotal());
            System.out.println("---");
        }
    }

    public void displayAllGuests() {
        ArrayList<Guest> guestList = manager.getGuestList();

        System.out.println("\n**********");
        System.out.println("~ All Guests at Hotel ~");
        for (Guest guest : guestList) {
            System.out.println("ID: " + guest.getGuestID());
            System.out.println("Guest Name: " + guest.getGuestName());
            System.out.println("Status: " + guest.getGuestStatus());
            System.out.println("---");
        }
    }

    public void displayAllRooms() {
        ArrayList<Room> roomList = manager.getRoomList();

        System.out.println("\n**********");
        System.out.println("~ All Rooms at Hotel ~");
        for (Room room : roomList) {
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Floor Number: " + room.getFloorNumber());
            System.out.println("Rate/Night: $" + room.getRate());
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Available?: " + room.getAvailability());
            System.out.println("Max Capacity: " + room.getMaxCapacity());
            System.out.println("---");
        }
    }
}
