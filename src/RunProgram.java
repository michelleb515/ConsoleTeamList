import java.util.List;
import java.util.Scanner;

import controller.ListTeamHelper;
import model.ListTeam;

public class RunProgram {
	static Scanner in = new Scanner(System.in);
	static ListTeamHelper lih = new ListTeamHelper();

	private static void addAPlayer() {
		// TODO Auto-generated method stub
		System.out.print("Enter a player name: ");
		String name = in.nextLine();
		System.out.print("Enter player's jersey number: ");
		int jersey = in.nextInt();
		System.out.print("Enter player's weight: ");
		int weight = in.nextInt();
		
		//wrap data into a ListTeam object and send to method to persist
		ListTeam toAdd = new ListTeam(name, jersey, weight);
		lih.insertPlayer(toAdd);
	}

	private static void deleteAPlayer() {
		// TODO Auto-generated method stub
		System.out.print("Enter the name of player to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the jersey number of player to be deleted: ");
		int jersey = in.nextInt();
		System.out.print("Enter the weight of player to be deleted: ");
		int weight = in.nextInt();
		
		ListTeam toDelete = new ListTeam(name, jersey, weight);
		lih.deletePlayer(toDelete);

	}

	private static void editAPlayer() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by player name");
		System.out.println("2 : Search by player's jersey number");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListTeam> foundPlayers;
		if (searchBy == 1) {
			System.out.print("Enter the player's name: ");
			String playerName = in.nextLine();
			foundPlayers = lih.searchForPlayerByName(playerName);
		} else {
			System.out.print("Enter the player's jersey number: ");
			int playerJersey = in.nextInt();
			foundPlayers = lih.searchForPlayerByJersey(playerJersey);
		}

		if (!foundPlayers.isEmpty()) {
			System.out.println("Found Results.");
			for (ListTeam l : foundPlayers) {
				System.out.println(l.getId() + " : " + l.returnPlayerDetails());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListTeam toEdit = lih.searchForPlayerById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName() + " Jersey " + toEdit.getJersey() + " Weight " + toEdit.getWeight());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Jersey");
			System.out.println("3 : Update Weight");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 2) {
				System.out.print("New Jersey Number: ");
				int newJersey = in.nextInt();
				toEdit.setJersey(newJersey);
			} else if (update == 3) {
				System.out.print("New Weight: ");
				int newWeight = in.nextInt();
				toEdit.setWeight(newWeight);
			}

			lih.updatePlayer(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the Team Roster App! ---");
		while (goAgain) {
			System.out.println("*  Select a player:");
			System.out.println("*  1 -- Add a player");
			System.out.println("*  2 -- Edit a player");
			System.out.println("*  3 -- Delete a player");
			System.out.println("*  4 -- View the team list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAPlayer();
			} else if (selection == 2) {
				editAPlayer();
			} else if (selection == 3) {
				deleteAPlayer();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<ListTeam> allPlayers = lih.showAllPlayers();
		for(ListTeam singlePlayer : allPlayers){
		System.out.println(singlePlayer.returnPlayerDetails());
		}

	}

}
