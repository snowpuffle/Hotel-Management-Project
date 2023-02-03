package src.Controller;

import src.Model.Hotel;
import src.View.HotelView;

public class Main {
    public static void main(String[] args) {

        // Create & Initialize Objects
        Hotel hotel = new Hotel();
        HotelManager manager = new HotelManager(hotel);
        HotelView view = new HotelView(manager);

        // Initialize & Generate Room Seeds
        manager.setHotelView(view);
        manager.generateRooms();

        int option = -1;
        while (option != 0) {
            option = view.displayMainMenu();

            switch (option) {
                case 1:
                    // Add a Reservation
                    manager.addReservation();
                    break;
                case 2:
                    // Add a New Guest
                    manager.addGuest();
                    break;
                case 3:
                    // Check-In a Guest
                    manager.checkInGuest();
                    break;
                case 4:
                    // Check-Out a Guest
                    manager.checkOutGuest();
                    break;
                case 5:
                    // Delete a Reservation
                    manager.cancelReservation();
                    break;
                case 6:
                    // Delete an Existing Guest
                    manager.deleteGuest();
                    break;
                case 7:
                    // List All Reservations
                    view.displayAllReservations();
                    break;
                case 8:
                    // List All Guests
                    view.displayAllGuests();
                    break;
                case 9:
                    // List All Rooms
                    view.displayAllRooms();
                    break;
                default:
                    break;
            }
        }
    }

}
