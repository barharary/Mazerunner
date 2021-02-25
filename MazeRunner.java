package mazeRunner;

import java.util.Scanner;

public class MazeRunner {
	static Maze myMap = new Maze();

	public static void main(String[] args) {

		int i = 0;
		String direction;
		String status = "";
		intro();
		while (i <= 100 && status != "winner") {
			direction = userMove();
			i = canIMove(direction, i);
			status = win(i);
			i = (navigatePit(direction, status, i));
			i = movesMessage(i, status);

		}
		GoodBye();
	}

	public static void intro() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("******** W E L C O M E   T O   T H E   M A Z E   R U N N E R ********");
		scanner.nextLine();
		System.out.print("Your goal is to escape the maze successfully.");
		scanner.nextLine();
		System.out.print("Press enter to see the Maze.");
		scanner.nextLine();
		myMap.printMap();
		System.out.println("INSTRUCTIONS:");
		System.out.println("1. The X symbol is your current position.");
		System.out.println(
				"2. To move inside the maze use: R for right ,L for left ,U for up and D for down (R, L, U, D).");
		System.out.println("3. After 100 moves you will lost and the game will over. Hurry up!");
		System.out.print("Press enter to start");
		scanner.nextLine();
	}

	public static String userMove() {
		String move = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("so.. Where would you like to move? ");

		move = scanner.nextLine(); // TODO why cant be char nextChar
		while (!move.equalsIgnoreCase("r") && !move.equalsIgnoreCase("l") && !move.equalsIgnoreCase("u")
				&& !move.equalsIgnoreCase("d")) {
			System.out.print("PLease insert one of the keys: R, L, U, D: ");
			move = scanner.nextLine();
		}
		return move.toUpperCase();
	}

	public static int canIMove(String direction, int i) {
		String sorry = "Sorry, you’ve hit a wall.";
		switch (direction) {
		case "R":
			if (myMap.canIMoveRight() == true) {
				myMap.moveRight();
				System.out.println("You move RIGHT");
				return ++i;
			} else if (myMap.isThereAPit(direction)) {
				return i;
			} else {
				System.out.println(sorry);
				return ++i;
			}

		case "L":
			if (myMap.canIMoveLeft() == true) {
				myMap.moveLeft();
				System.out.println("You move LEFT");
				return ++i;
			} else if (myMap.isThereAPit(direction)) {
				return i;
			} else {
				System.out.println(sorry);
				return ++i;
			}
		case "U":
			if (myMap.canIMoveUp() == true) {
				myMap.moveUp();
				System.out.println("You move UP");
				return ++i;
			} else if (myMap.isThereAPit(direction)) {
				return i;
			} else {
				System.out.println(sorry);
				return ++i;
			}
		case "D":
			if (myMap.canIMoveDown() == true) {
				myMap.moveDown();
				System.out.println("You move DOWN");
				return ++i;
			} else if (myMap.isThereAPit(direction)) {
				return i;
			} else {
				System.out.println(sorry);
				return ++i;
			}
		default:
			System.out.println("ERROR");
			return i;
		}

	}

	public static int movesMessage(int count, String status) {
String countString1 = "You have made "  + count + " moves, you have "+ (100-count) + " moves left to escape";
String countString2 = "Oh no! You took " + count + " moves.. took too long to escape, and now the maze exit is closed FOREVER.";
if (!status.equals("winner")) {
			switch (count) {

			case 5:
				System.out.println(countString1 + ".");
				return count;
			case 50:
				System.out.println(countString1 + ".");
				return count;

			case 75:
				System.out.println(countString1 + "!");
				return count;

			case 90:
				System.out.println(countString1 + "!!");
				return count;

			case 100:
				System.out.println(countString2);
				System.out.println("Game Over!");
				return ++count;

			}
		}
		return count;
	}

	public static int navigatePit(String direction, String status, int i) {

		if (!status.equals("winner")) { // without this patch when MAZE.java check for pits in (10,19), the win
										// condition, there an error..
			if (myMap.isThereAPit(direction)) {
				myMap.printMap();
				System.out.println("Watch out! There's a pit ahead, jump it? write yes or no:");
				Scanner scanner = new Scanner(System.in);
				String jump = scanner.nextLine();
				while (!jump.toLowerCase().startsWith("y") && !jump.toLowerCase().startsWith("n")) {
					System.out.println("Not understand.. want to jump? yes or no?");
					jump = scanner.nextLine();
				}
				if (jump.toLowerCase().startsWith("y")) {
					myMap.jumpOverPit(direction);
					System.out.println("Great Jump!");
					myMap.printMap();
					return ++i;
				}
				if (jump.toLowerCase().startsWith("n")) {
					return i;
				}
			} else {
				myMap.printMap();
				return i;
			}
		}
		return i;
	}

	public static String win(int i) {
		if (myMap.didIWin()) {
			myMap.printMap();
			System.out.println("!!You mange to escape!! you are a winner!!");
			System.out.println("you did it in " + i + " moves");
			return "winner";
		}
		return "";
	}

	public static void GoodBye() {

		System.out.println("goodBye");
	}

}
