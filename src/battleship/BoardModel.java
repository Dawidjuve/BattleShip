package battleship;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import battleship.Field.State;



public class BoardModel {
	
	private static int currentAmount;
	private static Field[][] fields = new Field[10][10];
	private Random generator = new Random();
	private ArrayList<ArrayList<Field>> shapesList;
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
	
	public BoardModel (){
		shapesList = new ArrayList<ArrayList<Field>>();
		shapesList.add(createShip(4));
		shapesList.add(createShip(3));
		shapesList.add(createShip(3));
		shapesList.add(createShip(2));
		shapesList.add(createShip(2));
		shapesList.add(createShip(2));
		shapesList.add(createShip(1));
		shapesList.add(createShip(1));
		shapesList.add(createShip(1));
		shapesList.add(createShip(1));
		
	}
	public ArrayList<Field> createShip(int mastsAmount)
	{
		list1 = new ArrayList<>();
			
		do {
			currentAmount = 0;
			int x = generator.nextInt(10);
			int y = generator.nextInt(10);
			
			if (fields[x][y].equalsState(State.Available)){

					currentAmount=1;
					fields[x][y].setState(State.Ship);
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
							if(!fields[j][k].equalsState(State.Ship)){
								
								fields[j][k].setState(State.Unavailable);
							}
		}

		return list1;
	}
	
	public ArrayList<Field> addMast(int x, int y, int mastsAmount){

		//add all potential masts to list2
		for (int i = x-1; i<=x+1 ; i++){
			if (i>=0 && i<=9)
				for(int j= y-1; j<=y+1; j++)
					if(j>=0 && j<=9)
						if(((i!=x && j==y) || (i==x && j!=y)) && fields[i][j].equalsState(State.Available)){

							list2.add(fields[i][j]);
						}
		}	
			 
		if (list2.size()>0){

			int r = generator.nextInt(list2.size());
			list2.get(r).setState(State.Ship);
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
				list1.get(i).setState(State.Available);
			}
		}
		return list1;
	}

	public boolean checkMove(int x, int y){
		
		if( fields[x][y].equalsState(State.Ship))
			return true;
		else return false;
	}
	
	public int getShipNumber(int x, int y){
		int i=0;
		int iter=0;
		for(Iterator<ArrayList<Field>> iterShip =shapesList.iterator(); iterShip.hasNext();){
			i++;
			ArrayList list = iterShip.next();
			for(Iterator<Field> iterField = list.iterator(); iterField.hasNext();){
				Field field = iterField.next();
				if(field.getX()==x && field.getY()==y){
					iterField.remove();
					iter = i-1;
				}
			}
		}
		return iter;
	}
	
	public boolean isSunk(int i){
		if( shapesList.get(i).isEmpty()){
			shapesList.remove(i);
			return true;
		}else return false;
	}
	
	public boolean isWinner(){
		return shapesList.isEmpty();
	}

}
