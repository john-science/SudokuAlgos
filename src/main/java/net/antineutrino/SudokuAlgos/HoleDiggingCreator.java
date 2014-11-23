package net.antineutrino.SudokuAlgos;

public class HoleDiggingCreator extends Creator {

	public byte[][] create(byte difficulty) throws NoSolutionExistsException {
		byte[][] pattern = TerminalPattern.create(new DFSSolver());

		return pattern;
	}
}
