package dynamicGraphFlow;

import driver.GraphVisualizer;
import edu.uci.ics.jung.graph.Graph;
import graphBasics.Edge;
import graphBasics.Vertex;

public class Simulator {

	GraphVisualizer gv;
	DynamicFlowGraph dfg;
	Vertex v1, v2;

	public Simulator() {
		dfg = new DynamicFlowGraph();
		populate();
		visualizeGraph(dfg.getGraph());
		System.out.println("Cutting");
		dfg.cut(dfg.getGraph().findEdge(v1, v2));
		visualizeGraph(dfg.getGraph());
	}

	public void visualizeGraph(Graph<Vertex, Edge> graph) {
		gv = new GraphVisualizer(graph);
		gv.visualize();

	}

	public void populate() {
		v1 = new Vertex("1", 0);
		v2 = new Vertex("2", 5);
		Edge e1 = new Edge(20, 10);
		dfg.add(v1);
		dfg.add(v2);
		dfg.add(e1, v1, v2);
	}

}
