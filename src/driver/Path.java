package driver;

import graphBasics.Vertex;

import java.util.LinkedList;

public class Path {

	private LinkedList<Vertex> path;

	public Path(Vertex v) {
		path = new LinkedList<>();
		path.add(v);
	}

	public LinkedList<Vertex> getPath() {
		return path;
	}
	
	public Vertex getTail(){
		return path.getLast();
	}

}
