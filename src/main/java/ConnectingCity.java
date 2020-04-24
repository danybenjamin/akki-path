public class ConnectingCity {

  String name;
  int cost;
  int time;

  ConnectingCity(String name, int cost, int time){
    this.name = name;
    this.cost = cost;
    this.time = time;
  }

  ConnectingCity(String name, String cost, String time){
    this.name = name;
    this.cost = Integer.parseInt(cost);
    this.time = Integer.parseInt(time);
  }

  public String toString(){
    return String.format("%s cost:%s time:%s", name, cost, time);
  }
}
