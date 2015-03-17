package net.antineutrino.SudokuAlgos;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.*;
import java.util.Arrays;

/**
 * Unit test for Depth-First Search Sudoku Solver, using a Project Euler puzzle.
 */
public class ProjectEuler96SolverTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ProjectEuler96SolverTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ProjectEuler96SolverTest.class);
	}

	/**
	 * Solve a single Sudoku puzzle using the DFS solver.
	 */
	public static byte[][] dfsSolverTestCase(byte[][] problem) {
		DFSSolver dfs = new DFSSolver();
		try {
			return dfs.solve(problem);

		} catch (NoSolutionExistsException e) {
			System.out.println(e);
			return new byte[9][9];
		}
	}

	/**
	 * This is the meat of the Project Euler puzzle.
	 * Take the top-left three cells from the solved puzzle and pretend
	 * they are a three digit number.
	 * Add up these three digit numbers from all 50 puzzles presented.
	 */
	public void testSumAll50Puzzles() {
		byte[][][] puzzles = parseInputFile();

		int cornerSum = 0;
		for (int i = 0; i < 50; i++) {
			byte[][] solution = ProjectEuler96SolverTest
					.dfsSolverTestCase(puzzles[i]);

			int cornerVal = Integer.valueOf(solution[0][0]) * 100
					+ Integer.valueOf(solution[0][1]) * 10
					+ Integer.valueOf(solution[0][2]);

			System.out.println(cornerVal);
			cornerSum += cornerVal;
		}

		assertEquals(cornerSum, 6150);
	}

	/**
	 * Parse the Project Euler input File.
	 */
	public byte[][][] parseInputFile() {
		byte[][][] puzzles = new byte[50][9][9];
		String puzzlesFile = "/ProjectEuler96.txt";

		// read the input file as a text stream.
		try {
			InputStream ips = getClass().getResourceAsStream(puzzlesFile);

			Assert.assertNotNull(ips);

			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			int lineNum = -1;
			while ((line = br.readLine()) != null) {
				lineNum += 1;

				if (lineNum % 10 == 0) {
					continue;
				}

				String[] lineArray = line.split("");
				for (int j = 1; j <= 9; j++) {
					puzzles[lineNum / 10][(lineNum % 10) - 1][j - 1] += Byte
							.parseByte(lineArray[j]);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return puzzles;
	}

}
