package dynamicGraphFlow;

import graphBasics.Edge;

import org.apache.commons.collections15.Transformer;

public class EdgeTransformer implements Transformer<Edge, Number> {

	@Override
	public Number transform(Edge e) {
		return e.getCapacity();
	}

}
