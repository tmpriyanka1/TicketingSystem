package main.TicketingSystem.models;

public class BookingDetails {
  private Booking booking;
  private Show show;
  private ShowDetails showDetails;

  public BookingDetails(Booking booking, Show show, ShowDetails showDetails) {
    this.booking = booking;
    this.show = show;
    this.showDetails = showDetails;
  }

  public Booking getBooking() {
    return booking;
  }

  public Show getShow() {
    return show;
  }

  public ShowDetails getShowDetails() {
    return showDetails;
  }
}
