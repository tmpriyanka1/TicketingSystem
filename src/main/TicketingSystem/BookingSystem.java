package main.TicketingSystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.TicketingSystem.models.*;

public class BookingSystem {
    private static List<Booking> bookings = new ArrayList<>();
    private static  List<Show> shows = new ArrayList<>();
    private static Scanner input;
    public static void main(String args[]) throws ParseException {
       input = new Scanner(System.in);

        while(true) {
            System.out.println("Select the option");
            System.out.println("1. Add Show");
            System.out.println("2. List of shows");
            System.out.println("3. List of booking");

            switch(input.nextInt()) {
                case 1:
                    shows.add(createShow(input));
                    break;
                case 2:
                    for(int i = 1; i<=shows.size(); i++) {
                        System.out.println(i + " " +shows.get(i-1).getShowName());
                    }
                    System.out.println("select the show to see the details or N to go to main menu");

                    String selection = input.next();
                    if(!selection.toLowerCase().equals("n")) {
                        showDetailsDisplay(shows.get(Integer.parseInt(selection) -1), input);
                    }
            }
        }
    }

    private static void showDetailsDisplay(Show show, Scanner input)
    {
        if(show.getShowDetailsList().size() == 0) {
            System.out.println("No Shows available");
            return;
        }

        show.displayShowDetails();
        System.out.println("Select the show details to book or type N to got to main menu");
        String selection = input.next();
        if(!selection.toLowerCase().equals("n")) {
            bookTickets(show.getShowDetailsByIndex(Integer.parseInt(selection) -1));

        }
    }

    private static void bookTickets(ShowDetails showDetails) {
        System.out.println("Enter number of tickets");

        int noOfTickets = input.nextInt();

        List<Booking> bookingForTheSameShow = bookings.stream().filter((b) -> b.getShowDetails().getShowDetailsId() == showDetails.getShowDetailsId())
                .collect(Collectors.toList());

        if(!showDetails.showAvailabilities(bookingForTheSameShow)) {
            System.out.println("No seat available sorry");
            return;
        }

        System.out.println("Select the seat number or N to cancel");
        String selection = input.next();

        if(!selection.toLowerCase().equals("n")) {

            List<Ticket> tickets = new ArrayList<>();
            for(int i = 0; i<noOfTickets; i++) {
                int seatSelection = input.nextInt();
                Seat seat = showDetails.getAvailableSeatByIndex(seatSelection-1);
                tickets.add(new Ticket(seat));
            }

            System.out.println("Enter user email");
            String email = input.next();

            System.out.println("Enter user phone number");
            String phone = input.next();

            Booking booking = new Booking(email, phone, showDetails, tickets);

            System.out.println("Do you wish to confirm the booking y/n");

            selection = input.next();

            if(selection.toLowerCase().equals("y")) {
                booking.confirmBooking(input);

                bookings.add(booking);
            }
        }
    }

    private static Show createShow(Scanner input) throws ParseException {
        System.out.println("Enter the show name");

        Show show = new Show(input.next());

        System.out.println("Do you wish to add show details (Y/N)");

        if(input.next().toLowerCase().equals("y")) {
            show.addShowDetails(createShowDetails(input));
        }
        return show;
    }

    private static ShowDetails createShowDetails(Scanner input) throws ParseException {
        System.out.println("Inter show date and time MM/DD/YYYY.HH:MM");
        String dateString = input.next();
        //System.out.println(dateString);

        DateFormat formatter = new SimpleDateFormat("MM/DD/YYYY.HH:MM");
        Date date = formatter.parse(dateString);

        System.out.println("Enter the price of each ticket");

        double price = input.nextDouble();

        System.out.println("Enter the number of rows for the show");
        int rows = input.nextInt();

        System.out.println("Enter the number of seats in each row");
        int seatCount = input.nextInt();

        List<Seat> seats = new ArrayList<>();
        for(int i = 0; i<rows; i++) {
            for(int j = 1; j<=seatCount; j++) {
                seats.add(new Seat((char) (65+i), j));
            }
        }

        ShowDetails showDetails = new ShowDetails(date, new Layout(seats), price);

        return showDetails;
    }
}
