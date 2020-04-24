import java.util.List;

public class FlightPath {

  int totalCost;
  int totalTime;
  List<String> cities;

  FlightPath(int totalCost, int totalTime, List<String> cities) {
    this.totalCost = totalCost;
    this.totalTime = totalTime;
    this.cities = cities;
  }

  public String toString() {

    String cityRoutes = "";

    for (int i = 0; i < cities.size(); i++) {
      if (i == cities.size() - 1) {
        cityRoutes += cities.get(i);
      } else {
        cityRoutes += cities.get(i) + " -> ";
      }
    }

    return String.format("%s. Time: %s, Cost: %s", cityRoutes, totalTime, totalCost);
  }
}
