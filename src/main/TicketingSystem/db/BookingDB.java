package main.TicketingSystem.db;

import main.TicketingSystem.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingDB {
  public static void insertBooking(Booking booking) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem", "root", "password");
      Statement stmt = con.createStatement();

      String sql = "INSERT INTO booking (id, userEmail, userPhone, cardNumber, cardHoldersName, bookingStatus, paymentType, showDetailsId) VALUES ('" + booking.getBookingId() + "','" + booking.getUserEmail() + "','" + booking.getUserPhoneNumber() + "','" + booking.getCardNumber() + "','" + booking.getCardHoldersName() + "','" + booking.getBookingStatus().toString() + "','" + booking.getPaymentType().toString() + "','" + booking.getShowDetailsId().toString() + "')";
      stmt.executeUpdate(sql);
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static List<BookingDetails> getListOfBookings(String userEmail) {
    List<BookingDetails> bookings = new ArrayList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem", "root", "password");
      Statement stmt = con.createStatement();
      //ResultSet rs = stmt.executeQuery("select * FROM booking  where userEmail ='"+userEmail +"';");
      ResultSet rs = stmt.executeQuery("select * FROM booking join show_details  on booking.showDetailsId = show_details.id join " +
          " etickingsystem.show on etickingsystem.show.id = show_details.showId  where userEmail = '"+userEmail +"' order by time");
      while(rs.next()) {
        String id = rs.getString("id");
        userEmail = rs.getString("userEmail");
        String userPhone = rs.getString("userPhone");
        String cardNumber = rs.getString("cardNumber");
        String cardHoldersName = rs.getString("cardHoldersName");
        String showDetailsId = rs.getString("showDetailsId");
        String bookingStatus = rs.getString("bookingStatus");
        Booking booking = new Booking(UUID.fromString(id), userEmail, userPhone, UUID.fromString(showDetailsId));
        booking.setCardHoldersName(cardHoldersName);
        booking.setCardNumber(cardNumber);
        booking.setBookingStatus(BookingStatus.valueOf(bookingStatus));

        Timestamp showTime = rs.getTimestamp("time");
        double price = rs.getDouble("price");
        String showId = rs.getString("showId");
        ShowDetails showDetails = new ShowDetails(showDetailsId, showTime, price, UUID.fromString(showId));

        String showName = rs.getString("showName");
        Show show = new Show(showId, showName);
        bookings.add(new BookingDetails(booking, show, showDetails));
      }
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return bookings;
  }

  public static void updateStatus(Booking booking) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String sql = "UPDATE booking SET bookingStatus = '"+booking.getBookingStatus().toString()+"' WHERE (id = '"+booking.getBookingId().toString()+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
}