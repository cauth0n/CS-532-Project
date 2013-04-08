package dynamicGraphFlow;

import graphBasics.Edge;
import graphBasics.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import driver.GraphVisualizer;
import edu.uci.ics.jung.graph.Graph;

public class Simulator {
	private final int numVertices = 10;
	private final int numEdges = 20;
	private final int maxFlow = 100;

	private GraphVisualizer gv;
	private DynamicFlowGraph dfg;
	private List<Vertex> vertices;
	private List<Edge> edges;

	public Simulator() {
		dfg = new DynamicFlowGraph();
		populate();
		visualizeGraph(dfg.getGraph());
		// System.out.println("Cutting");
		System.out.println(dfg.getGraph().getEdgeCount());
		// dfg.cut(dfg.getGraph().findEdge(v1, v2));
		// visualizeGraph(dfg.getGraph());
	}

	public void visualizeGraph(Graph<Vertex, Edge> graph) {
		gv = new GraphVisualizer(graph);
		gv.visualize();

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
	}

	public Vertex getRandVertex() {
		Random rand = new Random();
		return vertices.get(rand.nextInt(numVertices));
	}

	public int getRandomFlow() {
		Random rand = new Random();
		return rand.nextInt(maxFlow);
	}

}
