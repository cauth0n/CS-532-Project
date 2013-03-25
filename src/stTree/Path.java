package stTree;

import java.util.LinkedList;

public class Path {
	private LinkedList<Vertex> path;

	public Path(Vertex initialVertex) {
		path = new LinkedList<Vertex>();
		path.add(initialVertex);
	}

	public Path(LinkedList<Vertex> path) {
		this.path = path;
	}

	public LinkedList<Vertex> getPath() {
		return path;
	}

	public void addToPath(Vertex v) {
		path.add(v);
	}

}
