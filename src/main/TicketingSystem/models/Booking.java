package main.TicketingSystem.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Booking {
  private ArrayList<Ticket> tickets;
  private UUID bookingId;
  private String userEmail;
  private String userPhoneNumber;
  private String cardNumber ;
  private String holdersName;
  private Date expiryDate;
  private BookingStatus bookingStatus;
  private PaymentType paymentType;

  /**
   * Constructor to construct the instance of Booking
   * @param userEmail
   *
   * @param userPhoneNumber
   */
  public Booking(String userEmail, String userPhoneNumber)
  {
    this.bookingId = UUID.randomUUID();
    this.bookingStatus = BookingStatus.PENDING;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
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

  public String getHoldersName()
  {
    return holdersName;
  }
  public void setHoldersNumber(String holdersName)
  {
    this.holdersName = holdersName;
  }

  public Date getExpiryDate()
  {
    return expiryDate;
  }
  public void setExpiryDate(Date expiryDate)
  {
    this.expiryDate = expiryDate;
  }

  public ArrayList<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(ArrayList<Ticket> tickets) {
    this.tickets = tickets;
  }

  public BookingStatus getBookingStatus() {
    return bookingStatus;
  }

  public void setBookingStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }
}
