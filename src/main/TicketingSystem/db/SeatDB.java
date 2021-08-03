package main.TicketingSystem.db;

import main.TicketingSystem.models.Seat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SeatDB {
  public static void insertSeat(Seat seat) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String sql = "INSERT INTO seat (id, rowId, seatNumber, showDetailsId) VALUES ('"+seat.getSeatId()+"','"+seat.getRowId()+"',"+seat.getSeatNumber()+",'"+seat.getShowDetailsId()+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

  public static List<Seat> getUnreservedSeatsByShowDetailsId(String showDetailsId) {
    List<Seat> availableSeats = new ArrayList<>();
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("SELECT * FROM seat where isReserved=0 and showDetailsId = '"+showDetailsId+"' order by rowId, seatNumber");

      while(rs.next()) {
        String id = rs.getString("id");
        char rowId = rs.getString("rowId").charAt(0);
        int seatNumber = rs.getInt("seatNumber");
        Seat seat = new Seat(UUID.fromString(id), rowId, seatNumber, UUID.fromString(showDetailsId));
        availableSeats.add(seat);
      }
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    return availableSeats;
  }

  public static void reserveSeat(String seatId, int isReserved) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String sql = "UPDATE seat SET isReserved = '"+isReserved+"' WHERE (id = '"+seatId+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}
