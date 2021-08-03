package main.TicketingSystem.models;

import java.util.UUID;

public class Ticket {

  private UUID ticketId;
  private double price;
  private TicketStatus ticketStatus;
  private UUID seatId;
  private UUID bookingId;

  public Ticket(UUID ticketId, UUID seatId, double price, UUID bookingId, TicketStatus ticketStatus)
  {
    this(seatId, price, bookingId);
    this.ticketId = ticketId;
    this.ticketStatus = ticketStatus;
  }

  public Ticket(UUID seatId, double price, UUID bookingId)
  {
    this.ticketId = UUID.randomUUID();
    this.ticketStatus = TicketStatus.PENDING;
    this.seatId = seatId;
    this.price = price;
    this.bookingId = bookingId;
  }
  public UUID getTicketId() {
    return ticketId;
  }

  public UUID getSeatId() {
    return seatId;
  }

  public UUID getBookingId() {
    return bookingId;
  }

  public double getPrice() {
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
