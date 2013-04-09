package dynamicGraphFlow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.map.HashedMap;

import driver.GraphVisualizer;
import edu.uci.ics.jung.algorithms.flows.EdmondsKarpMaxFlow;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import graphBasics.Edge;
import graphBasics.Vertex;

public class Simulator extends Operations {

	private final EdgeType d = EdgeType.DIRECTED;
	protected final int numVertices = 5;
	private final int numEdges = (numVertices * (numVertices - 1)) / 2;

	private GraphVisualizer gv;
	private DynamicFlowGraph dfg;
	private List<Vertex> vertices;
	private List<Edge> edges;
	private DirectedGraph<Vertex, Edge> graph;

	public Simulator() {
		graph = populate();
		dfg = new DynamicFlowGraph(graph);

		visualizeGraph(dfg.getGraph());
		DynamicFlowGraph initialFlow = new DynamicFlowGraph(getInitialMaxFlow(graph));
		// System.out.println("Cutting");
		// dfg.cut(dfg.getGraph().findEdge(v1, v2));
		visualizeGraph(dfg.getGraph());
		visualizeGraph(initialFlow.getGraph());
	}

	public void visualizeGraph(Graph<Vertex, Edge> graph) {
		gv = new GraphVisualizer(graph);
	}

	public Vertex getRandVertex() {
		Random rand = new Random();
		return vertices.get(rand.nextInt(numVertices));
	}

	public DirectedGraph<Vertex, Edge> populate() {
		DirectedGraph<Vertex, Edge> graph = new DirectedSparseGraph<>();
		vertices = new ArrayList<>(numVertices);
		edges = new ArrayList<>(numEdges);

		for (int i = 0; i < numVertices; i++) {
			vertices.add(new Vertex(i + 1 + "", 8));
			graph.addVertex(vertices.get(i));
		}

		int i = 0;
		while (i < numEdges) {
			edges.add(new Edge(getRandomFlow(), 0));
			Vertex one = getRandVertex();
			Vertex two = getRandVertex();
			Edge isEdge = graph.findEdge(one, two);
			if (!(one == two)) {
				if (isEdge == null) {
					if ((graph.findEdge(two, one) == null)) {
						graph.addEdge(edges.get(i), one, two, d);
						System.out.println("Added from " + one.toString() + " to " + two.toString());
						if (isCycle(one, graph.getSuccessors(one), graph)) {
							System.out.println("Put in a cycle");
							graph.removeEdge(edges.get(i));
							System.out.println("Removed from " + one.toString() + " to " + two.toString());
						} else {
							i++;
						}
					}
				}
			}
		}
		return graph;
	}

	public boolean isCycle(Vertex toVertex, Collection<Vertex> successors, DirectedGraph<Vertex, Edge> graph) {
		for (Vertex v : successors) {
			if (v == toVertex) {
				return true;
			} else {
				return isCycle(toVertex, graph.getSuccessors(v), graph);
			}
		}
		return false;
	}

	public DirectedGraph<Vertex, Edge> getInitialMaxFlow(DirectedGraph<Vertex, Edge> originalGraph) {
		// uses E-K to get the initial max flow of the graph.
		// needs to be done, using the E-K already implemented.

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
