package dynamicGraphFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.map.HashedMap;

import edu.uci.ics.jung.algorithms.flows.EdmondsKarpMaxFlow;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import graphBasics.Edge;
import graphBasics.Vertex;

public abstract class Operations {

	protected final EdgeType d = EdgeType.DIRECTED;
	protected final int numVertices = 512;
	protected final int numEdges = (numVertices * (numVertices - 1)) / 2;
	protected List<Vertex> vertices;
	protected List<Edge> edges;
	protected final int maxFlow = 100;
	protected final int numCuts = 10;

	public int getRandomFlow() {
		Random rand = new Random();
		return rand.nextInt(maxFlow);
	}

	public DirectedGraph<Vertex, Edge> getEKMaxFlow(DirectedGraph<Vertex, Edge> originalGraph) {

		Transformer<Edge, Number> edgeCapacities = new EdgeTransformer();
		Map<Edge, Number> edgeFlowMap = new HashMap<>();// getEdgeFlowMap();
		Factory<Edge> edgeFactory = new EdgeFactory();
		DynamicFlowGraph duplicateGraph = new DynamicFlowGraph(originalGraph);

		EdmondsKarpMaxFlow<Vertex, Edge> ek = new EdmondsKarpMaxFlow<Vertex, Edge>(duplicateGraph.getGraph(), vertices.get(0),
				vertices.get(numVertices - 1), edgeCapacities, edgeFlowMap, edgeFactory);

		System.out.println("Computing Max Flow");
		ek.evaluate();
		System.out.println("Computed Max Flow");
		revertFlowsFromMap(edgeFlowMap);
		return ek.getFlowGraph();
	}

	public Map<Edge, Number> getEdgeFlowMap() {
		Map<Edge, Number> edgeFlowMap = new HashedMap<>();
		for (Edge e : edges) {
			edgeFlowMap.put(e, e.getCurrentFlow());
		}
		return edgeFlowMap;
	}

	public void revertFlowsFromMap(Map<Edge, Number> edgeFlowMap) {
		for (Edge e : edgeFlowMap.keySet()) {
			e.setCurrentFlow(edgeFlowMap.get(e).intValue());
		}
	}

}
