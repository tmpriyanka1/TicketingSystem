package main.TicketingSystem.models;

import java.util.UUID;

public class Seat {
  private char rowId;
  private int seatNumber;

  public Seat(char rowId, int seatNumber) {
    this.rowId = rowId;
    this.seatNumber = seatNumber;
  }

  public char getRowId() {
    return rowId;
  }

  public int getSeatNumber() {
    return seatNumber;
  }
}
