package main.TicketingSystem.models;

import java.util.UUID;

public class Seat {
  private char rowId;
  private int seatNumber;


  public char getRowId() {
    return rowId;
  }

  public void setRowId(char rowId) {
    this.rowId = rowId;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }
}
