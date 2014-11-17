package net.antineutrino.SudokuAlgos;

public abstract class Solver {
	public abstract int[][] solve(int[][] start)
			throws NoSolutionExistsException;
}