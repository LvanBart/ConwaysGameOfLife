package Backend;

public class Node {
	private boolean checked;
	private Node N, W, S, E;

	public Node() {
		checked = false;
	}

	public void createNeighbour(String direction) {
		switch (direction) {
		case "N":
			N = new Node();
			N.S = this;
			if (W.N == null) {
				W.N = new Node();
				W.N.S = W;
			}
			N.W = W.N;
			W.N.E = N;
			if (E.N == null) {
				E.N = new Node();
				E.N.S = E;
			}
			N.E = E.N;
			E.N.W = N;
			break;
		case "W":
			W = new Node();
			if (N.W == null) 
				N.W = new Node();
			W.N = N.W;
			if (S.W == null)
				S.W = new Node();
			W.S = S.W;
			break;
		case "S":
			S = new Node();
			if (W.S == null)
				W.S = new Node();
			S.W = W.S;
			if (E.S == null)
				E.S = new Node();
			S.E = E.S;
			break;
		case "E":
			break;
		}
	}

}
