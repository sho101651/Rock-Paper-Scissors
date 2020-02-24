package RockPaperScissors;

import java.util.Scanner;
import java.lang.reflect.Constructor;
import java.util.Random;

/**********************************************
 * Simple game made by Frekvens1 - Version 1.1 - Self explaining code - Made for
 * newer developers to learn more
 ***********************************************
 * 
 * Changelog
 ***********************************************
 * 
 * 1.1: - Bug fixes - Added more inputs - Increased readability - Optimized some
 * functions - Added changelog and credits
 ***********************************************
 * 
 * Credits - Thanks for testing my code!
 ***********************************************
 * 
 * Shlvam Rawat & Sasha_Kuprii - Shortened the inputs to "r, p, s"
 * 
 * Ivan - Made me convert to switch, as its more correct usage.
 * 
 * Gabriel Carvalho - Bug hunter: "NoSuchElementException" when input was null
 * 
 * Alexandre da Costa Leite - Bug hunter: Changing from && to || in function
 * check for win - Important change if converting this to multiplayer
 ***********************************************
 * 
 * Uploaded 19.07.2016 (DDMMYYYY) Last updated 04.02.2017 (DDMMYYYY)
 **********************************************/

public class Program {
	/*
	 * Valid user input: rock, paper, scissors, scissor r, p, s
	 */
	public static double WinChance[] = { 1, 1, 1 };

	public static void main(String[] args) {
		boolean end = true;

		do {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Input your answer (rock, scissors ,paper!)");

				if (sc.hasNext()) { // Checks for null values

					String userInput = quickFormat(sc.next());

					if (isValid(userInput)) {
						game(userInput);

					} else {
						displayInputs();
					}
				} else {
					displayInputs(); // Null value, displaying correct inputs
				}

				System.out.println("Still wanna play? [Y/N]");
				String play = sc.next();
				if (play.equalsIgnoreCase("n")) {
					System.out.println("thank you");
					end = false;
				} else {
					end = true;
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} while (end == true);
	}

	public static void game(String user) {
		String types[] = { "rock", "scissors", "paper" };
		String computer = computerResults();

		print(user + " vs " + computer + "\n");

		if (user.equalsIgnoreCase(computer)) {
			print("Stalemate! No winners.");
			System.out.println(WinChance[0] + " " + WinChance[1] + " " + WinChance[2]);
		} else {

			if (checkWin(user, computer)) {
				print("You won against the computer!");
				if(types[0].equalsIgnoreCase(computer)) {
					WinChance[0]=WinChance[0]-0.05;
				}
				else if(types[1].equalsIgnoreCase(computer)) {
					WinChance[1]=WinChance[1]-0.05;
				}
				else if(types[2].equalsIgnoreCase(computer)) {
					WinChance[2]=WinChance[2]-0.05;
				}
				System.out.println(WinChance[0] + " " + WinChance[1] + " " + WinChance[2]);

			} else {
				print("You lost against the computer!");
				if(types[0].equalsIgnoreCase(computer)) {
					WinChance[0]=WinChance[0]+0.05;
				}
				else if(types[1].equalsIgnoreCase(computer)) {
					WinChance[1]=WinChance[1]+0.05;
				}
				else if(types[2].equalsIgnoreCase(computer)) {
					WinChance[2]=WinChance[2]+0.05;
				}
				System.out.println(WinChance[0] + " " + WinChance[1] + " " + WinChance[2]);
			}
		}
	}

	public static String computerResults() {
		int computerChoice;

		String types[] = { "rock", "scissors", "paper" };

		double pick = 0, max = 0;
		String name[] = { "", "", "" };
		int same = 0;
		double equal[] = { 0, 0, 0 };
		Random rand = new Random();
		String maxName = "";

		for (int i = 0; i < WinChance.length; i++) {
			// System.out.println("round : " + (i+1));
			if (max < WinChance[i]) {
				max = WinChance[i];
				maxName = types[i];
				if (i == 1 && equal[i - 1] < max) {
					// System.out.println("Special case 13");
					equal[i - 1] = WinChance[i];
					name[i - 1] = types[i];
				} else {
					// System.out.println("normal case 13");
					equal[i] = WinChance[i];
					name[i] = types[i];
				}
			} else if (max == WinChance[i]) {
				same = same + 1;
				if (same == 1 && name[i - 1].isEmpty()) {
					// System.out.println("Spetial case 12");
					equal[i - 1] = WinChance[i];
					name[i - 1] = types[i];
				} else {
					// System.out.println("normal case 12");
					equal[i] = WinChance[i];
					name[i] = types[i];
				}
			}
		}

//		System.out.println("Max : " + max);
//		System.out.println("Max name : " + maxName);
//		System.out.println("Same : " + same);
//		System.out.println("name[] : " + name[0] + " " + name[1] + " " + name[2] + " ");
//		System.out.println("equal[] : " + equal[0] + " " + equal[1] + " " + equal[2] + " ");

		if (same == 1 && max == equal[0]) {
			// System.out.print("Use case same 1 : ");
			computerChoice = rand.nextInt(2);
			return name[computerChoice];
		} else if (same == 2 && max == equal[0]) {
			// System.out.print("Use case same 2 : ");
			computerChoice = rand.nextInt(3);
			return name[computerChoice];
		} else {
			// System.out.print("Use case same 0 : ");
			return maxName;
		}
	}

	public static boolean isValid(String input) {

		switch (input.toLowerCase()) {

		case "rock":
			return true;

		case "paper":
			return true;

		case "scissors":
			return true;

		default:
			return false;
		}
	}

	public static boolean checkWin(String user, String opponent) {

		if ((!isValid(user)) || (!isValid(opponent))) {
			return false;
		}

		String rock = "rock", paper = "paper", scissors = "scissors";

		if ((user.equalsIgnoreCase(rock)) && (opponent.equalsIgnoreCase(scissors))) {
			return true;
		}

		if ((user.equalsIgnoreCase(scissors)) && (opponent.equalsIgnoreCase(paper))) {
			return true;
		}

		if ((user.equalsIgnoreCase(paper)) && (opponent.equalsIgnoreCase(rock))) {
			return true;
		}

		return false;
		// If no possible win, assume loss.
	}

	/**********************************************
	 * Libraries
	 **********************************************/

	public static void displayInputs() {
		// One place to edit it all!
		print("Invalid user input!\nWrite rock, paper or scissors!");
	}

	public static void print(String text) {
		// Makes printing text easier
		System.out.println(text);
	}

	public static String quickFormat(String input) {
		// Just some quick function to shorten inputs.

		String output = input;

		switch (input.toLowerCase()) {

		case "r":
			output = "rock";
			break;

		case "p":
			output = "paper";
			break;

		case "s":
			output = "scissors";
			break;

		case "scissor":
			output = "scissors";
			break;
		}

		return output;
	}
}