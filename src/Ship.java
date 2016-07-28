
public abstract class Ship extends Tile{
		int bowColumn;
		int bowRow;
		int length;
		boolean isHorizontal;
		boolean[] hits;
		
		@Override 
		public String toString(){
			if (isSunk()) {
				return "X";
			}
			return "S";
		}
		
		public boolean isSunk(){
			for (boolean b : hits) {
				if (!b) {
					return false;
				}
			}
			return true;
		}
		
		public void shootAt(int c, int r){
			int distance;
			if (isHorizontal) {
				distance = c - bowColumn;
			}
			else {
				distance = r - bowRow;
			}
			hits[distance] = true;
		}
}
