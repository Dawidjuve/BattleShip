package battleship;

public class Field {

	private int col;
	private int row;
	/**
	 * Keeps state of field. During creating the board, there are 3 states:
	 * 0-available, 1-ship, 2-unavailable. After the board was created, there are
	 * 4 states: 0-initial, 1-hit, 2-miss, 3-mark
	 */
	private int state;

	public Field(int x, int y) {
		this.col = x;
		this.row = y;
		this.state = 0;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
}
