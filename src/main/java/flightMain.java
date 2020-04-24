
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class flightMain {
  public static void main(String arg[]) throws Exception {

    // Read in possible flightPaths
    File data = new File("/Users/dany.benjamin/IdeaProjects/akki/src/main/resources/FlightData.txt");
    Scanner scanner = new Scanner(data);

    LinkedList<City> cities = new LinkedList<City>();

    int count = scanner.nextInt();
    for (int i = 0; i < count; i++) {
      String pipeDelimited = scanner.next();
      String[] allInfo = pipeDelimited.split("\\|");

      int index = getCityIndex(cities, allInfo[0]);

      if (index < 0) {

        ConnectingCity connectingCity = new ConnectingCity(allInfo[1], allInfo[2], allInfo[3]);
        City newCity = new City(allInfo[0]);
        newCity.connectingCity.add(connectingCity);
        cities.add(newCity);

//        System.out.println("Adding new city: " + newCity.toString());
      } else {
        ConnectingCity connectingCity = new ConnectingCity(allInfo[1], allInfo[2], allInfo[3]);
        cities.get(index).connectingCity.add(connectingCity);

//				System.out.println("Adding new connection to city: " + cities.get(index).toString());
      }
    }

    scanner.close();

    System.out.println("\n -- All cities -- \n " + cities.toString());




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
} 
