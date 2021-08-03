package main.TicketingSystem.models;

import java.util.UUID;

public class Seat {
  private UUID seatId;
  private char rowId;
  private int seatNumber;
  private boolean isReserved;
  private UUID showDetailsId;

  public Seat(UUID seatId, char rowId, int seatNumber, UUID showDetailsId, boolean isReserved) {
    this(seatId, rowId, seatNumber, showDetailsId);
    this.isReserved = isReserved;
  }
  public Seat(UUID seatId, char rowId, int seatNumber, UUID showDetailsId) {
    this.seatId = seatId;
    this.rowId = rowId;
    this.seatNumber = seatNumber;
    this.showDetailsId = showDetailsId;
  }

  public void isReserved(boolean isReserved) {
    this.isReserved = isReserved;
  }

  public UUID getSeatId() {
    return seatId;
  }
  public UUID getShowDetailsId() {
    return showDetailsId;
  }
  public boolean isReserved() {
    return isReserved;
  }

  public char getRowId() {
    return rowId;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public String getSeatValue() {
    return rowId+"-"+seatNumber;
  }
}
