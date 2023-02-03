package src.Controller;

import src.Model.Hotel;
import src.View.HotelView;

public class Main {
    public static void main(String[] args) {

        // Create and Initialize Objects
        Hotel hotel = new Hotel();
        HotelManager manager = new HotelManager(hotel);
        HotelView view = new HotelView(manager);
        manager.setHotelView(view);

        // Create Room Seeds for Hotel
        manager.generateRooms();

        int option = -1;

        while (option != 0) {
            option = view.displayMainMenu();

            switch (option) {
                case 1:
                    // Add a Reservation
                    break;
                case 2:
                    // Add a New Guest
                    break;
                case 3:
                    // Delete a Reservation
                    break;
                case 4:
                    // Delete an Existing Guest
                    break;
                case 5:
                    // Check-In a Guest
                    break;
                case 6:
                    // Check-Out a Guest
                    break;
                default:
                    break;
            }

        }

        // Start Menu
        manager.addGuest();
        view.displayAllGuests();

        manager.addReservation();
        view.displayAllReservations();

        view.displayAllRooms();
    }

}
