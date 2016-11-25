import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SimpleShipTest {
	private ArrayList<SimpleShip> ship = new ArrayList<SimpleShip>();
	private int numGuess = 0;
	
	public static void main(String[] args) {
		SimpleShipTest game = new SimpleShipTest();
		game.setUp();
	}
	
	private void setUp() {
		ship.add(new SimpleShip("sub",3));
		ship.add(new SimpleShip("Battle Ship", 3));
		ship.add(new SimpleShip("War Machine", 3));
		setLocations();
		
		System.out.println("Welcome to The battle ship game.Try to kill the ships ");
		play();
	}
	private void play() {
		String guess, result;
		Scanner KB = new Scanner(System.in);
		while(!ship.isEmpty()) {
			result = "miss";
			numGuess++;
			System.out.println("Enter a guess:");
			guess = KB.nextLine();
			guess = guess.toUpperCase();
			for (int i = 0;i < ship.size();i++) {
				result = ship.get(i).check(guess);
				if(result.equals("kill")) {
					result = ("you sunk " + ship.get(i).getName());
					ship.remove(i);
					break;
				}else if(result.equals("hit")) {
					break;
				}
			}
			System.out.println(result);
			
		}
		KB.close();
		finish();
	}
	
	private void finish() {
		if (numGuess == 9) {
			System.out.println("Congratulations! you got a perfect score!!");
			System.out.println("number of guess "+ numGuess);

		}
		else if(numGuess < 20) {
			System.out.println("number of guess "+ numGuess);
		}
		else if(numGuess < 30) {
			System.out.println("number of guess "+ numGuess);

		}
	}
	private void setLocations() {
		Random rand = new Random();
		ArrayList<String> locationToSet = new ArrayList<String>();
		ArrayList<String> temp = null;
		int let,num,incl,incn;
		String alpha = "ABCDEFG";
		boolean worked;
		for(int i = 0; i < ship.size(); i++) {
			worked = false;
			start:
				while(!worked) {
					locationToSet.clear();
					worked = true;
					let = rand.nextInt(5);
					num = 1 + rand.nextInt(5);
					if (num % 2 == 0) {
						incl = 1;
						incn = 0;
					}
					else
					{
						incl = 0;
						incn = 1;
					}
					for (int j = 0;j < ship.get(i).getSize();j++) {
						String loc = "" + alpha.charAt(let) + num;
						let += incl;
						num += incn;
						
						for (int t = 0; t < ship.size();t++) {
							if (t != i) {
								temp = ship.get(t).getLocation();
								if(temp.contains(loc)) {
									worked = false;
									continue start;
								}
							}
						}
						locationToSet.add(loc);
					}
					ship.get(i).setLocation(locationToSet);
				}
		}
	}
	
}