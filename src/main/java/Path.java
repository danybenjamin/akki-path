
import java.util.LinkedList;
import java.util.Stack;

class Path{
	LinkedList<City> cities;
	Stack<City> stack;
	City depart;
	City arrive;
	boolean found;
	int cost, time;


	Path(LinkedList<City> c,City d, City a){
		cities = c;
		stack = new Stack<City>();
		stack.push(d);
		found = false;
		depart = d;
		arrive = a;
		cost = 0;
		time = 0;
	}

	void getPath(City city1) {
		for(int i = 0; i < city1.connectingFlights.size(); i++) {
			if(city1.connectingFlights.get(i).getName().equals(arrive.getName())){
				stack.push(city1.connectingFlights.get(i));
				found = true;
				printStack();
			}
		}
		if((stack.size() > 0) && (found == false)) {
			for(int i = 0; i < city1.connectingFlights.size(); i++) {
				boolean newCity = true;
				for(int j = 0; j < stack.size(); j++) {
					if(city1.connectingFlights.get(i).getName().equals(stack.get(j).getName()))
						newCity = false;
				}
				if(newCity) {
					for(int k = 0; k < cities.size(); k++) {
						if(cities.get(k).getName().equals(city1.connectingFlights.get(i).getName())) {
							stack.push(city1.connectingFlights.get(i));
							getPath(cities.get(k));
						}

					}
				}

			}
		}
		else if((stack.size() == 0)  && (found == false)){
			for(int i = 0; i < city1.connectingFlights.size(); i++) {
				for(int k = 0; k < cities.size(); k++) {
					if(cities.get(k).getName().equals(city1.connectingFlights.get(i).getName())) {
						stack.push(city1.connectingFlights.get(i));
						getPath(cities.get(k));
					}
				}
			}
		}
	}

	void printStack() {
		Stack<City> s = new Stack<City>();

		while(!(stack.size() == 0)) {
			s.push(stack.pop());
		}

		System.out.println();

		while(!(s.size() == 0)) {
			City temp = s.pop();
			System.out.print(temp.getName() + " --> ");
			cost += temp.getCost();
			time += temp.getTime();
		}

		System.out.print(" Cost: " + cost + ", Time: " + time);
	}


}
