package main.TicketingSystem.manager;

import main.TicketingSystem.db.BookingDB;
import main.TicketingSystem.models.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingManager {
  private Scanner input;
  private SeatManager seatManager;
  private TicketManager ticketManager;

  public BookingManager(Scanner input) {
    this.input = input;
    this.seatManager = new SeatManager(input);
    this.ticketManager = new TicketManager(input);
  }

  public void initiateBooking(UUID showDetailsId, double price) {
    System.out.println("Enter the number of seats");
    int noOfTickets = input.nextInt();

    List<Seat> availableSeats = seatManager.getAvailableSeatsByShowDetailsId(showDetailsId);

    if(availableSeats.size() < noOfTickets) {
      System.out.println("Sorry! no seat available");
      return;
    }

    System.out.println("Select the seat number or N to cancel");
    String selection = input.next();

    UUID bookingId = UUID.randomUUID();
    if(!selection.toLowerCase().equals("n")) {

      int seatSelection = Integer.parseInt(selection);
      List<Ticket> tickets = new ArrayList<>();
      for(int i = 0; i<noOfTickets; i++) {
        if(i != 0) {
          System.out.println("Enter the next seat number");
          seatSelection = input.nextInt();
        }
        Seat seat = availableSeats.get(seatSelection-1);
        seat.isReserved(true);
        seatManager.reservedSeat(seat.getSeatId());
        tickets.add(new Ticket(seat.getSeatId(), price, bookingId));
      }

      System.out.println("Enter user email");
      String email = input.next();

      System.out.println("Enter user phone number");
      String phone = input.next();

      Booking booking = new Booking(bookingId, email, phone, showDetailsId);

      System.out.println("Do you wish to confirm the booking y/n");

      selection = input.next();

      if(selection.toLowerCase().equals("y")) {

        confirmBooking(booking, tickets);
      }
      else {
        for(Ticket ticket : tickets) {
          seatManager.releaseSeat(ticket.getSeatId());
        }
      }
    }

  }

  private void confirmBooking(Booking booking, List<Ticket> tickets) {

    System.out.println("Enter card number");
    booking.setCardNumber(input.next());

    System.out.println("Card holder name");
    booking.setCardHoldersName(input.next());

    booking.setPaymentType(PaymentType.CREDIT_CARD);
    booking.setBookingStatus(BookingStatus.CONFIRMED);

    BookingDB.insertBooking(booking);
    ticketManager.createTickets(tickets);
    System.out.println("Booking Status is Confirmed");
  }

  public void  displayListOfBookings()
  {
    System.out.println("Enter user email");
    String email = input.next();
    List<BookingDetails> bookingDetailsList =  BookingDB.getListOfBookings(email);

    if(bookingDetailsList.size() == 0)
    {
      System.out.println("No bookings available");
    }
    if(!bookingDetailsList.isEmpty()) {
      for (int i = 1; i <= bookingDetailsList.size(); i++) {
        BookingDetails bookingDetails = bookingDetailsList.get(i-1);
        System.out.println(i + ". " + bookingDetails.getShow().getShowName() + " - " +bookingDetails.getShowDetails().getShowTime().toLocaleString() + " - " + bookingDetails.getBooking().getBookingStatus().toString());
      }

      System.out.println("Select the booking to see the details or N to go back to main menu");
      System.out.println("------------------------------------------------------------------");
      String selection = input.next();

      if (selection.toLowerCase().equals("n")) {
        return;
      }

      displayBookingDetails(bookingDetailsList.get(Integer.parseInt(selection)-1).getBooking());

    }
  }

  private void displayBookingDetails(Booking booking) {
    List<TicketDetails> ticketDetailsList = ticketManager.getTicketDetailsByBookingId(booking.getBookingId());

    for(int i = 0; i<ticketDetailsList.size(); i++) {
      TicketDetails ticketDetails = ticketDetailsList.get(i);

      System.out.println((i+1)+". "+ticketDetails.getSeat().getSeatValue() +" - $"+ticketDetails.getTicket().getPrice() + " - "+ ticketDetails.getTicket().getTicketStatus().toString());
    }

    cancelBooking(ticketDetailsList, booking);
  }

  private void cancelBooking(List<TicketDetails> ticketDetailsList, Booking booking) {
    List<TicketDetails> confirmedTicketDetailsList = ticketDetailsList.stream().filter(ticketDetails -> ticketDetails.getTicket().getTicketStatus() == TicketStatus.CONFIRMED).collect(Collectors.toList());

    if(confirmedTicketDetailsList.isEmpty()) {
      return;
    }

    System.out.println("Press Y to cancel the booking or N to go back to main menu");
    System.out.println("----------------------------------------------------------");
    String selection = input.next();
    if (!selection.toLowerCase().equals("y")) {
      return;
    }

    System.out.println("Enter the tickets to cancel from below list");
    for(int i = 0; i<confirmedTicketDetailsList.size(); i++) {
      System.out.println((i+1) + ". " + confirmedTicketDetailsList.get(i).getSeat().getSeatValue());
    }

    boolean shouldDelete = true;
    List<TicketDetails> ticketDetailsListToCancel = new ArrayList<>();

    selection = input.next();
    while(ticketDetailsListToCancel.size() < confirmedTicketDetailsList.size() && shouldDelete) {
      shouldDelete = false;
      TicketDetails ticketDetails = confirmedTicketDetailsList.get(Integer.parseInt(selection)-1);

      ticketDetailsListToCancel.add(ticketDetails);

      if(ticketDetailsListToCancel.size() < confirmedTicketDetailsList.size()) {
        System.out.println("Select other seat to cancel or n to continue");
        selection = input.next();

        if (!selection.toLowerCase().equals("n")) {
          shouldDelete = true;
        }
      }
    }

    ticketManager.cancelTickets(ticketDetailsListToCancel);

    if(ticketDetailsListToCancel.size() == confirmedTicketDetailsList.size()) {
      booking.setBookingStatus(BookingStatus.CANCELLED);
    }
    else {
      booking.setBookingStatus(BookingStatus.PARTIALLY_CANCELLED);
    }

    BookingDB.updateStatus(booking);

    System.out.println("Booking is cancelled successfully, refund will be issued to the card used at purchase");
  }
}
