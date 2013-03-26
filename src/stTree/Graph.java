package stTree;

import graphBasics.Vertex;

/**
 * http://www.cs.cmu.edu/~avrim/451/lectures/lect1009-linkcut.txt
 * 
 * @author derek.reimanis
 * 
 */

public class Graph {

	public Graph() {

	}

	public void rotateRight(Vertex p) {
		Vertex q = p.getParent();
		Vertex r = q.getParent();
		p.normalize();
		q.normalize();
		q.setLeft(p.getRight());
		if (q.getLeft() != null) {
			q.getLeft().setParent(p);
		}
		p.setRight(q);
		q.setParent(p);
		p.setParent(r);
		if (p.getParent() != null) {
			if (r.getLeft() == q) {
				r.setLeft(p);
			} else if (r.getRight() == q) {
				r.setRight(p);
			}
		}
		q.update();
	}

	public void rotateLeft(Vertex p) {
		Vertex q = p.getParent();
		Vertex r = q.getParent();
		p.normalize();
		q.normalize();
		q.setRight(p.getLeft());
		if (q.getRight() != null) {
			q.getRight().setParent(p);
		}
		p.setLeft(q);
		q.setParent(p);
		p.setParent(r);
		if (p.getParent() != null) {
			if (r.getLeft() == q) {
				r.setLeft(p);
			} else if (r.getRight() == q) {
				r.setRight(p);
			}
		}
		q.update();
	}

	public void splay(Vertex p) {
		while (!p.isRoot()) {
			Vertex q = p.getParent();
			if (q.isRoot()) {
				if (q.getLeft() == p) {
					rotateRight(p);
				} else {
					rotateLeft(p);
				}
			} else {
				Vertex r = q.getParent();
				if (r.getLeft() == q) {
					if (q.getLeft() == p) {
						rotateRight(q);
						rotateRight(p);
					} else {
						rotateLeft(p);
						rotateRight(p);
					}
				} else {
					if (q.getRight() == p) {
						rotateLeft(q);
						rotateLeft(p);
					} else {
						rotateRight(p);
						rotateLeft(p);
					}
				}
			}
		}
		p.normalize();
		p.update();
	}

	public void expose(Vertex v) {
		Vertex r = null;
		Vertex p = v;
		// while loop for working up the tree
		while (p != null) {
			splay(p);
			p.setLeft(r);
			p.update();
			r = p;
			p = p.getParent();
		}
		splay(v);
	}
}
