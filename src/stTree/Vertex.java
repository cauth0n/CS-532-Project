package stTree;

public class Vertex {
	private Vertex parent, left, right;
	private double cost, flippedCost, updateCost;
	private boolean isFlipped, flippedUpdate;

	public Vertex(double cost) {
		this.cost = cost;
		updateCost = 0;
		flippedCost = cost;
		parent = null;
		left = null;
		right = null;
		isFlipped = false;
		flippedUpdate = false;
	}

	public boolean isRoot() {
		return (parent == null) || (parent.getLeft() != this && parent.getRight() != this);
	}

	public void normalize() {
		if (isFlipped) {
			isFlipped = true;
			updateCost = cost - updateCost;
			flippedUpdate = !flippedUpdate;
			if (left != null) {
				left.setFlipped(!left.isFlipped());
			}
			if (right != null) {
				right.setFlipped(!right.isFlipped());
			}
		}
	}

	public void update() {
		cost = flippedCost;
		updateCost = (flippedUpdate) ? flippedCost : 0; // assign updateCost to
														// flippedCost if
														// flippedUpdate is
														// true; else, set it to
														// 0
		if (left != null) {
			cost += left.getCost();
			if (left.isFlipped()) {
				updateCost += left.getCost() - left.getUpdateCost();
			} else {
				updateCost += left.getCost();
			}
		}
		if (right != null) {
			cost += right.getCost();
			if (right.isFlipped()) {
				updateCost += right.getCost() - right.getUpdateCost();
			} else {
				updateCost += right.getCost();
			}
		}
	}

	public boolean isFlipped() {
		return isFlipped;
	}

	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}

	public boolean isFlippedUpdate() {
		return flippedUpdate;
	}

	public void setFlippedUpdate(boolean flippedUpdate) {
		this.flippedUpdate = flippedUpdate;
	}

	public double getFlippedCost() {
		return flippedCost;
	}

	public void setFlippedCost(double flippedCost) {
		this.flippedCost = flippedCost;
	}

	public double getUpdateCost() {
		return updateCost;
	}

	public void setUpdateCost(double updateCost) {
		this.updateCost = updateCost;
	}

	public Vertex getLeft() {
		return left;
	}

	public void setLeft(Vertex left) {
		this.left = left;
	}

	public Vertex getRight() {
		return right;
	}

	public void setRight(Vertex right) {
		this.right = right;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
