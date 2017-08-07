package battleship;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameCreator {
	
	public static void main (String[] args)
	{
//		EventQueue.invokeLater(new Runnable()
//        {
//           public void run()
           {
        	   
        	   BoardView bv = new BoardView();
        	   BoardModel s = new BoardModel();
        	   BoardController c = new BoardController(bv,s);
        	   JFrame frame = new JFrame();
        	   frame.add(bv.getGui());   
        	   frame.pack();
        	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	   frame.setVisible(true);
           }
//        });
	}

}

