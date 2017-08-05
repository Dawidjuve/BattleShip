package battleship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MyButton extends JButton{
	
	private int row;
	private int col;
	private int z;
	
	public MyButton(int row, int col, int z){
		super();
		this.row=row;
		this.col=col;
		this.z=z;
		
		setBorder(new LineBorder(Color.DARK_GRAY));
		ImageIcon icon = new ImageIcon(
                new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB));
        setIcon(icon);
        Insets buttonMargin = new Insets(0,0,0,0);
        setMargin(buttonMargin);
		
        setBackground(Color.WHITE);
	}
	
	
	public int getRow(){
		return row;
	
	}
	
	public int getColumn(){
		return col;
	}
	
	public int getZ(){
		return z;
	}
	
	public void setZ(int z){
		this.z=z;
	}

}
