package battleship;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BoardView {
	
	private JPanel gui = new JPanel(new BorderLayout(5,5));
	private MyButton[][] myButtons = new MyButton[10][10];
	private final String COLS = "ABCDEFGHIJ";
	private final int size=10;
	
	
	public BoardView(){
		
		drawBoard();
	}
	
	public void drawBoard(){
		
		gui.setSize(1000,400);
		gui.setBorder(new EmptyBorder(5,5,5,5));
		gui.setBorder(new LineBorder(Color.BLACK));
		
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        
        JPanel board = new JPanel();
		board.setLayout(new GridLayout(11,11));
		board.setBorder(new LineBorder(Color.BLUE, 1));
		
		gui.add(board);
		
		for (int i=0; i<myButtons.length; i++){
			for (int j=0; j<myButtons[i].length; j++){
				myButtons[i][j]=new MyButton(i,j,0);

			}	
		}

		board.add(new JLabel());
        for (int ii = 0; ii < 10; ii++) {
            board.add(new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        
        for (int ii = 0; ii < size; ii++) {
            for (int jj = 0; jj < size; jj++) {
                switch (jj) {
                    case 0:
                        board.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        board.add(myButtons[ii][jj]);
                }
            }
        }		
        
        
	}
	
	public JPanel getGui(){
		return gui;
	}
	
	public MyButton getButton(int x, int y){
		return myButtons[x][y];
		
	}
	
	public void addListener(MouseListener mouseListener){
		
		for (int i=0; i<myButtons.length; i++)
			for (int j=0; j<myButtons[i].length;j++)
				myButtons[i][j].addMouseListener(mouseListener);
	}
	
	public void paintShip(ArrayList<MyButton> mybuttons){
		for(int i =0; i<mybuttons.size(); i++)
			mybuttons.get(i).setBorder(new LineBorder(new Color(50, 220, 30)));
	}
	
	public void paintEmpty(MyButton mybutton){
		mybutton.setIcon(new ImageIcon("yellow-ball.gif"));
		mybutton.setDisabledIcon(new ImageIcon("yellow-ball.gif"));
	}
	
	public void paintMast(MyButton mybutton){
		mybutton.setBorder(new LineBorder(Color.RED));
	}
	
	public void paintX(MyButton mybutton){
		mybutton.setIcon(new ImageIcon("krzy¿yk.jpg"));
		mybutton.setDisabledIcon(new ImageIcon("krzy¿yk.jpg"));
	}
	
	public void paintStart(MyButton mybutton){
		mybutton.setIcon(new ImageIcon());
		
	}
	public void endGame(){
		JOptionPane.showMessageDialog(gui, "Wysztkie statki zatopione!");
	}
	
	

}
