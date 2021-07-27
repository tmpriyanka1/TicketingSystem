package main.TicketingSystem.models;

import java.util.ArrayList;
import java.util.UUID;

public class Layout {
  private UUID layoutId;
  private ArrayList<Seat> seats;

  public Layout(UUID layoutId) {
    this.layoutId = UUID.randomUUID();

  }

  public UUID getLayoutId() {
    return layoutId;
  }

  public ArrayList<Seat> getSeats() {
    return seats;
  }

  public void setSeats(ArrayList<Seat> seats) {
    this.seats = seats;
  }
}
