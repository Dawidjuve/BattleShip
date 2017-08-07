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
	private Cell[][] cells = new Cell[10][10];
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
		
		for (int i=0; i<cells.length; i++){
			for (int j=0; j<cells[i].length; j++){
				cells[i][j]=new Cell(i,j,0);

			}	
		}

		board.add(new JLabel());
        for (int i = 0; i < 10; i++) {
            board.add(new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
        
        for (int i = 0; i < size; i++) {
        	board.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
            for (int j = 0; j < size; j++) 
                        board.add(cells[i][j]); 
        }	
	}
	
	public JPanel getGui(){
		return gui;
	}
	
	public Cell getButton(int x, int y){
		return cells[x][y];
		
	}
	
	public void addListener(MouseListener mouseListener){
		
		for (int i=0; i<cells.length; i++)
			for (int j=0; j<cells[i].length;j++)
				cells[i][j].addMouseListener(mouseListener);
	}
	
	public void paintShip(ArrayList<Cell> cells){
		for(Cell cell: cells)
			cell.setBorder(new LineBorder(new Color(50, 220, 30)));
	}
	
	public void paintEmpty(Cell cell){
		cell.setIcon(new ImageIcon("yellow-ball.gif"));
		cell.setDisabledIcon(new ImageIcon("yellow-ball.gif"));
	}
	
	public void paintMast(Cell cell){
		cell.setBorder(new LineBorder(Color.RED));
	}
	
	public void paintX(Cell cell){
		cell.setIcon(new ImageIcon("krzy¿yk.jpg"));
		cell.setDisabledIcon(new ImageIcon("krzy¿yk.jpg"));
	}
	
	public void paintStart(Cell cell){
		cell.setIcon(new ImageIcon());
		
	}
	public void endGame(){
		JOptionPane.showMessageDialog(gui, "All Ships are sunken!");
	}
	
	

}
