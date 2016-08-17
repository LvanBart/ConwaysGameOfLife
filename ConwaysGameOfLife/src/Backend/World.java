package Backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class World {
	protected int x;
	protected int y;
	protected int z;
	// protected int[][] alive = new int [x][y];
	// protected ArrayList<int[]> Neighbourhood = new ArrayList<int[]>();
	protected ArrayList<int[]> Alive = new ArrayList<int[]>();
	protected Set<int[]> Neighbourhood = new HashSet<int[]>();
	protected Set<int[]> tempAlive = new HashSet<int[]>();
	protected Set<int[]> tempNeighbour = new HashSet<int[]>();

	// protected int [][] worldly = new int [5][10];
	// protected ArrayList<Birth> newborn = new ArrayList<Birth>();
	// protected List <Integer> holding = new <Integer>();

	public void tobealive(int choosex, int choosey) {
		this.x = choosex;
		this.y = choosey;
		Alive.add(new int[] { x, y });
	}

	public void getneighbours() {
		//System.out.println();
		for (int i = 0; i < Alive.size(); i++) {
			//System.out.println("Alive: [" + Alive.get(i)[0] + "," +Alive.get(i)[1] +"]");
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] }); //current
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] - 1 }); // North
																					// x,
																					// y-1
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] - 1 });// NorthEast
																						// x+1,y-1
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] - 1 });// NorhtWest
																						// x-1,y-1
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] });// East
																					// x+1,y
			Neighbourhood.add(new int[] { Alive.get(i)[0] + 1, Alive.get(i)[1] + 1 });// SouthEast
																						// x+1,y+1
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] + 1 });// SouthWest
																						// x-1,y+1
			Neighbourhood.add(new int[] { Alive.get(i)[0], Alive.get(i)[1] + 1 });// South
																					// x,
																					// y+1
			Neighbourhood.add(new int[] { Alive.get(i)[0] - 1, Alive.get(i)[1] });// West
																					// x-1,y
		}
	}

	public int checkneighbours(int[] cell) {
		int counter = 0;

		int[] N = { cell[0], cell[1] - 1 };
		if (checkAlive(N)) {
			counter++;
		}
		int[] NE = { cell[0] + 1, cell[1] - 1 };
		if (checkAlive(NE)) {
			counter++;
		}
		int[] E = { cell[0] + 1, cell[1] };
		if (checkAlive(E)) {
			counter++;
		}
		int[] SE = { cell[0] + 1, cell[1] + 1 };
		if (checkAlive(SE)) {
			counter++;
		}
		int[] S = { cell[0], cell[1] + 1 };
		if (checkAlive(S)) {
			counter++;
		}
		int[] SW = { cell[0] - 1, cell[1] + 1 };
		if (checkAlive(SW)) {
			counter++;
		}
		int[] NW = { cell[0] - 1, cell[1] - 1 };
		if (checkAlive(NW)) {
			counter++;
		}
		int[] W = { cell[0] - 1, cell[1] };
		if (checkAlive(W)) {
			counter++;
		}

		return counter;
	}

	public boolean checkAlive(int[] cell) {
		for (int[] aliveCell : Alive) {
			if (aliveCell[0] == cell[0] && aliveCell[1] == cell[1]) {
				return true;
			}
		}

		return false;
	}

	public void updateworld() {
		Neighbourhood.clear();
		getneighbours();
		tempAlive.clear();
		tempNeighbour.clear();
		int counter;
		for (int[] eachNeighbour : Neighbourhood) {
			if (checkAlive(eachNeighbour)) {
				counter = checkneighbours(eachNeighbour);
				// System.out.println(eachNeighbour[0] + "," +
				if (counter < 2 || counter > 3) {
					tempAlive.add(eachNeighbour);
				}
			} else {
				counter = checkneighbours(eachNeighbour);
				if (counter == 3) {
					tempNeighbour.add(eachNeighbour);
					// add cell to Alive list
				}

			}

		}
		for (int[] eachToChange : tempAlive) {
			removeAlive(eachToChange);
		}
		
		for (int[] eachToChange : tempNeighbour) {
			if (!checkAlive(eachToChange))
				Alive.add(eachToChange);
		}

	}

	public void removeAlive(int[] cell) {
		int[] temp = new int[2];
		for (int[] aliveCell : Alive) {

			if (aliveCell[0] == cell[0] && aliveCell[1] == cell[1]) {
				temp = aliveCell;
				continue;
			}
		}
		Alive.remove(temp);
	}

	public static void main(String args[]) {
		ArrayList<int[]> Alive1 = new ArrayList<int[]>();
		Set<int[]> Neighbourhood1 = new HashSet<int[]>();
		Set<int[]> temp1 = new HashSet<int[]>();

		int[] a = { 2, 2 };
		Alive1.add(new int[] { 1, 1 });
		Alive1.add(a);
//		System.out.println("contains a: " + Alive1.contains(a));
//		System.out.println("contains {1,1}" + Alive1.contains(new int[] { 1, 1 }));

		World W1 = new World();
		W1.tobealive(4, 2);
		System.out.println(W1.checkAlive(new int[] { 4, 2 }));
		W1.updateworld();

	}

	public ArrayList<int[]> getAlive() {
		return Alive;
	}
}