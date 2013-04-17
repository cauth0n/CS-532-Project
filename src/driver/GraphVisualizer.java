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

	private JFrame frame;
	private BasicVisualizationServer<Vertex, Edge> vv;
	private Layout<Vertex, Edge> layout;

	public GraphVisualizer(Graph<Vertex, Edge> graph) {
		frame = new JFrame("Simple graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new CircleLayout<>(graph);

		layout.setSize(new Dimension(600, 600));
		vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(600, 600));

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());

		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());

		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
