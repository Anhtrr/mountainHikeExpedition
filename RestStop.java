package project5;

import java.util.ArrayList;

/**
 * This class represents a reststop at a given point in the mountain. Each
 * restStop is represented by its label that is passed through from the main
 * input file.
 * 
 * @author Anh Tran 12/10/2021
 *
 */
public class RestStop implements Comparable<RestStop> {

	// variables initiated - label stored as string
	// arraylist storing all supplies at a given reststop
	// arraylist storing all obstacles given at a reststop
	private String label;
	private ArrayList<String> supplies;
	private ArrayList<String> obstacles;

	/**
	 * This is the constructor method of a certain reststop. It calls directly to
	 * the private data fields.
	 * 
	 * @param label     - stores label of the restStop
	 * @param supplies  - stores all supplies given at a reststop
	 * @param obstacles - stores all obstacles given at a reststop
	 */
	public RestStop(String label, ArrayList<String> supplies, ArrayList<String> obstacles) {
		this.label = label;
		this.supplies = supplies;
		this.obstacles = obstacles;
	}

	/**
	 * This is the getter method for the label at a certain reststop
	 * 
	 * @return label - label at a certain reststop.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * This is the getter method for the supplies at a certain reststop
	 * 
	 * @return supplies - an arraylist storing all supplies at a reststop
	 */
	public ArrayList<String> getSupplies() {
		return supplies;
	}

	/**
	 * This is the getter method for the obstacles at a certain reststop
	 * 
	 * @return obstacles - an arraylist storing all obstacles at a reststop
	 */
	public ArrayList<String> getObstacles() {
		return obstacles;
	}

	/**
	 * This is the setter method for the label at a certain reststop
	 * 
	 * @param label - label at a certain reststop.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * This is the setter method for the arraylist of supplies at a certain
	 * reststop.
	 * 
	 * @param supplies - an arraylist storing all supplies at a reststop
	 */
	public void setSupplies(ArrayList<String> supplies) {
		this.supplies = supplies;
	}

	/**
	 * This is the setter method for the arraylist of obstacles at a certain
	 * reststop.
	 * 
	 * @param obstacles - an arraylist storing all obstacles at a reststop
	 */
	public void setObstacles(ArrayList<String> obstacles) {
		this.obstacles = obstacles;
	}

	/**
	 * This is the compareTo method used for alphabetical ordering of the labels at
	 * each reststop. This will be called in the BSTMountain class when labels are
	 * recieved from the input file, then added to construct the tree.
	 */
	@Override
	public int compareTo(RestStop o) {
		return (this.label).compareTo(o.getLabel());
	}

}
