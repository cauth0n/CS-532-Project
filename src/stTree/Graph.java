package stTree;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private List<Path> paths;

	public Graph() {
		paths = new LinkedList<Path>();
	}

	public void makeTree(Vertex v) {
		makePath(v);
		v.setSuccessor(null);
	}

	public void expose(Vertex v) {
		Path p, q, r;
		Vertex w;
		p = new Path(new LinkedList<Vertex>());
		do {
			//w = findPath(v).g
			
			
		} while (v != null);
	}

	public void makePath(Vertex v) {

	}

	public Path findPath(Vertex v) {
		for (Path path : paths) {
			for (Vertex vert : path.getPath()) {
				
			}
		}
		return null;
	}

	public Vertex findTail(Path p) {
		return p.getPath().getLast();
	}

	public void printGraph() {
		
	}

}
