package main.TicketingSystem.manager;

import main.TicketingSystem.db.SeatDB;
import main.TicketingSystem.models.Seat;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SeatManager {

  private Scanner input;

  public SeatManager(Scanner input) {
    this.input = input;
  }

  public void createSeat(Seat seat) {
    SeatDB.insertSeat(seat);
  }

  public List<Seat> getAvailableSeatsByShowDetailsId(UUID showDetailsId) {
    List<Seat> availableSeats = SeatDB.getUnreservedSeatsByShowDetailsId(showDetailsId.toString());

    for(int i = 0; i<availableSeats.size(); i++) {
      Seat seat = availableSeats.get(i);
      System.out.println((i+1) + ". " + seat.getSeatValue());
    }

    return availableSeats;
  }

  public void reservedSeat(UUID seatId) {
    SeatDB.reserveSeat(seatId.toString(), 1);
  }

  public void releaseSeat(UUID seatId) {
    SeatDB.reserveSeat(seatId.toString(), 0);
  }
}
