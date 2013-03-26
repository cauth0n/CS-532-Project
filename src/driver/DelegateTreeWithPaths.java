package driver;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.DelegateTree;
import graphBasics.Vertex;

public class DelegateTreeWithPaths<V, E> extends DelegateTree<V, E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Path> paths;

	public DelegateTreeWithPaths() {
		super();
		paths = new LinkedList<>();
	}

	public void makePath(Vertex v) {
		paths.add(new Path(v));
	}

	public Path findPath(Vertex v) {
		for (Path p : paths) {
			for (Vertex vert : p.getPath()) {
				if (vert.equals(v)) {
					return p;
				}
			}
		}
		System.out.println("Did not find vertex in any paths.");
		return null;
	}

}
