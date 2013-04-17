package dynamicGraphFlow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import driver.GraphVisualizer;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import graphBasics.Edge;
import graphBasics.Vertex;

public class Simulator extends Operations {

	private GraphVisualizer gv;
	private DynamicFlowGraph dfg;

	private DirectedGraph<Vertex, Edge> graph;

	public Simulator() {
		graph = manualPopulate();// populate();
		dfg = new DynamicFlowGraph(graph);

		visualizeGraph(dfg.getGraph());
		DynamicFlowGraph initialFlow = new DynamicFlowGraph(getEKMaxFlow(graph));
		// System.out.println("Cutting");
		// dfg.cut(dfg.getGraph().findEdge(v1, v2));
		// visualizeGraph(dfg.getGraph());
		// visualizeGraph(initialFlow.getGraph());
		fillVertexFlows();
		// Edge e = getRandEdge();
		// dfg.cut(e);

		Edge edge = edges.get(0);
		dfg.cut(edge);

		visualizeGraph(dfg.getGraph());
		fillVertexFlows();

		/*
		 * The constantly updating E-K will look something like this:
		 */
		// getMaxFlow(graph);
		// visualizeGraph(dfg.getGraph());
		/*
		 * 
		 */
	}

	public void fillVertexFlows() {
		for (Vertex v : vertices) {
			int flowSum = 0;
			for (Edge e : graph.getInEdges(v)) {
				flowSum += e.getCurrentFlow();
			}
			v.setFlow(flowSum);
		}
	}

	public DirectedGraph<Vertex, Edge> manualPopulate() {
		DirectedGraph<Vertex, Edge> graph = new DirectedSparseGraph<>();
		vertices = new ArrayList<>();
		edges = new ArrayList<>();

		Vertex v1 = new Vertex("1", 0);
		Vertex v2 = new Vertex("2", 0);
		Vertex v3 = new Vertex("3", 0);
		Vertex v4 = new Vertex("4", 0);
		Vertex v5 = new Vertex("5", 0);

		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		vertices.add(v5);

		Edge e1 = new Edge(5, 0);
		Edge e2 = new Edge(20, 0);
		Edge e3 = new Edge(20, 0);
		Edge e4 = new Edge(20, 0);
		Edge e5 = new Edge(5, 0);
		Edge e6 = new Edge(5, 0);
		Edge e7 = new Edge(5, 0);
		Edge e8 = new Edge(10, 0);
		Edge e9 = new Edge(10, 0);
		Edge e10 = new Edge(25, 0);

		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		edges.add(e8);
		edges.add(e9);
		edges.add(e10);

		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);

		graph.addEdge(e1, v1, v2);
		graph.addEdge(e2, v1, v3);
		graph.addEdge(e3, v1, v4);
		graph.addEdge(e4, v1, v5);
		graph.addEdge(e5, v2, v3);
		graph.addEdge(e6, v2, v4);
		graph.addEdge(e7, v2, v5);
		graph.addEdge(e8, v3, v4);
		graph.addEdge(e9, v3, v5);
		graph.addEdge(e10, v4, v5);

		return graph;
	}

	public Edge getRandEdge() {
		Random rand = new Random();
		return edges.get(rand.nextInt(numEdges));
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
			vertices.add(new Vertex(i + 1 + "", 0));
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

}
