package battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class BoardModel {

	private ArrayList<Boat> boats;
	private Set<Consumer<BoardModel>> observers = new HashSet<>();
	private Field[][] fields;

	public BoardModel() {
		BoardCreator bc = new BoardCreator();
		boats = bc.getBoats();
		fields = bc.getFields();
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				fields[i][j].setState(0);
			}
		}
	}

	public boolean hasShipAt(int col, int row) {
		return boatAt(col, row) != null;
	}

	public boolean isOver() {
		for (Boat boat : boats) {
			if (!boat.isSunk())
				return false;
		}
		return true;

	}

	public boolean isSunkAt(int col, int row) {
		return boatAt(col, row).isSunk();
	}

	public Boat boatAt(int col, int row) {
		for (Boat boat : boats) {
			if (boat.isAt(col, row))
				return boat;
		}
		return null;
	}

	public void setHitAt(int col, int row) {
		fields[col][row].setState(1);
		notifyChange();
	}

	public void setMarkAt(int col, int row) {
		fields[col][row].setState(3);
		notifyChange();
	}

	public void setMissAt(int col, int row) {
		fields[col][row].setState(2);
		notifyChange();
	}

	public boolean isHitAt(int col, int row) {
		return fields[col][row].getState() == 1;
	}

	public boolean isMissAt(int col, int row) {
		return fields[col][row].getState() == 2;
	}

	public boolean isMarkAt(int col, int row) {
		return fields[col][row].getState() == 3;
	}

	public void notifyChange() {
		observers.forEach(c -> c.accept(this));
	}

	public void register(Consumer<BoardModel> observer) {
		this.observers.add(observer);
	}

	public static class Boat {

		private Field[] boatFields;

		public Boat(Field[] fields) {
			this.boatFields = fields;
		}

		public boolean isAt(int col, int row) {
			for (Field field : boatFields)
				if (field.getCol() == col && field.getRow() == row)
					return true;
			return false;
		}

		public boolean isSunk() {
			for (int i = 0; i < boatFields.length; i++) {
				if (boatFields[i].getState() != 1)
					return false;
			}
			return true;
		}

		public Field[] getFields() {
			return boatFields;
		}
	}
}
