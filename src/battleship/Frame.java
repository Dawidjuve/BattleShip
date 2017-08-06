package battleship;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class Frame extends JFrame{
	
	public Frame()
	{
		add(new JLabel("GRACZ"), BorderLayout.NORTH);
		JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.EAST);
        BoardView b = new BoardView();
        
		pack();
	}
	

}
