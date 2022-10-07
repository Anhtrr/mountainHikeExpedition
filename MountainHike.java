package mountainHikeExpedition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the MountainHike program itself. It consists of the
 * main method and is responsible for validating the input file, validating
 * supplies/ obstacles offered, adding them to an arraylist which is then passed
 * to the BSTMountain object constructor.
 * 
 * @author Anh Tran - 12/10/2021
 */
public class MountainHike {

	/**
	 * This is the main method. It is firstly responsible for validating that the
	 * input file is readable. Then, it will read line by line to find the label of
	 * each RestStop, storing them in a string variable. Then it finds the obstacles
	 * and supplies at each RestStop, storing them in arrayList variables. Most of
	 * this method's file validation process was taken from my project 2 file and
	 * inspired by professor Klukowska's project 1 file. After this, it calls the
	 * BSTMountain class by constructing a new mountain with data from the input
	 * file. It then also calls the hiker to move through the mountain.
	 * 
	 * @param args - this parameter represents the path to the input file that will
	 *             be used to construct the mountain that is used throughout
	 *             MountainHike.
	 */
	public static void main(String[] args) {

		// taken from my proj 2
		// verifies that the command line args is not empty.
		if (args.length < 1) {
			System.err.println("Error: MountainHike expects an argument.\n");
			System.exit(1);
		}

		// taken from my proj 2
		// the first input of the command line argument will represent the file.
		// this references the input file as treeFile.
		File treeFile = new File(args[0]);

		// taken from my proj 2
		// this verifies that the input file actually exists. Otherwise, program exits
		// and print error
		// through the err stream.
		if (!treeFile.exists()) {
			System.err.println("Error: the file " + treeFile.getAbsolutePath() + "does not exist.\n");
			System.exit(1);
		}

		// taken from my proj 2
		// this verifies that the input file can actually be read. Otherwise, program
		// exits and print error
		// through the err stream.
		if (!treeFile.canRead()) {
			System.err.println("Error: MountainHike can not open the file " + treeFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		// creates a scanner object called inTree, used to read the treeFile.
		Scanner inTree = null;

		// taken from my proj 2
		// this try catch block verifies that the scanner can actually open the file to
		// read its content.
		// Otherwise, program exits and print error through the err stream.
		try {
			inTree = new Scanner(treeFile);

		} catch (FileNotFoundException e) {
			System.err.println("Error: MountainHike can not open the file " + treeFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		// creates a new, empty BSTMountain object named newMountain
		BSTMountain newMountain = new BSTMountain();

		// creates a reference string called line to store each line that is read in the
		// input file.
		String line;

		// while loop continues for every line in the input file.
		while (inTree.hasNext() == true) {

			// use reference String line to store each nextline of the input file
			line = inTree.nextLine();

			// arraylists to store all obstacles and supplies at each given reststop
			ArrayList<String> obstacles = new ArrayList<>();
			ArrayList<String> supplies = new ArrayList<>();

			// checks that the line has more than just a label
			if (line.split(" ").length >= 2) {
				for (int i = 1; i < line.split(" ").length; i++) {

					// supplies have to come before obstacles
					if (obstacles.isEmpty() == true) {
						// food supply check
						if (line.split(" ")[i].equals("food")) {
							supplies.add("food");
						}

						// raft supply check
						if (line.split(" ")[i].equals("raft")) {
							supplies.add("raft");
						}

						// axe supply check
						if (line.split(" ")[i].equals("axe")) {
							supplies.add("axe");
						}
					}

					// checks that fallen tree is written fully and not just "fallen"
					if (line.split(" ")[i].equals("fallen")) {
						if ((i + 1) != line.split(" ").length) {
							if (line.split(" ")[i + 1].equals("tree")) {
								obstacles.add("fallen tree");
							}

						}
					}
					// checks for river obstacle
					if (line.split(" ")[i].equals("river")) {
						obstacles.add("river");
					}
				}
			}

			// creates a new rest stop with every line of the input file -- first element
			// of each line will always be label
			RestStop newRS = new RestStop(line.split(" ")[0], supplies, obstacles);

			// creates new mountain under reststop
			newMountain.addRS(newRS);
		}

		// initiates hiker to move down the mountain created
		newMountain.moveHiker();

	}

}
