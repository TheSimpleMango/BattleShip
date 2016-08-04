import java.util.Random;

public class Board{
	Tile[][] tiles = new Tile[10][10];
	boolean[][] shots = new boolean[10][10];
	int shotsFired;
	int hitCount;
	int shipsSunk;
	String boardString;
	public Board() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Water();
			}
		}
	}
	
	public void print(){
		boardString = "<html>";
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (shots[i][j]) {
					boardString += tiles[i][j].toString() + "  ";
				}
				else {
					boardString += "-  ";
				}
			}
			boardString += "<br>";
		}
		boardString += "</html>";
	}
	
	public boolean okToPlaceShipAt(int r, int c, Ship s, boolean horizontal){
		if (horizontal) {
			for (int i = c; i < s.length + c; i++) {
				if (tiles[r][i].isOccupied()) {
					return false;
				}
			}
		}
		else{
			for (int i = r; i < s.length + r; i++) {
				if (tiles[i][c].isOccupied()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void placeShipAt(int c, int r, Ship s, boolean horizontal) throws Exception{
		if (!okToPlaceShipAt(c, r, s, horizontal)){
			throw new Exception("Not okay to place ship at");
		}
		if (horizontal) {
			s.isHorizontal = true;
			for (int i = c; i < s.length + c; i++) {
				tiles[r][i] = s;
			}
		}
		else{
			for (int i = r; i < s.length + r; i++) {
				tiles[i][c] = s;
			}
		}
		s.bowColumn = c;
		s.bowRow = r;
	}
	
	public void placeShipsRandomly(){
		Ship[] ships = new Ship[10];
		Random rand = new Random();
		boolean h;
		boolean hasPlaced = false;
		int c;
		int r;
		
		for (int i = 0; i < ships.length; i++) {
			switch (i) {
			case 0:
				ships[i] = new Battleship();
				break;
			case 1: case 2:
				ships[i] = new Cruiser();
				break;
			case 3: case 4:case 5:
				ships[i] = new Destroyer();
				break;
			case 6: case 7: case 8: case 9:
				ships[i] = new Submarine();
				break;
			default:
				break;
			}
		}
		
		for (Ship ship : ships) {
			while (!hasPlaced) {
				h = rand.nextBoolean();
				c = rand.nextInt(10);
				r = rand.nextInt(10);
				try {
					placeShipAt(c, r, ship, h);
					hasPlaced = true;
				} catch (Exception e) {
				}
			}
			hasPlaced = false;
		}
	}
	
	public boolean shootAt(int c, int r){
		shotsFired++;
		shots[r][c] = true;
		if (tiles[r][c].isOccupied()) {
			System.out.println("hit!");
			hitCount++;
			Ship s = (Ship) tiles[r][c];
			s.shootAt(c, r);
			return true;
		}
		System.out.println("not hit");
		return false;
	}
}
