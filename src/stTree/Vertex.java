package stTree;

public class Vertex {
	private Vertex successor;
	
	public Vertex(){
		
	}

	public Vertex getSuccessor() {
		return successor;
	}

	public void setSuccessor(Vertex successor) {
		this.successor = successor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((successor == null) ? 0 : successor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (successor == null) {
			if (other.successor != null)
				return false;
		} else if (!successor.equals(other.successor))
			return false;
		return true;
	}
	
	

}
