package Backend;

public class Pattern {
	
	public static int[][] getPattern(String pattern, int x, int y) {
		int[][] newPattern = null;
		
		switch (pattern) {
			case "block":
				newPattern = new int[][] {{x, y}, {x, y+1}, {x+1, y}, {x+1, y+1}};
				break;
				
			case "beehive":
				newPattern = new int[][] {{x+1, y}, {x+2, y}, {x, y+1}, {x+3, y+1}, {x+1, y+2}, {x+2, y+2}};
				break;
				
			case "loaf":
				newPattern = new int[][] {{x+1, y}, {x+2, y}, {x, y+1}, {x+2, y+1}, {x+3, y+1}, {x+1, y+2}, {x+3, y+2}, {x+2, y+3}};
				break;
				
			case "boat":
				newPattern = new int[][] {{x, y}, {x+1, y}, {x, y+1}, {x+2, y+1}, {x, y+2}, {x+1, y+2}};
				break;
				
			case "blinker":
				newPattern = new int[][] {{x, y}, {x+1, y}, {x+2, y}};
				break;
				
			case "toad":
				newPattern = new int[][] {{x+1, y}, {x+2, y}, {x+3, y}, {x, y+1}, {x+1, y+1}, {x+2, y+1}};
				break;
				
			case "beacon":
				newPattern = new int[][] {{x, y}, {x, y+1}, {x+3, y+2}, {x+2, y+3}, {x+3, y+3}};
				break;
				
			case "pentadecathlon":
				newPattern = new int[][] {{x, y}, {x+1, y}, {x+2, y}};
				break;
				
			case "glider":
				newPattern = new int[][] {{x+1, y}, {x+2, y+1}, {x, y+2}, {x+1, y+2}, {x+2, y+2}};
				break;
				
			case "lwss":
				newPattern = new int[][] {{x+1, y}, {x+4, y}, {x, y+1}, {x, y+2}, {x+4, y+2}, {x, y+3}, {x+1, y+3}, {x+2, y+3}, {x+3, y+3}};
				break;
				
			case "rPent":
				newPattern = new int[][] {{x+1, y}, {x+2, y}, {x, y+1}, {x+1, y+1}, {x+1, y+2}};
				break;
				
			case "pulsar":
				newPattern = new int[][] {{x+2, y}, {x+3, y}, {x+4, y}, {x+8, y}, {x+9, y}, {x+10, y}, {x, y+2}, };
				
			default:
				newPattern = null;
				break;
			
		}
		
		return newPattern;
		
		
		
	}
}
	
	

