package battleship;

import java.util.ArrayList;
import java.util.Random;

import battleship.BoardModel.Boat;

/**
 * This class builds all Boats for a computer player
 *
 */
public class BoardCreator {
	private static int currentAmount;
	private static Field[][] fields;
	private Random generator = new Random();
	private ArrayList<Boat> boatsList;
	private static ArrayList<Field> list1;
	private static ArrayList<Field> list2;

	public BoardCreator() {
		fields = new Field[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				fields[i][j] = new Field(i, j);
			}
		}
		boatsList = new ArrayList<Boat>();
		boatsList.add(createBoat(4));
		boatsList.add(createBoat(3));
		boatsList.add(createBoat(3));
		boatsList.add(createBoat(2));
		boatsList.add(createBoat(2));
		boatsList.add(createBoat(2));
		boatsList.add(createBoat(1));
		boatsList.add(createBoat(1));
		boatsList.add(createBoat(1));
		boatsList.add(createBoat(1));
	}

	public Boat createBoat(int mastsAmount) {
		list1 = new ArrayList<>();

		do {
			currentAmount = 0;
			int x = generator.nextInt(10);
			int y = generator.nextInt(10);

			if (fields[x][y].getState() == 0) {

				currentAmount = 1;
				fields[x][y].setState(1);
				list1.add(fields[x][y]);
				if (mastsAmount > 1) {
					list2 = new ArrayList<>();
					addMast(x, y, mastsAmount);
				}
			}
		} while (!(list1.size() == mastsAmount));

		for (int i = 0; i < list1.size(); i++) {
			for (int j = list1.get(i).getCol() - 1; j <= list1.get(i).getCol() + 1; j++)
				if (j >= 0 && j <= 9)
					for (int k = list1.get(i).getRow() - 1; k <= list1.get(i).getRow() + 1; k++)
						if (k >= 0 && k <= 9)
							if (!(fields[j][k].getState() == 1)) {

								fields[j][k].setState(2);
							}
		}
		Field[] boat = new Field[list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			boat[i] = list1.get(i);
		}

		System.out.println(list1.size());

		return new Boat(boat);
	}

	public ArrayList<Field> addMast(int x, int y, int mastsAmount) {

		// add all potential masts to list2
		for (int i = x - 1; i <= x + 1; i++) {
			if (i >= 0 && i <= 9)
				for (int j = y - 1; j <= y + 1; j++)
					if (j >= 0 && j <= 9)
						if (((i != x && j == y) || (i == x && j != y)) && fields[i][j].getState() == 0) {

							list2.add(fields[i][j]);
						}
		}

		if (list2.size() > 0) {

			int r = generator.nextInt(list2.size());
			list2.get(r).setState(1);
			list1.add(list2.get(r));
			currentAmount++;
			if (currentAmount < mastsAmount) {
				list2.remove(r);
				addMast(list1.get(currentAmount - 1).getCol(), list1.get(currentAmount - 1).getRow(), mastsAmount);
			}
		} else {

			for (int i = 0; i < list1.size(); i++) {
				list1.get(i).setState(0);
			}
		}

		return list1;
	}

	public ArrayList<Boat> getBoats() {
		return boatsList;
	}

	public Field[][] getFields() {
		return fields;
	}

}
