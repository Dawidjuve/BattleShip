package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BoardView {

	private JPanel gui = new JPanel(new BorderLayout(5, 5));
	private Cell[][] cells = new Cell[10][10];
	private final String COLS = "ABCDEFGHIJ";
	private final int size = 10;
	private BoardController ctrl;
	private BoardModel model;

	public BoardView(BoardController ctrl, BoardModel model) {

		this.ctrl = ctrl;
		this.model = model;
		drawBoard();
		model.register(m -> reDraw());
	}

	public void drawBoard() {

		gui.setSize(1000, 400);
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		gui.setBorder(new LineBorder(Color.BLACK));

		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		tools.add(new JButton("New")); // TODO - add functionality!

		JPanel board = new JPanel();
		board.setLayout(new GridLayout(11, 11));
		board.setBorder(new LineBorder(Color.BLUE, 1));

		gui.add(board);

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i, j);
				cells[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int col = ((Cell) e.getSource()).getColumn();
						int row = ((Cell) e.getSource()).getRow();

						if (e.getButton() == 1) {
							if (!((Cell) e.getSource()).isEnabled()) {
								return;
							}

							ctrl.onTest(col, row);
						} else if (e.getButton() == 3) {
							if (!((Cell) e.getSource()).isEnabled())
								return;
							ctrl.toMark(col, row);
						}
					}
				});
			}
		}

		board.add(new JLabel());
		for (int i = 0; i < 10; i++) {
			board.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}

		for (int i = 0; i < size; i++) {
			board.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
			for (int j = 0; j < size; j++)
				board.add(cells[i][j]);
		}
	}

	public void reDraw() {
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[i].length; j++) {
				paintCellAt(i, j);
			}
	}

	private void paintCellAt(int col, int row) {
		if (model.isHitAt(col, row)) {
			cells[col][row].setIcon(new ImageIcon("krzy¿yk.jpg"));
			cells[col][row].setDisabledIcon(new ImageIcon("krzy¿yk.jpg"));
			cells[col][row].setEnabled(false);
			if (model.isSunkAt(col, row)) {
				cells[col][row].setBorder(new LineBorder(new Color(50, 220, 30)));
				if (model.isOver())
					endGame();
			} else
				cells[col][row].setBorder(new LineBorder(Color.RED));
		} else if (model.isMarkAt(col, row)) {
			cells[col][row].setIcon(new ImageIcon("yellow-ball.gif"));
			cells[col][row].setDisabledIcon(new ImageIcon("yellow-ball.gif"));
		} else if (model.isMissAt(col, row)) {
			cells[col][row].setIcon(new ImageIcon("yellow-ball.gif"));
			cells[col][row].setDisabledIcon(new ImageIcon("yellow-ball.gif"));
			cells[col][row].setEnabled(false);
		} else
			cells[col][row].setIcon(new ImageIcon());

	}

	public JPanel getGui() {
		return gui;
	}

	public Cell getButton(int x, int y) {
		return cells[x][y];

	}

	public void endGame() {
		JOptionPane.showMessageDialog(gui, "All Ships are sunken!");
	}

	public class Cell extends JButton {

		private int row;
		private int col;

		public Cell(int col, int row) {
			super();
			this.row = row;
			this.col = col;

			setBorder(new LineBorder(Color.DARK_GRAY));
			ImageIcon icon = new ImageIcon(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB));
			setIcon(icon);
			Insets buttonMargin = new Insets(0, 0, 0, 0);
			setMargin(buttonMargin);

			setBackground(new Color(240, 255, 255));
		}

		public int getRow() {
			return row;

		}

		public int getColumn() {
			return col;
		}
	}

}
