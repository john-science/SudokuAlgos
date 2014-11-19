package net.antineutrino.SudokuAlgos;

public abstract class Solver {
	public abstract byte[][] solve(byte[][] start)
			throws NoSolutionExistsException;
}