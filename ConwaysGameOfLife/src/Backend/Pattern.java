package Backend;

public class Pattern {
	private int x;
	private int y;
	
	private int[][] block = {{x, y}, {x, y+1}, {x+1, y}, {x+1, y+1}};
	private int[][] beehive = {{x, y+1}, {x, y+2}, {x+1, y}, {x+1, y+3}, {x+2, y+1}, {x+2, y+2}};
	
	
	public Pattern (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
	
	

