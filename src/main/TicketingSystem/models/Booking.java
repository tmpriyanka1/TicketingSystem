package main.TicketingSystem.models;

import java.util.*;

public class Booking {
  private List<Ticket> tickets;
  private UUID bookingId;
  private String userEmail;
  private String userPhoneNumber;
  private String cardNumber ;
  private String cardHoldersName;
  private BookingStatus bookingStatus;
  private PaymentType paymentType;
  private ShowDetails showDetails;

  /**
   * Constructor to construct the instance of Booking
   * @param userEmail
   *
   * @param userPhoneNumber
   */
  public Booking(String userEmail, String userPhoneNumber, ShowDetails showDetails, List<Ticket> tickets)
  {
    this.bookingId = UUID.randomUUID();
    this.bookingStatus = BookingStatus.PENDING;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.showDetails = showDetails;
    this.tickets = tickets;
  }

  public UUID getBookingId()
  {
    return bookingId;
  }

  public String getUserEmail()
  {
    return userEmail;
  }
  public void setUserEmail(String userEmail)
  {
    this.userEmail = userEmail;
  }

  public String getUserPhoneNumber()
  {
    return userPhoneNumber;
  }
  public void setUserPhoneNumber(String userPhoneNumber)
  {
    this.userPhoneNumber = userPhoneNumber;
  }

  public String getCardNumber()
  {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber)
  {
    this.cardNumber = cardNumber;
  }

  public ShowDetails getShowDetails() {
    return showDetails;
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public BookingStatus getBookingStatus() {
    return bookingStatus;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }


  public String getCardHoldersName() {
    return cardHoldersName;
  }

  public void confirmBooking(Scanner input) {

    System.out.println("Enter care number");
    cardNumber = input.next();

    System.out.println("Card holder name");
    cardHoldersName = input.next();

    paymentType =PaymentType.CREDIT_CARD;

    for(Ticket ticket: tickets) {
      ticket.setTicketStatus(TicketStatus.CONFIRMED);
    }

    bookingStatus = BookingStatus.CONFIRMED;
  }
}
