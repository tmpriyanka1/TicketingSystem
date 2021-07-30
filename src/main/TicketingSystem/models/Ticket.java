package main.TicketingSystem.models;

import java.util.UUID;

public class Ticket {

  private UUID ticketId;
  private float price;
  private TicketStatus ticketStatus;
  private Seat seat;

  public Ticket (Seat seat)
  {
    this.ticketId = UUID.randomUUID();
    this.ticketStatus = TicketStatus.PENDING;
    this.seat = seat;
  }
  public UUID getTicketId() {
    return ticketId;
  }

  public Seat getSeat() {
    return seat;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public TicketStatus getTicketStatus() {
    return ticketStatus;
  }

  public void setTicketStatus(TicketStatus ticketStatus) {
    this.ticketStatus = ticketStatus;
  }
}
