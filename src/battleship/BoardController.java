package battleship;

public class BoardController {

	private final BoardModel model;

	public BoardController(final BoardModel model) {
		this.model = model;
	}

	public void onTest(int col, int row) {
		if (model.hasShipAt(col, row)) {
			model.setHitAt(col, row);
		} else
			model.setMissAt(col, row);
	}

	public void toMark(int col, int row) {
		model.setMarkAt(col, row);
	}

}
