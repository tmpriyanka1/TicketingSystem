package main.TicketingSystem.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Layout {
  private UUID layoutId;
  private List<Seat> seats;

  public Layout(List<Seat> seats) {
    this.layoutId = UUID.randomUUID();
    this.seats = seats;

  }

  public UUID getLayoutId() {
    return layoutId;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(ArrayList<Seat> seats) {
    this.seats = seats;
  }
}
