package dynamicGraphFlow;

import graphBasics.Edge;
import graphBasics.Vertex;

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
import edu.uci.ics.jung.graph.Graph;

public class Simulator extends Operations {

	protected final int numVertices = 10;
	private final int numEdges = 5;

	private GraphVisualizer gv;
	private DynamicFlowGraph dfg;
	protected List<Vertex> vertices;
	private List<Edge> edges;

	public Simulator() {
		dfg = new DynamicFlowGraph();
		populate();
		getInitialMaxFlow();
		visualizeGraph(dfg.getGraph());
		// System.out.println("Cutting");
		// dfg.cut(dfg.getGraph().findEdge(v1, v2));
		// visualizeGraph(dfg.getGraph());
	}

	public void visualizeGraph(Graph<Vertex, Edge> graph) {
		gv = new GraphVisualizer(graph);
		gv.visualize();

	}

	public Vertex getRandVertex() {
		Random rand = new Random();
		return vertices.get(rand.nextInt(numVertices));
	}

	public void populate() {
		vertices = new ArrayList<>(numVertices);
		edges = new ArrayList<>(numEdges);

		for (int i = 0; i < numVertices; i++) {
			vertices.add(new Vertex(i + 1 + "", 8));
			dfg.add(vertices.get(i));
		}

		int i = 0;
		while (i < numEdges) {
			edges.add(new Edge(getRandomFlow(), 0));
			Vertex one = getRandVertex();
			Vertex two = getRandVertex();
			Edge isEdge = dfg.getGraph().findEdge(one, two);
			if (!(one == two)) {
				if (isEdge == null) {
					if ((dfg.getGraph().findEdge(two, one) == null)) {
						dfg.add(edges.get(i), one, two);
						System.out.println("Added from " + one.toString() + " to " + two.toString());
						if (isCycle(one, dfg.getGraph().getSuccessors(one))) {
							System.out.println("Put in a cycle");
							dfg.getGraph().removeEdge(edges.get(i));
							System.out.println("Removed from " + one.toString() + " to " + two.toString());
						} else {
							i++;
						}
					}
				}
			}
		}
	}

	public boolean isCycle(Vertex toVertex, Collection<Vertex> successors) {
		for (Vertex v : successors) {
			if (v == toVertex) {
				return true;
			} else {
				return isCycle(toVertex, dfg.getGraph().getSuccessors(v));
			}
		}
		return false;
	}

	public void getInitialMaxFlow() {
		// uses E-K to get the initial max flow of the graph.
		// needs to be done, using the E-K already implemented.
		Transformer<Edge, Number> edgeCapacities = new EdgeTransformer();
		Map<Edge, Number> edgeFlowMap = new HashMap<>();// getEdgeFlowMap();
		Factory<Edge> edgeFactory = new EdgeFactory();

		EdmondsKarpMaxFlow<Vertex, Edge> ek = new EdmondsKarpMaxFlow<Vertex, Edge>(dfg.getGraph(), vertices.get(0), vertices.get(numVertices - 1),
				edgeCapacities, edgeFlowMap, edgeFactory);

		System.out.println("Computing Max Flow");
		ek.evaluate();
		System.out.println("Computed Max Flow");
		revertFlowsFromMap(edgeFlowMap);
		dfg.setGraph(ek.getFlowGraph());
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
