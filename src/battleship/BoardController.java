package battleship;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class BoardController {
	private BoardView view;
	private BoardModel model;
	private ArrayList<ArrayList<Cell>> cellsList;
	
	public void createButtonsList(){
		cellsList=new ArrayList<ArrayList<Cell>>();
		for(int i=0; i<=9; i++){
			cellsList.add(new ArrayList<Cell>());
		}
	}
	
	public BoardController(BoardView bv, BoardModel s ){
		
		this.view = bv;
		this.model= s;
		view.addListener(new MyListener());
		createButtonsList();
		
	}
	
	public class MyListener extends MouseAdapter{

		public void mouseClicked(MouseEvent e){

			if(e.getButton() == 1){

				if (!((Cell)e.getSource()).isEnabled()) {
					return;
				}

				showField((Cell)e.getSource());    

			}
			else if(e.getButton()==3)
			{
				if(!((Cell)e.getSource()).isEnabled() )
					return;
				flagEmpty((Cell)e.getSource());
			}
		}
	}

	public void showField(Cell cell){
		
		
		boolean t =model.checkMove(cell.getRow(),cell.getColumn());
		if(t){
			view.paintX(cell);
			int iter = model.getShipNumber(cell.getRow(), cell.getColumn());
			addButtonToShip(cell, iter);
			if(model.isSunk(iter)){
				view.paintShip(cellsList.get(iter));
				if(model.isWinner())
					view.endGame();
			}
				
			else view.paintMast(cell);
		}
		else view.paintEmpty(cell);
		cell.setEnabled(false);
	}
	
	public void flagEmpty(Cell cell){
		if(cell.getZ()==0){
			view.paintEmpty(cell);
			cell.setZ(1);
		}
		else if(cell.getZ()==1){
			view.paintStart(cell);
			cell.setZ(0);
		}
	}
	
		
	public void addButtonToShip(Cell cell, int i){
		
		cellsList.get(i).add(cell);
	}
	
	
	
}

