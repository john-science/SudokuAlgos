package net.antineutrino.SudokuAlgos;

public abstract class Creator {
	public abstract byte[][] create(byte difficulty)
			throws NoSolutionExistsException;
}
