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
		cascadeFlowToChildren(two);
		cascadeFlowFromParent(one, e.getCurrentFlow());
	}

	public void cascadeFlowFromParent(Vertex v, int flow) {
		int remainingFlow = flow;
		if (v == null) {
			return;
		}
		for (Edge e : graph.getOutEdges(v)) {
			while (remainingFlow >= 0 && e.getCurrentFlow() >= 0) {
				e.decrementFlow();
				remainingFlow--;
			}
			//This goes in a DFS manner. It does not matter if BFS or DFS.
			cascadeFlowFromParent(graph.getEndpoints(e).getSecond(), flow);	//get the second vertex, updating the entire flow.
		}
	}

	public void cascadeFlowToChildren(Vertex v) {
		if (v == null || graph.getOutEdges(v) == null) {
			return;
		}
		for (Edge e : graph.getOutEdges(v)) {
			e.setCurrentFlow(0);
			cascadeFlowToChildren(graph.getDest(e));
		}
	}

	public void updateChildren(Vertex v, int flowRemoved) {

	}

	public DirectedGraph<Vertex, Edge> getGraph() {
		return graph;
	}

}
