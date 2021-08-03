package main.TicketingSystem.models;

public class TicketDetails {
  private Ticket ticket;
  private Seat seat;

  public TicketDetails(Ticket ticket, Seat seat) {
    this.ticket = ticket;
    this.seat = seat;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public Seat getSeat() {
    return seat;
  }
}
