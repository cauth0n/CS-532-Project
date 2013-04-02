package driver;

import graphBasics.Vertex;

public class JungTest {

	Vertex one = new Vertex("1", 3);
	Vertex two = new Vertex("2", 2);
	Vertex three = new Vertex("3", -2);
	Vertex four = new Vertex("4", 5);
	DelegateTreeWithPaths<Vertex, String> graph;
	GraphVisualizer gv;

	public JungTest() {
		graph = new DelegateTreeWithPaths<>();
		// gv = new GraphVisualizer(graph);
		// gv.go();
		// System.out.println(graph.getParent(three));
		// gv.go();
	}

	public DelegateTreeWithPaths<Vertex, String> buildGraph() {

		DelegateTreeWithPaths<Vertex, String> g2 = new DelegateTreeWithPaths<Vertex, String>();

		g2.addVertex(one);

		g2.addChild("Eone", one, two);
		g2.addChild("Etwo", two, three);
		g2.addChild("Ethree", one, four);
		return g2;
	}

	public Vertex findRoot() {
		return graph.getRoot();
	}

	public void makeTree(Vertex v) {
		graph.addVertex(v);
	}
}
