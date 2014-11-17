package net.antineutrino.SudokuAlgos;

import java.util.*;

public class DFSSolver extends Solver {
	private int[][] current;
	private int[][][] possibles = new int[9][9][1];
	private List<int[]> unsolved = new ArrayList<int[]>();

	/**
	 * This abstract method solves a Sudoku puzzle using a simple Depth-First
	 * Search approach.
	 */
	public int[][] solve(int[][] start) throws NoSolutionExistsException {
		current = start.clone();

		build_possibles();
		build_unsolved();
		dsf_solve();

		return current;
	}

	/**
	 * 
	 * @throws NoSolutionExistsException
	 */
	private void dsf_solve() throws NoSolutionExistsException {
		// TODO: The meat.
	}

	/**
	 * 
	 * @throws NoSolutionExistsException
	 */
	private void build_possibles() throws NoSolutionExistsException {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (current[r][c] != 0) {
					possibles[r][c][0] = current[r][c];
				} else {
					// Determine which values are still possible for this cell
					List<Integer> temp = new ArrayList<Integer>();
					for (int i = 0; i < 9; i++) {
						if (isPossible(r, c, i)) {
							temp.add(i);
						}
					}

					// if nothing is possible, the puzzle has no solution
					if (temp.size() == 0) {
						throw new NoSolutionExistsException("hey");
					} else if (temp.size() == 1) {
						// If there is only one possibility, just fix it now.
						current[r][c] = temp.get(0);
					}

					// Add those values to the possibles array.
					int[] poss = new int[temp.size()];
					for (int i = 0; i < temp.size(); i++) {
						poss[i] = temp.get(i).intValue();
					}
					possibles[r][c] = poss;
				}
			}
		}
	}

	/**
	 * 
	 * @throws NoSolutionExistsException
	 */
	private void build_unsolved() throws NoSolutionExistsException {
		// find all unsolved cells in the puzzle
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (current[r][c] == 0) {
					unsolved.add(new int[] { r, c });
				}
			}
		}

		// shuffle the unsolved list, to remove bias
		long seed = System.nanoTime();
		Collections.shuffle(unsolved, new Random(seed));
	}

	/**
	 * Determine if a particular value is possible, for a given cell. The logic
	 * here checks the values already filled in each row, column, and block in
	 * the puzzle.
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	private boolean isPossible(int row, int col, int val) {
		if (isPossibleRow(row, col, val)) {
			if (isPossibleCol(row, col, val)) {
				if (isPossibleBlock(row, col, val)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	private boolean isPossibleRow(int row, int col, int val) {
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
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	private boolean isPossibleCol(int row, int col, int val) {
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
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	private boolean isPossibleBlock(int row, int col, int val) {
		int[][] elements = blocks[row][col];

		for (int i = 0; i < 8; i++) {
			if (current[elements[i][0]][elements[i][1]] == val) {
				return false;
			}
		}

		return true;
	}

	/**
	 * This array represents all of the sister cells in each block for each cell
	 * in the puzzle.
	 * 
	 * NOTE: This is a static attribute, though it could be calculated on the
	 * fly easily, for performance.
	 */
	private static int[][][][] blocks = {
			// row 1
			{
					{ { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 1
					{ { 0, 0 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 2
					{ { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 3
					{ { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 }, { 1, 5 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 4
					{ { 0, 3 }, { 0, 5 }, { 1, 3 }, { 1, 4 }, { 1, 5 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 5
					{ { 0, 3 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 1, 5 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 6
					{ { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } }, // col 7
					{ { 0, 6 }, { 0, 8 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } }, // col 8
					{ { 0, 6 }, { 0, 7 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } } }, // col 9
			// row 2
			{
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 }, { 1, 2 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 1
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 2 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 2
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 },
							{ 2, 0 }, { 2, 1 }, { 2, 2 } }, // col 3
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 4 }, { 1, 5 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 4
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 5 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 5
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 },
							{ 2, 3 }, { 2, 4 }, { 2, 5 } }, // col 6
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 7 }, { 1, 8 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } }, // col 7
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 8 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } }, // col 8
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 },
							{ 2, 6 }, { 2, 7 }, { 2, 8 } } }, // col 9
			// row 3
			{
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 },
							{ 1, 2 }, { 2, 1 }, { 2, 2 } }, // col 1
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 },
							{ 1, 2 }, { 2, 0 }, { 2, 2 } }, // col 2
					{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 },
							{ 1, 2 }, { 2, 0 }, { 2, 1 } }, // col 3
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 },
							{ 1, 5 }, { 2, 4 }, { 2, 5 } }, // col 4
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 },
							{ 1, 5 }, { 2, 3 }, { 2, 5 } }, // col 5
					{ { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 3 }, { 1, 4 },
							{ 1, 5 }, { 2, 3 }, { 2, 4 } }, // col 6
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 },
							{ 1, 8 }, { 2, 7 }, { 2, 8 } }, // col 7
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 },
							{ 1, 8 }, { 2, 6 }, { 2, 8 } }, // col 8
					{ { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 6 }, { 1, 7 },
							{ 1, 8 }, { 2, 6 }, { 2, 7 } } }, // col 9
			// row 4
			{
					{ { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 }, { 5, 2 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 1
					{ { 4, 0 }, { 4, 2 }, { 5, 0 }, { 5, 1 }, { 5, 2 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 2
					{ { 4, 0 }, { 4, 1 }, { 5, 0 }, { 5, 1 }, { 5, 2 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 3
					{ { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 }, { 5, 5 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 4
					{ { 4, 3 }, { 4, 5 }, { 5, 3 }, { 5, 4 }, { 5, 5 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 5
					{ { 4, 3 }, { 4, 4 }, { 5, 3 }, { 5, 4 }, { 5, 5 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 6
					{ { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } }, // col 7
					{ { 4, 6 }, { 4, 8 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } }, // col 8
					{ { 4, 6 }, { 4, 7 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } } }, // col 9
			// row 5
			{
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 1 }, { 5, 2 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 1
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 2 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 2
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 },
							{ 6, 0 }, { 6, 1 }, { 6, 2 } }, // col 3
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 4 }, { 5, 5 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 4
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 5 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 5
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 },
							{ 6, 3 }, { 6, 4 }, { 6, 5 } }, // col 6
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 7 }, { 5, 8 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } }, // col 7
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 8 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } }, // col 8
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 },
							{ 6, 6 }, { 6, 7 }, { 6, 8 } } }, // col 9
			// row 6
			{
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 },
							{ 5, 2 }, { 6, 1 }, { 6, 2 } }, // col 1
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 },
							{ 5, 2 }, { 6, 0 }, { 6, 2 } }, // col 2
					{ { 4, 0 }, { 4, 1 }, { 4, 2 }, { 5, 0 }, { 5, 1 },
							{ 5, 2 }, { 6, 0 }, { 6, 1 } }, // col 3
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 },
							{ 5, 5 }, { 6, 4 }, { 6, 5 } }, // col 4
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 },
							{ 5, 5 }, { 6, 3 }, { 6, 5 } }, // col 5
					{ { 4, 3 }, { 4, 4 }, { 4, 5 }, { 5, 3 }, { 5, 4 },
							{ 5, 5 }, { 6, 3 }, { 6, 4 } }, // col 6
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 },
							{ 5, 8 }, { 6, 7 }, { 6, 8 } }, // col 7
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 },
							{ 5, 8 }, { 6, 6 }, { 6, 8 } }, // col 8
					{ { 4, 6 }, { 4, 7 }, { 4, 8 }, { 5, 6 }, { 5, 7 },
							{ 5, 8 }, { 6, 6 }, { 6, 7 } } }, // col 9
			// row 7
			{
					{ { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 }, { 7, 2 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 1
					{ { 6, 0 }, { 6, 2 }, { 7, 0 }, { 7, 1 }, { 7, 2 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 2
					{ { 6, 0 }, { 6, 1 }, { 7, 0 }, { 7, 1 }, { 7, 2 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 3
					{ { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 }, { 7, 5 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 4
					{ { 6, 3 }, { 6, 5 }, { 7, 3 }, { 7, 4 }, { 7, 5 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 5
					{ { 6, 3 }, { 6, 4 }, { 7, 3 }, { 7, 4 }, { 7, 5 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 6
					{ { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 }, { 7, 8 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } }, // col 7
					{ { 6, 6 }, { 6, 8 }, { 7, 6 }, { 7, 7 }, { 7, 8 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } }, // col 8
					{ { 6, 6 }, { 6, 7 }, { 7, 6 }, { 7, 7 }, { 7, 8 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } } }, // col 9
			// row 8
			{
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 1 }, { 7, 2 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 1
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 2 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 2
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 },
							{ 8, 0 }, { 8, 1 }, { 8, 2 } }, // col 3
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 4 }, { 7, 5 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 4
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 5 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 5
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 },
							{ 8, 3 }, { 8, 4 }, { 8, 5 } }, // col 6
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 7 }, { 7, 8 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } }, // col 7
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 8 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } }, // col 8
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 },
							{ 8, 6 }, { 8, 7 }, { 8, 8 } } }, // col 9
			// row 9
			{
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 },
							{ 7, 2 }, { 8, 1 }, { 8, 2 } }, // col 1
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 },
							{ 7, 2 }, { 8, 0 }, { 8, 2 } }, // col 2
					{ { 6, 0 }, { 6, 1 }, { 6, 2 }, { 7, 0 }, { 7, 1 },
							{ 7, 2 }, { 8, 0 }, { 8, 1 } }, // col 3
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 },
							{ 7, 5 }, { 8, 4 }, { 8, 5 } }, // col 4
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 },
							{ 7, 5 }, { 8, 3 }, { 8, 5 } }, // col 5
					{ { 6, 3 }, { 6, 4 }, { 6, 5 }, { 7, 3 }, { 7, 4 },
							{ 7, 5 }, { 8, 3 }, { 8, 4 } }, // col 6
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 },
							{ 7, 8 }, { 8, 7 }, { 8, 8 } }, // col 7
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 },
							{ 7, 8 }, { 8, 6 }, { 8, 8 } }, // col 8
					{ { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 6 }, { 7, 7 },
							{ 7, 8 }, { 8, 6 }, { 8, 7 } } } // col 9
	};
}