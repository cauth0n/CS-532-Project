package graphBasics;

public class Edge {

	private int capacity;
	private int currentFlow;

	public Edge(int capacity, int currentFlow) {
		this.capacity = capacity;
		this.currentFlow = currentFlow;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCurrentFlow() {
		return currentFlow;
	}

	public void setCurrentFlow(int currentFlow) {
		this.currentFlow = currentFlow;
	}
	
	public String toString(){
		return "" + currentFlow + "/" + capacity;
	}

}
