package main.TicketingSystem.models;

import java.util.UUID;

public class Show {
  private UUID showId;
  private String showName;

  public Show (String id, String showName)
  {
    this.showId = UUID.fromString(id);
    this.showName = showName;
  }
  public UUID getShowId() {
    return showId;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String movieName) {
    this.showName = movieName;
  }

}
