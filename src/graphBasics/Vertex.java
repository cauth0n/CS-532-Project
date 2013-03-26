package graphBasics;

public class Vertex {

	private double cost;

	private String id;

	public Vertex(String id, double cost) {
		this.id = id;
		this.cost = cost;
	}

	public String toString() {
		return id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
