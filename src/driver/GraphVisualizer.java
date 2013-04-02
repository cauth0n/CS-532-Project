package driver;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import graphBasics.Edge;
import graphBasics.Vertex;

public class GraphVisualizer {
	private Graph<Vertex, Edge> graph;


	public GraphVisualizer(Graph<Vertex, Edge> graph) {
		this.graph = graph;
	}

	public void visualize() {
		Layout<Vertex, Edge> layout = new CircleLayout<>(graph);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<Vertex, Edge> vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		JFrame frame = new JFrame("Simple graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
