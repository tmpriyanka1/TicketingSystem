package main.TicketingSystem.models;

import java.util.*;

public class ShowDetails {

  private UUID showDetailsId;
  private Date showTime;
  private double price;
  private UUID showId;

  public UUID getShowId() {
    return showId;
  }

  public ShowDetails(String id, Date showTime,double price, UUID showId) {
    this.showDetailsId = UUID.fromString(id);
    this.showTime = showTime;
    this.price = price;
    this.showId = showId;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
