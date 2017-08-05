package battleship;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;

public class BoardController {
	
	
	private BoardView view;
	private BoardModel model;
	private ArrayList<ArrayList<MyButton>> buttonsList;
	
	public void createButtonsList(){
		buttonsList=new ArrayList<ArrayList<MyButton>>();
		for(int i=0; i<=9; i++){
			buttonsList.add(new ArrayList<MyButton>());
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

				if (!((MyButton)e.getSource()).isEnabled()) {
					return;
				}

				showField((MyButton)e.getSource());    

			}
			else if(e.getButton()==3)
			{
				if(!((MyButton)e.getSource()).isEnabled() )
					return;
				flagEmpty((MyButton)e.getSource());
			}
		}
	}

	public void showField(MyButton mybutton){
		
		
		boolean t =model.checkMove(mybutton.getRow(),mybutton.getColumn());
		if(t){
			view.paintX(mybutton);
			int iter = model.getShipNumber(mybutton.getRow(), mybutton.getColumn());
			addButtonToShip(mybutton, iter);
			if(model.isSunk(iter)){
				view.paintShip(buttonsList.get(iter));
				if(model.isWinner())
					view.endGame();
			}
				
			else view.paintMast(mybutton);
		}
		else view.paintEmpty(mybutton);
		mybutton.setEnabled(false);
	}
	
	public void flagEmpty(MyButton mybutton){
		if(mybutton.getZ()==0){
			view.paintEmpty(mybutton);
			mybutton.setZ(1);
		}
		else if(mybutton.getZ()==1){
			view.paintStart(mybutton);
			mybutton.setZ(0);
		}
	}
	
		
	public void addButtonToShip(MyButton mybutton, int i){
		
		buttonsList.get(i).add(mybutton);
	}
	
	
	
}

