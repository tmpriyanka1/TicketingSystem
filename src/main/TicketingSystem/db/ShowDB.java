package main.TicketingSystem.db;


import main.TicketingSystem.models.Show;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowDB {
  public static void insertShow(Show show) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      String sql = "INSERT INTO etickingsystem.show (id, showName) VALUES ('"+show.getShowId()+"','"+show.getShowName()+"')";
      stmt.executeUpdate(sql);
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

  public static List<Show> getAllShows() {
    List<Show> shows = new ArrayList<>();
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/etickingsystem","root","password");
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("select * from etickingsystem.show order by etickingsystem.show.showName");

      while(rs.next()) {
        String id = rs.getString("id").toString();
        String showName = rs.getString("showName");
        Show show = new Show(id, showName);
        shows.add(show);
      }
      con.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    return shows;
  }
}
