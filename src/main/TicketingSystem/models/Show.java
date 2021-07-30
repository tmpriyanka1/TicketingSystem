package main.TicketingSystem.models;

import sun.tools.tree.ShortExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Show {
  private UUID showId;
  private String showName;
  private List<ShowDetails> showDetailsList = new ArrayList<>();

  public Show (String showName)
  {
    this.showId = UUID.randomUUID();
    this.showName = showName;
  }
  public UUID getShowId() {
    return showId;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String movieName) {
    this.showName = movieName;
  }

  public List<ShowDetails> getShowDetailsList() {
    return showDetailsList;
  }

  public void addShowDetails(ShowDetails showDetails)
  {
    showDetailsList.add(showDetails);
  }

  public ShowDetails getShowDetailsByIndex(int index) {
    return showDetailsList.get(index);
  }

  public void displayShowDetails() {
    for (int i = 1; i<=showDetailsList.size(); i++) {
      System.out.println(i + " " + showDetailsList.get(i-1).getShowTime().getTime());
    }
  }
}
