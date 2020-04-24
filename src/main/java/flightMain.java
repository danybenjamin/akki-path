
import java.io.File;
import java.util.*;

public class flightMain {

  public static void main(String arg[]) throws Exception {

    // Read in possible flightPaths
    File flightPaths = new File("/Users/dany.benjamin/IdeaProjects/akki/src/main/resources/FlightData.txt");
    Scanner flightPathScanner = new Scanner(flightPaths);

    LinkedList<City> cities = new LinkedList<City>();

    int count = flightPathScanner.nextInt();
    for (int i = 0; i < count; i++) {
      String pipeDelimited = flightPathScanner.next();
      String[] allInfo = pipeDelimited.split("\\|");

      // Route A>B
      int index = getCityIndex(cities, allInfo[0]);
      int indexB = getCityIndex(cities, allInfo[1]);
      ConnectingCity connectingCity = new ConnectingCity(allInfo[1], allInfo[2], allInfo[3]);
      ConnectingCity connectingCityB = new ConnectingCity(allInfo[0], allInfo[2], allInfo[3]);

      if (index < 0) {

        City newCity = new City(allInfo[0]);
        newCity.connectingCity.add(connectingCity);
        cities.add(newCity);

        System.out.println("Adding new city: " + newCity.toString());

        // Add Route B > A

        if (indexB < 0) {

          City newCityB = new City(allInfo[1]);
          newCityB.connectingCity.add(connectingCityB);
          cities.add(newCityB);

          System.out.println("Adding new city: " + newCityB.toString());
        } else {
          cities.get(indexB).connectingCity.add(connectingCityB);
        }
      } else {
        cities.get(index).connectingCity.add(connectingCity);

        System.out.println("Adding new connection to city: " + cities.get(index).toString());

        // Add Route B > A
        if (indexB < 0) {

          City newCityB = new City(allInfo[1]);
          newCityB.connectingCity.add(connectingCityB);
          cities.add(newCityB);

          System.out.println("Adding new city: " + newCityB.toString());
        } else {
          cities.get(indexB).connectingCity.add(connectingCityB);
        }
      }
    }

    flightPathScanner.close();

    System.out.println("\n -- All cities -- \n " + cities.toString() + "\n -- -- \n");

    // Read and process requests
    File requestedFlights = new File("/Users/dany.benjamin/IdeaProjects/akki/src/main/resources/Requested.txt");
    Scanner requestScanner = new Scanner(requestedFlights);
    int lineCount = requestScanner.nextInt();
    for (int i = 0; i < lineCount; i++) {

      String pipeDelimited = requestScanner.next();
      String[] allInfo = pipeDelimited.split("\\|");

      if (allInfo[2].equals("T")) {

        System.out.println(String.format("Sorting routes from %s to %s by Time", allInfo[0], allInfo[1]));

        sortByTime(allInfo[0], allInfo[1], cities);
      } else {
        System.out.println("allInfo is " + allInfo[2]);
      }
    }

    requestScanner.close();
  }

  private static int getCityIndex(LinkedList<City> cities, String cityName) {

    if (cities.isEmpty()) {
      return -1;
    }

    for (int i = 0; i < cities.size(); i++) {

      if (cities.get(i).name.equals(cityName)) {
        return i;
      }
    }

    return -1;
  }

  public static void sortByTime(String startCity, String endCity, LinkedList<City> cities) {

    // Find the city
    int cityIndex = getCityIndex(cities, startCity);

    if (cityIndex >= 0) {
      System.out.println("City found at index:" + cityIndex);

      City city = cities.get(cityIndex);
      LinkedList<ConnectingCity> connectingCities = city.connectingCity;

      Stack<FlightPath> flightPaths = new Stack<>();
      //Find routes

      System.out.println(String.format("\n\nLooking for connections from %s to %s.", startCity, endCity));

      for (int i = 0; i < connectingCities.size(); i++) {

        System.out.println(String.format("\t\tfound dest.. %s > %s ", startCity, connectingCities.get(i).name));
        ConnectingCity currCity = connectingCities.get(i);

        if (currCity.name.equals(endCity)) {
          List<String> pathCities = new ArrayList<>();
          pathCities.add(0, startCity);
          pathCities.add(currCity.name);

          FlightPath flightPath = new FlightPath(currCity.cost, currCity.time, pathCities);

          if (flightPaths.isEmpty()) {
            flightPaths.push(flightPath);
          } else if (flightPaths.get(0).totalTime > flightPath.totalTime) {
            flightPaths.push(flightPath);
          } else {
            flightPaths.add(flightPath);
          }

          System.out.println("\t\t -- direct connection");
        } else {
          System.out.println("\t\t\tLooking for connections");

          City passingCity = cities.get(getCityIndex(cities, connectingCities.get(i).name));

          for (int j = 0; j < passingCity.connectingCity.size(); j++) {
            ConnectingCity passingCityConnection = passingCity.connectingCity.get(j);
            if (passingCityConnection.name.equals(endCity)) {

              System.out.println(String.format("\t\t\t%s connects to %s", passingCityConnection.name, currCity.name));

              List<String> pathCities = new ArrayList<>();
              pathCities.add(0, startCity);
              pathCities.add(passingCity.name);
              pathCities.add(endCity);

              int totalTime = currCity.time + passingCityConnection.time;
              int totalCost = currCity.cost + passingCityConnection.cost;

              FlightPath flightPath = new FlightPath(totalCost, totalTime, pathCities);

              if (flightPaths.isEmpty()) {
                flightPaths.push(flightPath);
              } else if (flightPaths.get(0).totalTime > flightPath.totalTime) {
                flightPaths.push(flightPath);
              } else {
                flightPaths.add(flightPath);
              }

            }
          }
        }
      }

      for (int i = 0; i < flightPaths.size(); i++) {
        System.out.println("Flightpath: " + flightPaths.get(i).toString());
      }
      return;
    }

    System.out.println("no info found on city");
  }
}
//
//static Stack<FlightPath> getAllRoutes(String startCity, String endCity, LinkedList<City> cities, int totalPaths) {
//  Stack <FlightPath> flightPaths = new Stack<>();
//
//  int foundPaths = 0;
//  boolean allPathsFound = false;
//  while (!allPathsFound && foundPaths < totalPaths){
//
//
//
//  }
//}