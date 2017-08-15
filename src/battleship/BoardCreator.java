package battleship;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;




public class BoardCreator {
	private static int currentAmount;
	private static Field[][] fields = new Field[10][10];
	private Random generator = new Random();
	private ArrayList<Point[]> shipsList;
	private static ArrayList<Field> list1;
	private static ArrayList<Field> list2;
	
	
	{
	int i, j;
	for(i=0; i<10; i++){
		for(j=0; j<10; j++){
			fields[i][j]=new Field(i,j);
		}
	}
	}
	
	
	
	public BoardCreator (){
		shipsList = new ArrayList<Point[]>();
		shipsList.add(createShip(4));
		shipsList.add(createShip(3));
		shipsList.add(createShip(3));
		shipsList.add(createShip(2));
		shipsList.add(createShip(2));
		shipsList.add(createShip(2));
		shipsList.add(createShip(1));
		shipsList.add(createShip(1));
		shipsList.add(createShip(1));
		shipsList.add(createShip(1));
		
	}
	
	
	public Point[] createShip(int mastsAmount)
	{
		list1 = new ArrayList<>();
			
		do {
			currentAmount = 0;
			int x = generator.nextInt(10);
			int y = generator.nextInt(10);
			
			if (fields[x][y].equalsState(BoardCreator.Field.State.Available)){

					currentAmount=1;
					fields[x][y].setState(BoardCreator.Field.State.Ship);
					list1.add(fields[x][y]);
					if(mastsAmount>1){
						list2 = new ArrayList<>();
						addMast(x,y, mastsAmount);
					}
			}
		}while(!(list1.size()==mastsAmount));
	
		for(int i = 0; i<list1.size(); i++)
		{
			for(int j =list1.get(i).getX()-1; j<=list1.get(i).getX()+1; j++)
				if (j>=0 && j<=9)
					for( int k = list1.get(i).getY()-1; k<=list1.get(i).getY()+1; k++)
						if(k>=0 && k<=9)
							if(!fields[j][k].equalsState(BoardCreator.Field.State.Ship)){
								
								fields[j][k].setState(BoardCreator.Field.State.Unavailable);
							}
		}
		
		Point[] points = new Point[list1.size()];
		for(int i = 0; i<list1.size(); i++){
			points[i].setLocation(list1.get(i).getX(), list1.get(i).getY());
		}

		return points;
	}
	
	public ArrayList<Field> addMast(int x, int y, int mastsAmount){

		//add all potential masts to list2
		for (int i = x-1; i<=x+1 ; i++){
			if (i>=0 && i<=9)
				for(int j= y-1; j<=y+1; j++)
					if(j>=0 && j<=9)
						if(((i!=x && j==y) || (i==x && j!=y)) && fields[i][j].equalsState(BoardCreator.Field.State.Available)){

							list2.add(fields[i][j]);
						}
		}	
			 
		if (list2.size()>0){

			int r = generator.nextInt(list2.size());
			list2.get(r).setState(BoardCreator.Field.State.Ship);
			list1.add(list2.get(r));
			currentAmount++;
			if(currentAmount<mastsAmount){
				list2.remove(r);
				addMast(list1.get(currentAmount-1).getX(),list1.get(currentAmount-1).getY(), mastsAmount);
			}
		}
		else{

			for(int i = 0; i<list1.size(); i++)
			{
				list1.get(i).setState(BoardCreator.Field.State.Available);
			}
		}
		
		
		
		return list1;
	}
	
	public ArrayList<Point[]> getShips(){
		return shipsList;
	}
	
	

	private static class Field {
		
		private int x;
		private int y;
		public enum State{
			Ship ("Ship"),
			Available("Available"),
			Unavailable("Unavailable");
			
			private String text;
			private State(String text){
				this.text=text;
			}
		}

		private State state;
		
		public Field(int x, int y){
			this.x = x;
			this.y = y;
			this.state=State.Available;
			

		}
		
		public boolean equalsState(State s){
			return this.getState()==s;
		}
		public void setState(State s){
			this.state = s;
		}
		
		public State getState(){
			
			return this.state;
		}
		
		public int getX(){
			return this.x;
		}
		
		public int getY(){
			return this.y;
		}
		


	}

}
