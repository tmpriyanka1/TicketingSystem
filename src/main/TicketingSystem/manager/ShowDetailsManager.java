package main.TicketingSystem.manager;

import main.TicketingSystem.db.ShowDetailsDB;
import main.TicketingSystem.models.Seat;
import main.TicketingSystem.models.ShowDetails;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowDetailsManager {
  private Scanner input;
  private SeatManager seatManager;
  private BookingManager bookingManager;

  public ShowDetailsManager(Scanner input) {
    this.input = input;
    this.seatManager = new SeatManager(input);
    this.bookingManager = new BookingManager(input);
  }

  public void displayShowDetails(UUID showId) throws ParseException {
    List<ShowDetails> showDetailsList = ShowDetailsDB.getShowDetailsByShowId(showId);
    if(showDetailsList.isEmpty()) {
      System.out.println("No Shows available");
    }

    if(showDetailsList.size() != 0) {
      for (int i = 1; i <= showDetailsList.size(); i++) {
        ShowDetails showDetails = showDetailsList.get(i - 1);
        System.out.println(i + ". " + showDetails.getShowTime().toLocaleString() + " - $"+showDetails.getPrice());
      }
    }

    System.out.println("Select the options or press N to go to main menu");
    System.out.println("------------------------------------------------");
    if(!showDetailsList.isEmpty()) {
      System.out.println("Select the show details to book");
    }
    System.out.println("Press '+' to add new show details");

    String selection = input.next();

    if (selection.toLowerCase().equals("n")) {
      return;
    }

    if (selection.toLowerCase().equals("+")) {
      createShowDetails(showId);
    }
    else {
      ShowDetails showDetails = showDetailsList.get(Integer.parseInt(selection)-1);
      bookingManager.initiateBooking(showDetails.getShowDetailsId(), showDetails.getPrice());
    }
  }

  private ShowDetails createShowDetails(UUID showId) throws ParseException {
    System.out.println("Enter show date and time MM/DD/YYYY.HH:MM");
    String dateString = input.next();

    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy.HH:mm");
    Date date = formatter.parse(dateString);

    System.out.println("Enter the price of each ticket");

    double price = input.nextDouble();

    System.out.println("Enter the number of rows for the show");
    int rows = input.nextInt();

    System.out.println("Enter the number of seats in each row");
    int seatCount = input.nextInt();

    ShowDetails showDetails = new ShowDetails(UUID.randomUUID().toString(), date, price, showId);

    ShowDetailsDB.insertShowDetails(showDetails);

    for(int i = 0; i<rows; i++) {
      for(int j = 1; j<=seatCount; j++) {
        seatManager.createSeat(new Seat(UUID.randomUUID(),(char) (65+i), j, showDetails.getShowDetailsId()));
      }
    }

    return null;
  }
}
