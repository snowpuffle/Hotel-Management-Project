package src;

import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);
    // Hotel hotel;

    public Menu() {
        // this.hotel = hotel;
    }

    public void initializeMenu() {
        System.out.println("Hotel Management System");
        System.out.println("[1] Reserve a Room");
        System.out.println("[2] Show Empty Rooms");
        System.out.println("[3] Remove Reservations for a Room");
        System.out.println("[4] Generate Reservation Report");
        System.out.println("[5] Exit Program");

        do {
            System.out.print("Select one of the options from above: ");
            int option = input.nextInt();
            if (option == 5) {
                break;
            }
            chooseOptionFromMenu(option);
        } while (true);

    }

    public void chooseOptionFromMenu(int option) {
        switch (option) {
            case 1: {
                // Reserve a Room
                reserveRoom();
                break;
            }
            case 2: {
                // Show Empty Rooms
                showEmptyRooms();
                break;
            }
            case 3: {
                // Remove Reservations for a Room
                removeReservationsForARoom();
                break;
            }
            case 4: {
                // Generate Reservation Report
                generateReservationReport();
                break;
            }
        }
    }

    private void reserveRoom(){
        
    }

    private void showEmptyRooms(){
        
    }

    private void removeReservationsForARoom(){
        
    }

    private void generateReservationReport(){

    }


}