package main.TicketingSystem.manager;

import main.TicketingSystem.db.TicketDB;
import main.TicketingSystem.models.Ticket;
import main.TicketingSystem.models.TicketDetails;
import main.TicketingSystem.models.TicketStatus;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TicketManager {
  private Scanner input;
  private SeatManager seatManager;

  public TicketManager(Scanner input) {
    this.input = input;
    this.seatManager = new SeatManager(input);
  }

  public void createTickets(List<Ticket> tickets) {
    for(Ticket ticket: tickets) {
      ticket.setTicketStatus(TicketStatus.CONFIRMED);
      TicketDB.createTicket(ticket);
    }
  }

  public List<TicketDetails> getTicketDetailsByBookingId(UUID bookingID) {
    return TicketDB.getTicketDetailsByBookingID(bookingID.toString());
  }

  public void cancelTickets(List<TicketDetails> ticketDetailsList) {
    for(TicketDetails ticketDetails : ticketDetailsList) {
      TicketDB.cancelTicket(ticketDetails.getTicket().getTicketId().toString());
      seatManager.releaseSeat(ticketDetails.getSeat().getSeatId());
    }
  }
}
