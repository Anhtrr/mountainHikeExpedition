package project5;

/**
 * This class represents a Binary search tree with self-balancing features and
 * mountain implementation. It consists of a private node class that is used to
 * store reststop object as data, and left, right, height as variables. Most
 * features were taken from professor Klukowska's BST file on ed workspaces.
 * Other features were taken from professor Klukowska's slides directly. One
 * method was taken and influenced by Geeksforgeeks on finding maxLevel.
 * 
 * @author Anh Tran - 12/10/2021
 *
 */
public class BSTMountain {

	/**
	 * This is the private node class that is used to store reststop object as data,
	 * and left, right, height as variables. It also has getters and setters that
	 * will be implemented within the class. Although not neccessary, using getters
	 * and setters was better for practice for me.
	 * 
	 * @author Anh Tran - 12/10/2021
	 *
	 */
	private class Node implements Comparable<RestStop> {

		private RestStop data;
		private Node left;
		private Node right;
		private int height;

		/**
		 * This is the default constructor of a node itself. It creates an empty node
		 * with empty restStop data, empty left node, empty right node.
		 * 
		 * @param data - represents the reststop object stored at each node
		 */
		public Node(RestStop data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		/**
		 * This is the getter for reststop data stored in each node.
		 * 
		 * @return data - the reststop object stored at a node.
		 */
		public RestStop getData() {
			return data;
		}

		/**
		 * This is the getter method for the node stored at left reference of a node or
		 * in other words, stored to the left of the node in the tree.
		 * 
		 * @return left - the node that is stored directly to the left of a node.
		 */
		public Node getLeft() {
			return left;
		}

		/**
		 * This is the setter method for the node stored at left reference of a node or
		 * in other words, stored to the left of the node in the tree.
		 * 
		 * @param left - the node that is stored directly to the left of a node.
		 */
		public void setLeft(Node left) {
			this.left = left;
		}

		/**
		 * This is the getter method for the node stored at right reference of a node or
		 * in other words, stored to the right of the node in the tree.
		 * 
		 * @return right - the node that is stored directly to the right of a node.
		 */
		public Node getRight() {
			return right;
		}

		/**
		 * This is the setter method for the node stored at right reference of a node or
		 * in other words, stored to the right of the node in the tree.
		 * 
		 * @param right - the node that is stored directly to the right of a node.
		 */
		public void setRight(Node right) {
			this.right = right;
		}

		/**
		 * This is the getter method for the height of the tree at a certain node.
		 * 
		 * @return height - height of the tree at a certain node.
		 */
		public int getHeight() {
			return height;
		}

		/**
		 * This is the setter method for the height of the tree at a certain node.
		 * 
		 * @param height - height of the tree at a certain node.
		 */
		public void setHeight(int height) {
			this.height = height;
		}

		/**
		 * This is the compareTo method, used for natural alphabetical ordering the
		 * reststop labels in the tree.
		 */
		@Override
		public int compareTo(RestStop o) {
			return this.data.compareTo(o);
		}
	}

	// initiate variables: root - top of the tree, added to tell if a node has been
	// added,
	private Node root;
	private boolean added;

	/**
	 * creates an empty mountain object
	 */
	public BSTMountain() {
		root = null;
	}

	/**
	 * This is the wrapper method used to add a reststop rs, to the tree as a node.
	 * This was taken from professor Klukowska's BST Ed workspace file.
	 * 
	 * @param rs - a reststop object passed in from the main.
	 * @return true - if the rs/node has been added. false - if not added.
	 */
	public boolean addRS(RestStop rs) {
		added = false;
		if (rs == null) {
			return false;
		}

		root = addRSRec(rs, root);
		return added;
	}

	/**
	 * This is the actual recursive addreststop method. It is responsible for
	 * actually adding the node to the tree and rebalancing it when balancing factor
	 * goes out of range. It is also responsible for updating the height reference
	 * everytime a new node is added. This was taken from professor Klukowska's BST
	 * Ed workspace file.
	 * 
	 * @param rs   - reststop that is added, passed from wrapper method
	 * @param node - node that stores the reststop data.
	 * @return - returns the node that is added.
	 */
	private Node addRSRec(RestStop rs, Node node) {

		if (node == null) {
			added = true;
			return new Node(rs);
		}

		int comp = node.compareTo(rs);

		if (comp > 0) {
			node.setLeft(addRSRec(rs, node.left));
		}

		else if (comp < 0) {
			node.setRight(addRSRec(rs, node.getRight()));

		}

		else {
			added = false;
			return node;
		}
		updateHeight(node);
		return balanceTree(node);
	}

	/**
	 * This is the self-balancing method for the tree. It compares balance factors
	 * and then is responsible for deciding which rotation to use. This was taken
	 * from professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - checks every node that is added for balancefactor value and
	 *             decides if rotation is needed.
	 * @return node - returns the node that was balanced.
	 */
	public Node balanceTree(Node node) {
		if (balanceFactor(node) >= 2 && balanceFactor(node.getRight()) > -1) {
			node = balanceRR(node);
		}

		if (balanceFactor(node) >= 2 && balanceFactor(node.getRight()) < 0) {
			node = balanceRL(node);
		}

		if (balanceFactor(node) <= -2 && balanceFactor(node.getLeft()) < 1) {
			node = balanceLL(node);
		}

		if (balanceFactor(node) <= -2 && balanceFactor(node.getLeft()) > 0) {
			node = balanceLR(node);
		}
		return node;
	}

	/**
	 * This is the method to check the maxLevel of the mountain. This was directly
	 * influenced by a post on geeksforgeeks about finding the max depth of a
	 * mountain. This will later be used to decide when the hiker has reached the
	 * bottom of the mountain.
	 * 
	 * @param node - this is usually the root of the tree as the method iterates
	 *             through the entire tree but starts at root.
	 * @return the bottom level of the mountain.
	 */
	public int maxLevel(Node node) {

		if (node == null) {
			return -1;
		} else {
			int leftLevel = maxLevel(node.getLeft());
			int rightLevel = maxLevel(node.getRight());

			if (leftLevel > rightLevel) {
				return (leftLevel + 1);
			} else {
				return (rightLevel + 1);
			}
		}
	}

	/**
	 * This is the getHeight method, used for balancing factor of each node. This
	 * was taken from professor Klukowska's Balancing trees lecture slides.
	 * 
	 * 
	 * @param node - a node in the tree that will be used.
	 * @return the height of a certain node.
	 */
	public int getHeight(Node node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
	}

	// slides
	/**
	 * This method is to update height for every node. it is helpful for everytime a
	 * rotation is done to a node. This was taken from professor Klukowska's
	 * Balancing trees lecture slides.
	 * 
	 * @param node - a node to have its height updated.
	 */
	public void updateHeight(Node node) {
		if (node.getLeft() == null && node.getRight() == null) {
			node.setHeight(0);
		} else if (node.getLeft() == null) {
			node.setHeight(node.getRight().getHeight() + 1);
		} else if (node.getRight() == null) {
			node.setHeight(node.getLeft().getHeight() + 1);
		} else {
			node.setHeight(1 + Math.max(node.getLeft().getHeight(), node.getRight().getHeight()));
		}
	}

	/**
	 * This method dictates the balancing factor at a certain node. This was taken
	 * from professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - node to be used
	 * @return the balancing factor at a certain node.
	 */
	public int balanceFactor(Node node) {
		if (node.getRight() == null) {
			return -node.getHeight();
		}
		if (node.getLeft() == null) {
			return node.getHeight();
		}
		return node.getRight().getHeight() - node.getLeft().getHeight();
	}

	/**
	 * This is the method used for LL rotation on a node. This was taken from
	 * professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - node to be rotated
	 * @return the node, left to the node.
	 */
	public Node balanceLL(Node node) {
		Node left = node.getLeft();

		node.setLeft(left.getRight());
		left.setRight(node);

		updateHeight(node);
		updateHeight(left);

		return left;
	}

	/**
	 * This is the method used for LR rotation on a node. This was taken from
	 * professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - node to be rotated
	 * @return the node, right to the node.
	 */
	public Node balanceLR(Node node) {
		Node left = node.getLeft();
		Node right = left.getRight();

		node.setLeft(right.getRight());
		left.setRight(right.getLeft());
		right.setLeft(left);
		right.setRight(node);

		updateHeight(node);
		updateHeight(right);
		updateHeight(left);

		return right;
	}

	/**
	 * This is the method used for RR rotation on a node. This was taken from
	 * professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - node to be rotated
	 * @return the node, right to the node.
	 */
	public Node balanceRR(Node node) {
		Node right = node.getRight();

		node.setRight(right.getLeft());
		right.setLeft(node);

		updateHeight(node);
		updateHeight(right);

		return right;
	}

	/**
	 * This is the method used for RL rotation on a node. This was taken from
	 * professor Klukowska's Balancing trees lecture slides.
	 * 
	 * @param node - node to be rotated
	 * @return the node, left to the node.
	 */
	public Node balanceRL(Node node) {
		Node right = node.getRight();
		Node left = right.getLeft();

		node.setRight(left.getLeft());
		right.setLeft(left.getRight());
		left.setRight(right);
		left.setLeft(node);

		updateHeight(node);
		updateHeight(right);
		updateHeight(left);

		return left;
	}

	/**
	 * This is the wrapper method for moving the hiker along the mountain. It is
	 * responsible for intiating the start point as the root, creating a new Hiker
	 * object and starting the hiker at level 0.
	 * 
	 */
	public void moveHiker() {
		Node start = root;
		Hiker hiker = new Hiker();
		int hikerLevel = 0;

		moveHikerRec(start, hiker, hikerLevel, "");
	}

	// mountainLevel - stores the max level of the tree or the level that a hiker
	// has to reach
	// in order to get to the bottom of the mountain.
	private int mountainLevel;

	/**
	 * This is the actual recursive method for moving the hiker along the mountain.
	 * It is responsible for finding correct answers and ways through to the max
	 * level of the mountain. It will then print the answers.
	 * 
	 * @param node       - a node as the start point of the hiker.
	 * @param hiker      - a new hiker object that will be moving through the
	 *                   mountain.
	 * @param hikerLevel - tracks the hikerLevel and sees when it reaches the bottom
	 *                   - mountainLevel.
	 * @param answers    - stores all the ways through the mountain.
	 */
	public void moveHikerRec(Node node, Hiker hiker, int hikerLevel, String answers) {
		if (node == null) {
			return;
		}

		// store reststop data at a certain node as rs for simplicity.
		RestStop rs = node.getData();

		// call the hiker class to add all supplies at a certain reststop
		hiker.addRSCounts(rs);

		// set mountainLevel (endpoint) to the maxLevel of the tree.
		mountainLevel = maxLevel(root);

		// If the bottom is reached, then print out answers.
		if (node.getLeft() == null && node.getRight() == null) {
			if (hiker.removeRSCounts(rs) == true) {
				if (hikerLevel == mountainLevel) {
					answers += rs.getLabel();
					System.out.println(answers);
				}
			}
		}

		// If the bottom is not reached, then continue.
		else if (hiker.removeRSCounts(rs) == true) {
			// check if hiker still has at least 1 food.
			if (hiker.checkFood(rs) == true) {
				// take away 1 food for every reststop
				hiker.setFoodCount(hiker.getFoodCount() - 1);

				// append label to answer if hiker can survive the rs
				answers += rs.getLabel();

				// add space to answer for formatting
				answers += " ";

				// store next hiker level to call function recursively
				int nextHikerLevel = hikerLevel + 1;

				// keep track of current counts in case hiker cannot pass the next one.
				int currentFoodCount = hiker.getFoodCount();
				int currentRaftCount = hiker.getRaftCount();
				int currentAxeCount = hiker.getAxeCount();

				// move hiker left recursively.
				moveHikerRec(node.getLeft(), hiker, nextHikerLevel, answers);

				// if hiker gets past left, then set counts to new count.
				hiker.setFoodCount(currentFoodCount);
				hiker.setRaftCount(currentRaftCount);
				hiker.setAxeCount(currentAxeCount);

				// move hiker right recursively
				moveHikerRec(node.getRight(), hiker, nextHikerLevel, answers);
			}
		}
	}

}