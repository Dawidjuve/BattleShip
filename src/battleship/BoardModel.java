package battleship;

import java.awt.Point;
import java.util.ArrayList;


public class BoardModel {
	
	private ArrayList<Boat> boats;
	
	public BoardModel(){
		BoardCreator bc = new BoardCreator();
		ArrayList<Point[]> p = bc.getShips();
		for(int i =0; i<p.size();i++){
			boats.add(new Boat(p.get(i)));
		}
		
	}
	
	public boolean hasShipAt(int col, int row){
		for(Boat boat : boats){
			if(boat.get)
		}
	}
	
	public class Boat{
		
		private Point[] points;
		private boolean[] hits;
		
		public Boat(Point[] points){
			this.points=points;
			this.hits = new boolean[points.length];
		}
		
		public Point[] getPoints(){
			return points;
		}
	}

//	public boolean checkMove(int x, int y){
//		
//		return fields[x][y].equalsState(State.Ship);
//		
//	}
//	
//	public int getShipNumber(int x, int y){
//		int i=0;
//		int iter=0;
//		for(Iterator<ArrayList<Field>> iterShip =shapesList.iterator(); iterShip.hasNext();){
//			i++;
//			ArrayList list = iterShip.next();
//			for(Iterator<Field> iterField = list.iterator(); iterField.hasNext();){
//				Field field = iterField.next();
//				if(field.getX()==x && field.getY()==y){
//					iterField.remove();
//					iter = i-1;
//				}
//			}
//		}
//		return iter;
//	}
//	
//	public boolean isSunk(int i){
//		if( shapesList.get(i).isEmpty()){
//			shapesList.remove(i);
//			return true;
//		}else return false;
//	}
//	
//	public boolean isWinner(){
//		return shapesList.isEmpty();
//	}

}
