package battleship;

import javax.swing.JFrame;

public class GameCreator {

	public static void main(String[] args) {

		BoardModel model = new BoardModel();
		BoardController ctrl = new BoardController(model);
		BoardView bv = new BoardView(ctrl, model);

		JFrame frame = new JFrame();
		frame.add(bv.getGui());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
