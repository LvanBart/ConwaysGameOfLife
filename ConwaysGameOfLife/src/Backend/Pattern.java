package Backend;

public class Pattern {

	public static String[] patternNames = { " ", "block", "beehive", "loaf", "boat", "blinker", "toad", "beacon",
			"pentadecathlon", "glider", "lwss", "rPent", "pulsar" };

	public static int[][] getPattern(String pattern, int x, int y) {
		int[][] newPattern;

		switch (pattern) {
		case "block":
			newPattern = new int[][] { { x, y }, { x, y + 1 }, { x + 1, y }, { x + 1, y + 1 } };
			break;

		case "beehive":
			newPattern = new int[][] { { x + 1, y }, { x + 2, y }, { x, y + 1 }, { x + 3, y + 1 }, { x + 1, y + 2 },
					{ x + 2, y + 2 } };
			break;

		case "loaf":
			newPattern = new int[][] { { x + 1, y }, { x + 2, y }, { x, y + 1 }, { x + 2, y + 1 }, { x + 3, y + 1 },
					{ x + 1, y + 2 }, { x + 3, y + 2 }, { x + 2, y + 3 } };
			break;

		case "boat":
			newPattern = new int[][] { { x, y }, { x + 1, y }, { x, y + 1 }, { x + 2, y + 1 }, { x, y + 2 },
					{ x + 1, y + 2 } };
			break;

		case "blinker":
			newPattern = new int[][] { { x, y }, { x + 1, y }, { x + 2, y } };
			break;

		case "toad":
			newPattern = new int[][] { { x + 1, y }, { x + 2, y }, { x + 3, y }, { x, y + 1 }, { x + 1, y + 1 },
					{ x + 2, y + 1 } };
			break;

		case "beacon":
			newPattern = new int[][] { { x, y }, { x + 1, y }, { x, y + 1 }, { x + 3, y + 2 }, { x + 2, y + 3 },
					{ x + 3, y + 3 } };
			break;

		case "pentadecathlon":
			newPattern = new int[][] { { x + 2, y }, { x + 7, y }, { x, y + 1 }, { x + 1, y + 1 }, { x + 3, y + 1 },
					{ x + 4, y + 1 }, { x + 5, y + 1 }, { x + 6, y + 1 }, { x + 8, y + 1 }, { x + 9, y + 1 },
					{ x + 2, y + 2 }, { x + 7, y + 2 } };
			break;

		case "glider":
			newPattern = new int[][] { { x + 1, y }, { x + 2, y + 1 }, { x, y + 2 }, { x + 1, y + 2 },
					{ x + 2, y + 2 } };
			break;

		case "lwss":
			newPattern = new int[][] { { x + 1, y }, { x + 4, y }, { x, y + 1 }, { x, y + 2 }, { x + 4, y + 2 },
					{ x, y + 3 }, { x + 1, y + 3 }, { x + 2, y + 3 }, { x + 3, y + 3 } };
			break;

		case "rPent":
			newPattern = new int[][] { { x + 1, y }, { x + 2, y }, { x, y + 1 }, { x + 1, y + 1 }, { x + 1, y + 2 } };
			break;

		case "pulsar":
			newPattern = new int[][] { { x + 2, y }, { x + 3, y }, { x + 4, y }, { x + 8, y }, { x + 9, y },
					{ x + 10, y }, { x, y + 2 }, { x + 5, y + 2 }, { x + 7, y + 2 }, { x + 12, y + 2 }, { x, y + 3 },
					{ x + 5, y + 3 }, { x + 7, y + 3 }, { x + 12, y + 3 }, { x, y + 4 }, { x + 5, y + 4 },
					{ x + 7, y + 4 }, { x + 12, y + 4 }, { x + 2, y + 5 }, { x + 3, y + 5 }, { x + 4, y + 5 },
					{ x + 8, y + 5 }, { x + 9, y + 5 }, { x + 10, y + 5 }, { x + 2, y + 7 }, { x + 3, y + 7 },
					{ x + 4, y + 7 }, { x + 8, y + 7 }, { x + 9, y + 7 }, { x + 10, y + 7 }, { x, y + 8 },
					{ x + 5, y + 8 }, { x + 7, y + 8 }, { x + 12, y + 8 }, { x, y + 9 }, { x + 5, y + 9 },
					{ x + 7, y + 9 }, { x + 12, y + 9 }, { x, y + 10 }, { x + 5, y + 10 }, { x + 7, y + 10 },
					{ x + 12, y + 10 }, { x + 2, y + 12 }, { x + 3, y + 12 }, { x + 4, y + 12 }, { x + 8, y + 12 },
					{ x + 9, y + 12 }, { x + 10, y + 12 } };
			break;

		default:
			newPattern = new int[][] { { x, y } };
			break;

		}

		return newPattern;

	}
}
