package main.TicketingSystem.db;

import main.TicketingSystem.models.ShowDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowDetailsDB {
  public static List<ShowDetails> getShowDetailsByShowId(UUID showId) {
    List<ShowDetails> showDetailsList = new ArrayList<>();
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("select * from show_details where showId = '"+showId.toString()+"' order by show_details.time");

      while(rs.next()) {
        String id = rs.getString("id").toString();
        Timestamp showTime = rs.getTimestamp("time");
        double price = rs.getDouble("price");
        ShowDetails showDetails = new ShowDetails(id, showTime, price, showId);
        showDetailsList.add(showDetails);
      }
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    return showDetailsList;
  }

  public static void insertShowDetails(ShowDetails showDetails) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      Timestamp date = new Timestamp(showDetails.getShowTime().getTime());
      String sql = "INSERT INTO show_details (id, time, price, showId) VALUES ('" + showDetails.getShowDetailsId() + "', '" + date +"',"+showDetails.getPrice()+",'"+showDetails.getShowId()+"')";

      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

}
