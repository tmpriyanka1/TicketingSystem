package main.TicketingSystem.models;

import java.util.*;

public class ShowDetails {

  private UUID showDetailsId;
  private Date showTime;
  private Layout layout;
  private double price;
  private List<Seat> availableSeats;

  public ShowDetails(Date showTime, Layout layout, double price) {
    this.showDetailsId = UUID.randomUUID();
    this.showTime = showTime;
    this.layout = layout;
    this.price = price;
  }

  public UUID getShowDetailsId() {
    return showDetailsId;
  }

  public Date getShowTime() {
    return showTime;
  }

  public void setShowTime(Date showTime) {
    this.showTime = showTime;
  }

  public Layout getLayout() {
    return layout;
  }

  public void setLayout(Layout layout) {
    this.layout = layout;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean showAvailabilities(List<Booking> bookings) {
    availableSeats = new ArrayList<>();

    Map<Character, Set<Integer>> bookedSeats = new HashMap<>();

    for(Booking booking : bookings) {
      for(Ticket ticket : booking.getTickets()) {
        if(ticket.getTicketStatus() == TicketStatus.CONFIRMED || ticket.getTicketStatus() == TicketStatus.PENDING)
        {
          if(!bookedSeats.containsKey(ticket.getSeat().getRowId())){
            bookedSeats.put(ticket.getSeat().getRowId(), new HashSet<>());
          }
          Set<Integer> seatNumbers = bookedSeats.get(ticket.getSeat().getRowId());
          seatNumbers.add(ticket.getSeat().getSeatNumber());
          bookedSeats.put(ticket.getSeat().getRowId(), seatNumbers);
        }
      }
    }

    for( int i = 0; i<layout.getSeats().size(); i++) {
      Seat seat = layout.getSeats().get(i);
      if(!bookedSeats.containsKey(seat.getRowId()) || !bookedSeats.get(seat.getRowId()).contains(seat.getSeatNumber())) {
        availableSeats.add(seat);
        System.out.println(availableSeats.size() + " " + seat.getRowId()+"-"+seat.getSeatNumber());
      }

    }

    return availableSeats.size() > 0;
  }

  public Seat getAvailableSeatByIndex(int i) {
    return availableSeats.get(i);
  }
}
