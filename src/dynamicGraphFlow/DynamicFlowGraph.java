package dynamicGraphFlow;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import graphBasics.Edge;
import graphBasics.Vertex;

public class DynamicFlowGraph {

	private final EdgeType d = EdgeType.DIRECTED;
	DirectedSparseGraph<Vertex, Edge> graph;

	public DynamicFlowGraph() {
		graph = new DirectedSparseGraph<>();
	}

	public void add(Vertex v) {
		graph.addVertex(v);
	}

	public void add(Edge e, Vertex one, Vertex two) {
		graph.addEdge(e, one, two, d);
	}

	public void cut(Edge e){
		Vertex one = graph.getEndpoints(e).getFirst();
		Vertex two = graph.getEndpoints(e).getSecond();
		Edge e1 = new Edge(e.getCapacity(), e.getCurrentFlow());
		Edge e2 = new Edge(e.getCapacity(), e.getCurrentFlow());
		graph.removeEdge(e);
		Vertex v = new Vertex("3", 0);
		add(e1, one, v);
		//add(e2, v, two);

	}

	public DirectedSparseGraph<Vertex, Edge> getGraph() {
		return graph;
	}

}