package Backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class World {
	protected int x;
	protected int y;
	protected int z;
	// protected int[][] alive = new int [x][y];
	// protected ArrayList<int[]> Neighbourhood = new ArrayList<int[]>();
	protected ArrayList<int[]> Alive = new ArrayList<int[]>();
	protected Set<int[]> Neighbourhood = new HashSet<int[]>();
	protected Set<int[]> temp = new HashSet<int[]>();

	// protected int [][] worldly = new int [5][10];
	// protected ArrayList<Birth> newborn = new ArrayList<Birth>();
	// protected List <Integer> holding = new <Integer>();
	public int setx() {
		return x;
	}

	public int sety() {
		return y;
	}

	public int getx() {
		return x;

	}

	public int gety() {
		return y;
	}

	public void tobealive(int choosex, int choosey) {
		this.x = choosex;
		this.y = choosey;
		Alive.add(new int[] { x, y });
	}

	public void getneighbours() {
		for (int i = 0; i < Alive.size(); i++) {
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] });
			// North
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] });
			// NorthEast
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] + 1 });
			// NorhtWest
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] - 1 });
			// East
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] + 1 });
			// SouthEast
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] + 1 });
			// SouthWest
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] - 1 });
			// South
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] });
			// West
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] - 1 });
		}
	}

	public int checkneighbours(int[] cell, ArrayList<int[]> alive) {
		int counter = 0;

		int[] N = { cell[0], cell[1] - 1 };
		if (alive.contains(N)) {
			counter++;
		}
		int[] NE = { cell[0] + 1, cell[1] - 1 };
		if (alive.contains(NE)) {
			counter++;
		}
		int[] E = { cell[0] + 1, cell[1] };
		if (alive.contains(E)) {
			counter++;
		}
		int[] SE = { cell[0] + 1, cell[1] - 1 };
		if (alive.contains(SE)) {
			counter++;
		}
		int[] S = { cell[0], cell[1] + 1 };
		if (alive.contains(S)) {
			counter++;
		}
		int[] SW = { cell[0] - 1, cell[1] + 1 };
		if (alive.contains(SW)) {
			counter++;
		}
		int[] NW = { cell[0] - 1, cell[1] - 1 };
		if (alive.contains(NW)) {
			counter++;
		}
		int[] W = { cell[0] - 1, cell[1] };
		if (alive.contains(W)) {
			counter++;
		}

		return counter;
	}

	public void updateworld() {
		temp.clear();
		// int n = 0;
		// for (int i = 0; i < Neighbourhood.size(); i++) {
		for (int[] eachNeighbour : Neighbourhood) {

			if (Alive.contains(eachNeighbour)) {
				int counter = checkneighbours(eachNeighbour, Alive);
				if (counter < 2 || counter > 3) {
					temp.add(eachNeighbour);
					//Alive.remove(eachNeighbour);
					// kill cell from alive list
				}
			} else {
				int counter1 = checkneighbours(eachNeighbour, Alive);
				if (counter1 >= 3) {
					temp.add(eachNeighbour);
					// add cell to Alive list
				}

			}

		}
		for (int[] eachToChange : temp) {
			if (Alive.contains(eachToChange)) {
				Alive.remove(eachToChange);
			}
			else {
				Alive.add(eachToChange);
			}
		}
		
	}

	public static void main(String args[]) {

		World W1 = new World();
		W1.tobealive(4, 2);
		W1.updateworld();

	}
}