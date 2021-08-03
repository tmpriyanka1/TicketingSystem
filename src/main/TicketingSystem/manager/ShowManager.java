package main.TicketingSystem.manager;

import main.TicketingSystem.db.ShowDB;
import main.TicketingSystem.models.*;

import java.text.ParseException;
import java.util.*;

public class ShowManager {
  private Scanner input;
  private ShowDetailsManager showDetailsManager;

  public ShowManager(Scanner input) {
    this.input = input;
    this.showDetailsManager = new ShowDetailsManager(input);
  }

  public void displayShows() throws ParseException {
    List<Show> shows = ShowDB.getAllShows();
    if(shows.size() == 0)
    {
      System.out.println("No shows available");
    }

    if(shows.size() != 0) {
      for (int i = 1; i <= shows.size(); i++) {
        System.out.println(i + ". " + shows.get(i - 1).getShowName());
      }
    }

    System.out.println("Select the options or press N to go to main menu");
    System.out.println("------------------------------------------------");
    if(!shows.isEmpty()) {
      System.out.println("Select the show to view details");
    }
    System.out.println("Press '+' to add new show");

    String selection = input.next();

    if (selection.toLowerCase().equals("n")) {
      return;
    }

    if (selection.toLowerCase().equals("+")) {
      createShow();
    }
    else {
      showDetailsManager.displayShowDetails(shows.get(Integer.parseInt(selection) - 1).getShowId());
    }
  }

  private void createShow() throws ParseException {
    System.out.println("Enter the show name");

    Show show = new Show(UUID.randomUUID().toString(), input.next());

    ShowDB.insertShow(show);
  }
}
