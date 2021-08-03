package main.TicketingSystem.models;

import java.util.*;

public class Booking {
  private UUID bookingId;
  private String userEmail;
  private String userPhoneNumber;
  private String cardNumber ;
  private String cardHoldersName;
  private BookingStatus bookingStatus;
  private PaymentType paymentType;
  private UUID showDetailsId;


  public Booking(UUID bookingId, String userEmail, String userPhoneNumber, UUID showDetailsId)
  {
    this.bookingId = bookingId;
    this.bookingStatus = BookingStatus.PENDING;
    this.userEmail = userEmail;
    this.userPhoneNumber = userPhoneNumber;
    this.showDetailsId = showDetailsId;
  }

  public UUID getBookingId() {
    return bookingId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getUserPhoneNumber() {
    return userPhoneNumber;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getCardHoldersName() {
    return cardHoldersName;
  }

  public BookingStatus getBookingStatus() {
    return bookingStatus;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public UUID getShowDetailsId() {
    return showDetailsId;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void setCardHoldersName(String cardHoldersName) {
    this.cardHoldersName = cardHoldersName;
  }

  public void setBookingStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }
}
