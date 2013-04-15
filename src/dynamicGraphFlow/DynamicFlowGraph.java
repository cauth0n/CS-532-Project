package dynamicGraphFlow;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import graphBasics.Edge;
import graphBasics.Vertex;

public class DynamicFlowGraph extends Operations {

	private final EdgeType d = EdgeType.DIRECTED;
	private DirectedGraph<Vertex, Edge> graph;

	public DynamicFlowGraph(DirectedGraph<Vertex, Edge> graph) {
		this.graph = graph;
	}

	public DynamicFlowGraph(DynamicFlowGraph another) {
		this(another.getGraph());
	}

	public void add(Vertex v) {
		graph.addVertex(v);
	}

	public void add(Edge e, Vertex one, Vertex two) {
		graph.addEdge(e, one, two, d);
	}

	public void cut(Edge e) {
		Vertex one = graph.getEndpoints(e).getFirst();
		Vertex two = graph.getEndpoints(e).getSecond();
		Edge e1 = new Edge(0, 0);
		graph.removeEdge(e);
		Vertex v = new Vertex("new", 0);
		add(e1, one, v);
		cascadeFlowToChildren(two, e.getCurrentFlow());
		boolean isWeakEdge = true;
		for (Edge ePrime : graph.getOutEdges(one)) {
			if (ePrime.getCurrentFlow() < e.getCurrentFlow()) {
				isWeakEdge = false; // another edge has less flow.
			}
		}
		if (isWeakEdge) {
			System.out.println("Found a weak edge. ");
			// run E-K on graph again
		}
	}

	public void cascadeFlowToChildren(Vertex v, int flowToCascade) {
		if (v == null || graph.getOutEdges(v) == null) {
			return;
		}
		int flowRemaining = flowToCascade;
		for (Edge e : graph.getOutEdges(v)) {
			while (flowRemaining > 0) {
				if (e.getCurrentFlow() <= flowRemaining) {
					flowRemaining -= e.getCurrentFlow();
					e.setCapacity(0);
					e.setCurrentFlow(0);
				} else {
					e.setCapacity(e.getCurrentFlow() - flowRemaining);
					e.setCurrentFlow(e.getCurrentFlow() - flowRemaining);
					flowRemaining = 0;
				}
			}

		}
		for (Edge e : graph.getOutEdges(v)) {
			cascadeFlowToChildren(graph.getDest(e), flowToCascade);
		}
	}

	public DirectedGraph<Vertex, Edge> getGraph() {
		return graph;
	}

}
