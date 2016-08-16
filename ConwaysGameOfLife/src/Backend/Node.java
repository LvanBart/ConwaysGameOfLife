package Backend;

public class Node {
	private boolean checked;
	private Node N, W, S, E;

	public Node() {
		this.checked = false;
	}

	public void createNeighbour(String direction) {
		switch (direction) {
		case "N":
			N = new Node();
			N.setS(this);
			if (W.getN() == null) {
				W.setN(new Node());
				W.getN().setS(W);
			}
			N.setW(W.getN());
			W.getN().setE(N);
			if (E.getN() == null) {
				E.setN(new Node());
				E.getN().setS(E);
			}
			N.setE(E.getN());
			E.getN().setW(N);
			break;
		case "W":
			break;
		case "S":
			break;
		case "E":
			break;
		}
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Node getN() {
		return N;
	}

	public void setN(Node n) {
		N = n;
	}

	public Node getW() {
		return W;
	}

	public void setW(Node w) {
		W = w;
	}

	public Node getS() {
		return S;
	}

	public void setS(Node s) {
		S = s;
	}

	public Node getE() {
		return E;
	}

	public void setE(Node e) {
		E = e;
	}

}
