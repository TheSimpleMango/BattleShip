import java.util.Random;

public class Board{
	Tile[][] tiles = new Tile[10][10];
	int shotsFired;
	int hitCount;
	int shipsSunk;
	public Board() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Water();
			}
		}
	}
	
	public void print(){
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				System.out.print(tiles[i][j].toString() + " ");
			}
			System.out.println();
		}
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
	
	public void placeShipAt(int r, int c, Ship s, boolean horizontal) throws Exception{
		if (!okToPlaceShipAt(r, c, s, horizontal)){
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
		s.bowRow = r;
		s.bowColumn = c;
	}
	
	public void placeShipsRandomly(){
		Ship[] ships = new Ship[10];
		Random rand = new Random();
		boolean h;
		boolean hasPlaced = false;
		int r;
		int c;
		
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
			System.out.println("x");
			while (!hasPlaced) {
				h = rand.nextBoolean();
				r = rand.nextInt(10);
				c = rand.nextInt(10);
				try {
					placeShipAt(r, c, ship, h);
					hasPlaced = true;
				} catch (Exception e) {
				}
			}
			hasPlaced = false;
		}
	}
	
	public boolean shootAt(int r, int c){
		shotsFired++;
		if (tiles[r][c].isOccupied()) {
			hitCount++;
			Ship s = (Ship) tiles[r][c];
			s.shootAt(r, c);
			return true;
		}
		return false;
	}
}
