import java.util.LinkedList;

public class City {

  String name;
  LinkedList<ConnectingCity> connectingCity;

  City(String name, LinkedList connectingCity){
    this.name = name;
    this.connectingCity = connectingCity;
  }

  City (String name){
    this.name = name;
    this.connectingCity = new LinkedList<ConnectingCity>();
  }

  public String toString(){

    return String.format("%s --> " + connectingCity.toString(), name);
  }
}
