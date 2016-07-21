
public abstract class Ship extends Tile{
		int bowRow;
		int bowColumn;
		int length;
		boolean isHorizontal;
		Boolean[] hits = new Boolean[length];
		
		@Override 
		public String toString(){
			if (isSunk()) {
				return "X";
			}
			return "S";
		}
		
		public boolean isSunk(){
			for (Boolean b : hits) {
				if (!b) {
					return false;
				}
			}
			return true;
		}
		
		public void shootAt(int r, int c){
			
		}
}
