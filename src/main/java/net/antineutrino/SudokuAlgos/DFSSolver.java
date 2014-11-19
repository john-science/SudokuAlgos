package net.antineutrino.SudokuAlgos;

import java.util.*;

public class DFSSolver extends Solver {
	private byte[][] current;
	private byte[][][] possibles = new byte[9][9][1];
	private List<int[]> unsolved = new ArrayList<int[]>();

	/**
	 * This abstract method solves a Sudoku puzzle using a simple Depth-First
	 * Search approach.
	 */
	public byte[][] solve(byte[][] start) throws NoSolutionExistsException {
		current = start.clone();

		check_for_duplicates();
		build_possibles();
		build_unsolved();
		dsf_solve();

		return current;
	}

	/**
	 * 
	 * @throws NoSolutionExistsException
	 */
	private void check_for_duplicates() throws NoSolutionExistsException {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (current[r][c] != 0) {
					if (!isPossible(r, c, current[r][c])) {
						throw new NoSolutionExistsException(
								"The puzzle is not solvable.");
					}
				}
			}
		}
	}

	/**
	 * 
	 * @throws NoSolutionExistsException
	 */
	private void dsf_solve() throws NoSolutionExistsException {
		int i = 0;

		// loop through the unsolved cells list
		while (i > -1 && i < unsolved.size()) {
			// System.out.println(i);
			int[] u = unsolved.get(i);
			// TODO: I need to test for the effects of this on speed
			// TODO: byte[] poss = possibles[u[0]][u[1]];

			// if current cell is empty, easy.
			if (current[u[0]][u[1]] == 0) {
				boolean found1 = false;
				for (int k = 0; k < possibles[u[0]][u[1]].length; k++) {
					if (isPossible(u[0], u[1], possibles[u[0]][u[1]][k])) {
						current[u[0]][u[1]] = possibles[u[0]][u[1]][k];
						i += 1;
						found1 = true;
						break;
					}
				}

				if (!found1) {
					current[u[0]][u[1]] = 0;
					i -= 1;
				}
			} else {
				// if current cell is not empty, figure out where its value lies
				// in its possibles list
				int j = ArrayUtils.findIndex(possibles[u[0]][u[1]],
						current[u[0]][u[1]]);

				// if we've run past the end of the possibles list, take a step
				// back in the unsolved list
				if (j == possibles[u[0]][u[1]].length - 1) {
					current[u[0]][u[1]] = 0;
					i -= 1;
				} else {
					// Go to the next valid cell in the possibles list
					boolean found2 = false;
					for (int k = j + 1; k < possibles[u[0]][u[1]].length; k++) {
						if (isPossible(u[0], u[1], possibles[u[0]][u[1]][k])) {
							current[u[0]][u[1]] = possibles[u[0]][u[1]][k];
							i += 1;
							found2 = true;
							break;
						}
					}

					if (!found2) {
						current[u[0]][u[1]] = 0;
						i -= 1;
					}
				}
			}
		}

		if (i <= 0) {
			throw new NoSolutionExistsException("The puzzle is not solvable.");
		}
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
					List<Byte> temp = new ArrayList<Byte>();
					for (byte i = 1; i < 10; i++) {
						if (isPossible(r, c, i)) {
							temp.add(i);
						}
					}

					// if nothing is possible, the puzzle has no solution
					if (temp.size() == 0) {
						throw new NoSolutionExistsException(
								"The puzzle is not solvable.");
					} else if (temp.size() == 1) {
						// If there is only one possibility, fix it now.
						current[r][c] = temp.get(0);
					}

					// Add those values to the possibles array.
					byte[] poss = new byte[temp.size()];
					for (int i = 0; i < temp.size(); i++) {
						poss[i] = temp.get(i).byteValue();
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
	private boolean isPossible(int row, int col, byte val) {
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
	private boolean isPossibleRow(int row, int col, byte val) {
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
	private boolean isPossibleCol(int row, int col, byte val) {
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
	private boolean isPossibleBlock(int row, int col, byte val) {
		byte[][] elements = blocks[row][col];

		for (int i = 0; i < 8; i++) {
			if (current[elements[i][0]][elements[i][1]] == val) {
				return false;
			}
		}

		return true;
	}
}