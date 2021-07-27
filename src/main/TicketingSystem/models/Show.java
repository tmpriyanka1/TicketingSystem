package main.TicketingSystem.models;

import java.util.UUID;

public class Show {
  private UUID showId;
  private String movieName;

  public Show (String movieName)
  {
    this.showId = UUID.randomUUID();
    this.movieName = movieName;
  }
  public UUID getShowId() {
    return showId;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }
}
