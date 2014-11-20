package net.antineutrino.SudokuAlgos;

import java.util.*;

public class TerminalPattern {

	private static int[][] allCells = { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 },
			{ 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 1, 0 }, { 2, 1 },
			{ 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 },
			{ 2, 8 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 },
			{ 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 }, { 3, 0 }, { 4, 1 },
			{ 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 },
			{ 4, 8 }, { 4, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 },
			{ 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 }, { 5, 0 }, { 6, 1 },
			{ 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
			{ 6, 8 }, { 6, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 },
			{ 7, 5 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 7, 0 }, { 8, 1 },
			{ 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 },
			{ 8, 8 }, { 8, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 },
			{ 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 0, 0 } };

	/**
	 * Create a valid terminal pattern for a Sudoku puzzle, but randomly
	 * choosing cells from a blank puzzle and giving them randomly-selected
	 * starting values.
	 * 
	 * @return - a 2D byte array, terminal puzzle pattern
	 */
	public static byte[][] create() throws NoSolutionExistsException {
		// start with a blank puzzle
		byte[][] pattern = new byte[9][9];

		// create a randomized list of all the cells in the puzzle
		int[][] cells = allCells.clone();
		Collections.shuffle(Arrays.asList(cells));

		// set the first 9 cells in the list to 1 to 9
		for (byte i = 1; i < 10; i++) {
			pattern[cells[i - 1][0]][cells[i - 1][1]] = i;
		}

		// randomly set valid values for the rest of the cells
		List<Byte> possibles;
		Random rand = new Random();
		for (int i = 10; i < 82; i++) {
			possibles = new ArrayList<Byte>();

			// check what values are valid for this cell
			for (byte j = 1; j < 10; j++) {
				if (Rules.isPossible(pattern, cells[i][0], cells[i][1], j)) {
					possibles.add(j);
				}
			}

			// there should be SOME valid values
			if (possibles.size() == 0) {
				System.out.println(i);
				System.out.println(cells[i][0]);
				System.out.println(cells[i][1]);
				ArrayUtils.printArray(pattern);
				// TODO: Is this the correct exception?
				throw new NoSolutionExistsException(
						"Problem generating terminal pattern");
			}

			// randomly pick a valid value
			pattern[cells[i][0]][cells[i][1]] = possibles.get(rand
					.nextInt(possibles.size()));
		}

		return pattern;
	}

	/**
	 * 
	 * @param puzzle
	 * @return
	 */
	public static boolean validate(byte[][] puzzle) {
		// TODO: Everything.
		return false;
	}
}
