package net.antineutrino.SudokuAlgos;

/**
 * A custom exception for when a given Sudoku puzzle has no valid solution.
 */
public class NoSolutionExistsException extends Exception {

	private static final long serialVersionUID = 1729L;

	public NoSolutionExistsException(String message) {
		super(message);
	}
}
