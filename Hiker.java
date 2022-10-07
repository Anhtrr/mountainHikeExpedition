package mountainHikeExpedition;

import java.util.ArrayList;

/**
 * This class represents the hiker moving down the mountain. It is responsible
 * for keeping track of food, raft, and axe counts as the hiker moves through
 * the mountain. It is also responsible for updating these counts for every
 * reststop the hiker lands on.
 * 
 * @author Anh Tran - 12/10/2021
 *
 */
public class Hiker {

	private int foodCount;
	private int raftCount;
	private int axeCount;

	/**
	 * This is the getter method for the private data field foodcount.
	 * 
	 * @return foodCount - number of food that hiker has at a given RS.
	 */
	public int getFoodCount() {
		return foodCount;
	}

	/**
	 * This is the setter method for the private data field foodcount.
	 * 
	 * @param foodCount - number of food that hiker has at a given RS.
	 */
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

	/**
	 * This is the getter method for the private data field raftcount.
	 * 
	 * @return raftCount - number of rafts that hiker has at a given RS.
	 */
	public int getRaftCount() {
		return raftCount;
	}

	/**
	 * This is the setter method for the private data field raftcount.
	 * 
	 * @param raftCount - number of rafts that hiker has at a given RS.
	 */
	public void setRaftCount(int raftCount) {
		this.raftCount = raftCount;
	}

	/**
	 * This is the getter method for the private data field axecount.
	 * 
	 * @return axeCount - number of axes that hiker has at a given RS.
	 */
	public int getAxeCount() {
		return axeCount;
	}

	/**
	 * This is the setter method for the private data field axecount.
	 * 
	 * @param axeCount - number of axes that hiker has at a given RS.
	 */
	public void setAxeCount(int axeCount) {
		this.axeCount = axeCount;
	}

	// constructor method -- set all counts to 0
	/**
	 * This is the constructor method to create a default hiker. It sets all the
	 * counts to 0.
	 * 
	 */
	public Hiker() {
		this.foodCount = this.raftCount = this.axeCount = 0;
	}

	/**
	 * This method is responsible for checking supplies at each restStop that the
	 * hiker passes through. It will then add supplies to the hiker's inventory.
	 * 
	 * @param rs - a reststop that the hiker passes by.
	 */
	public void addRSCounts(RestStop rs) {

		// reference variable storing all supplies at a given reststop
		ArrayList<String> getSupplies = rs.getSupplies();

		// iterator value used to iterate through the supplies
		int iteratorVal = 0;

		// iterates through supplies at a certain RS
		while (getSupplies.size() > iteratorVal) {
			// if food, then add to hiker inventory
			if (getSupplies.get(iteratorVal).equals("food")) {
				this.foodCount += 1;
				iteratorVal += 1;
			}

			// if raft, then add to hiker inventory
			else if (getSupplies.get(iteratorVal).equals("raft")) {
				this.raftCount += 1;
				iteratorVal += 1;
			}

			// if axe, then add to hiker inventory.
			else if (getSupplies.get(iteratorVal).equals("axe")) {
				this.axeCount += 1;
				iteratorVal += 1;
			}
		}
	}

	// checks if the hiker has enough supplies for the RS
	/**
	 * This method is responsible for taking away the hiker's supplies for every
	 * obstacle that this hiker encounters. It also checks if the hiker has enough
	 * supplies to pass this reststop.
	 * 
	 * @param rs - reststop that hiker passes by
	 * @return true - if hiker can pass by the rest stop. returns false if hiker
	 *         does not have enough supplies to pass the obstacles at that rest
	 *         stop.
	 */
	public boolean removeRSCounts(RestStop rs) {

		// reference variable to store all obstacles at a given reststop
		ArrayList<String> getObstacles = rs.getObstacles();

		// iterates through the obstacles arraylist
		for (String obstacleX : getObstacles) {

			// if obstacle is river, then take away 1 raft from hiker inventory
			if (obstacleX.equals("river")) {
				if (raftCount >= 1) {
					setRaftCount(this.raftCount - 1);

					// if hiker does not have at least 1 raft, return false.
				} else if (raftCount < 1) {
					return false;
				}
			}

			// if obstacle is fallen tree, then take away 1 axe from hiker inventory
			if (obstacleX.equals("fallen tree")) {
				if (axeCount >= 1) {
					setAxeCount(this.axeCount - 1);

					// if hiker does not have at least 1 axe, return false.
				} else if (axeCount < 1) {
					return false;
				}
			}
		}

		// if hiker passes all checks, then hiker can get pass RS
		return true;
	}

	/**
	 * This method is used to check if the hiker has enough food to pass a RS.
	 * 
	 * @param rs - reststop that hiker passes by
	 * @return true - if hiker has at least 1 food. false, otherwise.
	 */
	public boolean checkFood(RestStop rs) {

		// if food count is -1, then hiker cannot pass RS
		if (this.foodCount < 0) {
			return false;
		} else {
			return true;
		}
	}

}
