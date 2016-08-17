package Backend;

public class Birth {

	protected int x;
	protected int y;
	protected int[] alive = { x, y };
	
	public int getx (){
		return x;
	}
	public int gety(){
		return y;
	}

	public Birth(int x1, int y1) {
		this.x = x1;
		this.y = y1;

	}
}
