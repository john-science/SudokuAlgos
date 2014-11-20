package net.antineutrino.SudokuAlgos;

public class Rules {

	/**
	 * Determine if a particular value is possible, for a given cell. The logic
	 * here checks the values already filled in each row, column, and block in
	 * the puzzle.
	 * 
	 * @return true if val would be a valid addition to the puzzle.
	 */
	public static boolean isPossible(byte[][] current, int row, int col,
			byte val) {
		if (isPossibleCol(current, row, col, val)) {
			if (isPossibleRow(current, row, col, val)) {
				if (isPossibleBlock(current, row, col, val)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Determine if a particular value exists else where in the col.
	 * 
	 * @return true if val would be a valid addition to the column
	 */
	public static boolean isPossibleCol(byte[][] current, int row, int col,
			byte val) {
		for (int i = 0; i < 9; i++) {
			if (i == col) {
				continue;
			}
			if (current[row][i] == val) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Determine if a particular value exists else where in the row.
	 * 
	 * @return true if val would be a valid addition to the row
	 */
	public static boolean isPossibleRow(byte[][] current, int row, int col,
			byte val) {
		for (int i = 0; i < 9; i++) {
			if (i == row) {
				continue;
			}
			if (current[i][col] == val) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Determine if a particular value exists else where in the block.
	 * 
	 * @return true if val would be a valid addition to the block
	 */
	public static boolean isPossibleBlock(byte[][] current, int row, int col,
			byte val) {
		byte[][] elements = Solver.blocks[row][col];

		for (int i = 0; i < 8; i++) {
			if (current[elements[i][0]][elements[i][1]] == val) {
				return false;
			}
		}

		return true;
	}
}
