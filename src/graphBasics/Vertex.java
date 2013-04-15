package graphBasics;

public class Vertex {

	private int outFlow;
	private String id;

	public Vertex(String id, int outFlow) {
		this.id = id;
		this.outFlow = outFlow;
	}

	public String toString() {
		return id + " ___ " + outFlow;
	}

	public int getCost() {
		return outFlow;
	}

	public void setFlow(int outFlow) {
		this.outFlow = outFlow;
	}

}
