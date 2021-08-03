package main.TicketingSystem;

import java.text.ParseException;
import java.util.*;

import main.TicketingSystem.manager.BookingManager;
import main.TicketingSystem.manager.ShowManager;
import main.TicketingSystem.models.*;

public class BookingSystem {
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner input;
    public static void main(String args[]) throws ParseException {
        input = new Scanner(System.in);
        ShowManager showManager = new ShowManager(input);
        BookingManager bookingManager = new BookingManager(input);
        while(true) {
            System.out.println("Select the option");
            System.out.println("1. List of shows");
            System.out.println("2. List of booking");

            switch(input.nextInt()) {
                case 1:
                    showManager.displayShows();
                    break;
                case 2:
                    bookingManager.displayListOfBookings();
                    break;
            }
        }
    }
}
