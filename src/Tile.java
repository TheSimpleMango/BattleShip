
public abstract class Tile {
	
	public boolean isOccupied(){
		if (this instanceof Water) {
			return false;
		}
		return true;
	}
	
	
}
