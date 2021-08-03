package main.TicketingSystem.db;

import main.TicketingSystem.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDB {
  public static void createTicket(Ticket ticket) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String status = ticket.getTicketStatus().toString();
      String sql = "INSERT INTO ticket (id, price, status, bookingId, seatId) VALUES ('"+ticket.getTicketId()+"',"+ticket.getPrice()+",'"+status+"','"+ticket.getBookingId()+"','"+ticket.getSeatId()+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

  public static List<TicketDetails> getTicketDetailsByBookingID(String bookingId) {
    List<TicketDetails> tickets = new ArrayList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem", "root", "password");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select *, ticket.id as ticketId from ticket join seat on ticket.seatId = seat.id where ticket.bookingId = '"+ bookingId +"' order by rowId, seatNumber");
      while(rs.next()) {
        String ticketId = rs.getString("ticketId");
        double price = rs.getDouble("price");
        String status = rs.getString("status");
        String seatId = rs.getString("seatId");
        char rowId = rs.getString("rowId").charAt(0);
        int seatNumber = rs.getInt("seatNumber");
        String showDetailsId = rs.getString("showDetailsId");
        boolean isReserved = rs.getInt("isReserved") == 1;

        Ticket ticket = new Ticket(UUID.fromString(ticketId), UUID.fromString(seatId), price, UUID.fromString(bookingId), TicketStatus.valueOf(status));
        Seat seat = new Seat(UUID.fromString(seatId), rowId, seatNumber, UUID.fromString(showDetailsId), isReserved);
        tickets.add(new TicketDetails(ticket, seat));
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return tickets;
  }

  public static void cancelTicket(String ticketId) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String sql = "UPDATE ticket SET status = '"+TicketStatus.CANCELLED+"' WHERE (id = '"+ticketId+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}
