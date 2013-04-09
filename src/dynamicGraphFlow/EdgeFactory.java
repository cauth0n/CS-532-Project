package dynamicGraphFlow;

import graphBasics.Edge;

import org.apache.commons.collections15.Factory;

public class EdgeFactory extends Operations implements Factory<Edge> {

	@Override
	public Edge create() {
		return new Edge(getRandomFlow(), 0);
	}

}
