package main.TicketingSystem.models;

import java.util.UUID;

public class Ticket {

  private UUID ticketId;
  private float price;
  private BookingStatus ticketStatus;

  public Ticket (UUID ticketId)
  {
    this.ticketId = UUID.randomUUID();
    this.ticketStatus = BookingStatus.PENDING;
  }
  public UUID getTicketId() {
    return ticketId;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public BookingStatus getTicketStatus() {
    return ticketStatus;
  }

  public void setTicketStatus(BookingStatus ticketStatus) {
    this.ticketStatus = ticketStatus;
  }
}
