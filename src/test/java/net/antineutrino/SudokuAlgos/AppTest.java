package net.antineutrino.SudokuAlgos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public static byte[][] testDFSSolver(byte[][] problem) {
		DFSSolver dfs = new DFSSolver();
		try {
			return dfs.solve(problem);
		} catch (NoSolutionExistsException e) {
			System.out.println(e);
			return new byte[9][9];
		}
	}

	/**
	 * Testing a very easy known puzzle and solution.
	 */
	public void testVeryEasyPuzzle() {
		byte[][] veasy = { { 0, 6, 7, 4, 2, 5, 0, 0, 9 },
				{ 0, 0, 0, 1, 8, 0, 0, 6, 0 }, { 8, 9, 0, 6, 0, 7, 0, 5, 2 },
				{ 4, 0, 0, 0, 6, 0, 9, 1, 3 }, { 6, 0, 2, 3, 9, 4, 5, 7, 0 },
				{ 9, 7, 3, 8, 0, 1, 6, 2, 0 }, { 0, 0, 0, 2, 4, 3, 7, 9, 5 },
				{ 0, 2, 4, 9, 7, 6, 8, 0, 0 }, { 0, 3, 0, 5, 1, 8, 2, 0, 0 } };

		long startTime = System.nanoTime();
		byte[][] test_solution = testDFSSolver(veasy);
		long endTime = System.nanoTime();

		System.out.printf("\nTiming Known Sudoku Puzzles");
		System.out.printf("\nvery easy:\t"
				+ String.format("%,d", endTime - startTime) + " ms");

		byte[][] solution = { { 3, 6, 7, 4, 2, 5, 1, 8, 9 },
				{ 2, 4, 5, 1, 8, 9, 3, 6, 7 }, { 8, 9, 1, 6, 3, 7, 4, 5, 2 },
				{ 4, 5, 8, 7, 6, 2, 9, 1, 3 }, { 6, 1, 2, 3, 9, 4, 5, 7, 8 },
				{ 9, 7, 3, 8, 5, 1, 6, 2, 4 }, { 1, 8, 6, 2, 4, 3, 7, 9, 5 },
				{ 5, 2, 4, 9, 7, 6, 8, 3, 1 }, { 7, 3, 9, 5, 1, 8, 2, 4, 6 } };
		assertTrue(test_solution == solution);
	}

	/**
	 * Testing an easy known puzzle and solution.
	 */
	public void testEasyPuzzle() {
		byte[][] easy = { { 2, 0, 0, 0, 5, 7, 3, 8, 9 },
				{ 0, 3, 0, 8, 9, 1, 0, 0, 0 }, { 7, 0, 9, 0, 0, 3, 0, 1, 6 },
				{ 0, 7, 3, 0, 8, 9, 2, 6, 0 }, { 0, 0, 2, 5, 0, 6, 0, 0, 0 },
				{ 0, 9, 0, 3, 0, 4, 0, 5, 7 }, { 0, 5, 0, 9, 0, 8, 0, 2, 0 },
				{ 9, 2, 8, 7, 0, 5, 6, 0, 0 }, { 0, 0, 0, 0, 3, 0, 0, 0, 0 } };

		long startTime = System.nanoTime();
		byte[][] test_solution = testDFSSolver(easy);
		long endTime = System.nanoTime();

		System.out.printf("\neasy:\t\t"
				+ String.format("%,d", endTime - startTime) + " ms");

		byte[][] solution = { { 5, 9, 8, 6, 7, 1, 2, 4, 3 },
				{ 6, 7, 2, 5, 3, 4, 9, 8, 1 }, { 4, 3, 1, 2, 9, 8, 6, 7, 5 },
				{ 7, 6, 9, 8, 2, 5, 1, 3, 4 }, { 1, 4, 5, 3, 6, 7, 8, 9, 2 },
				{ 8, 2, 3, 4, 1, 9, 5, 6, 7 }, { 3, 5, 6, 7, 8, 2, 4, 1, 9 },
				{ 9, 8, 4, 1, 5, 3, 7, 2, 6 }, { 2, 1, 7, 9, 4, 6, 3, 5, 8 } };
		assertTrue(test_solution == solution);
	}

	/**
	 * Testing a medium known puzzle and solution.
	 */
	public void testMediumPuzzle() {
		byte[][] medium = { { 0, 9, 0, 6, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 3, 0, 9, 0, 1 }, { 0, 3, 0, 2, 0, 8, 0, 0, 0 },
				{ 7, 0, 9, 0, 0, 0, 0, 0, 4 }, { 0, 4, 0, 3, 0, 7, 0, 9, 0 },
				{ 8, 0, 3, 0, 1, 0, 5, 0, 7 }, { 0, 5, 0, 7, 0, 2, 0, 1, 0 },
				{ 9, 0, 4, 0, 5, 0, 7, 0, 6 }, { 0, 1, 0, 9, 0, 6, 0, 5, 8 } };

		long startTime = System.nanoTime();
		byte[][] test_solution = testDFSSolver(medium);
		long endTime = System.nanoTime();

		System.out.printf("\nmedium:\t\t"
				+ String.format("%,d", endTime - startTime) + " ms");

		byte[][] solution = { { 5, 9, 8, 6, 7, 1, 2, 4, 3 },
				{ 6, 7, 2, 5, 3, 4, 9, 8, 1 }, { 4, 3, 1, 2, 9, 8, 6, 7, 5 },
				{ 7, 6, 9, 8, 2, 5, 1, 3, 4 }, { 1, 4, 5, 3, 6, 7, 8, 9, 2 },
				{ 8, 2, 3, 4, 1, 9, 5, 6, 7 }, { 3, 5, 6, 7, 8, 2, 4, 1, 9 },
				{ 9, 8, 4, 1, 5, 3, 7, 2, 6 }, { 2, 1, 7, 9, 4, 6, 3, 5, 8 } };
		assertTrue(test_solution == solution);
	}

	/**
	 * Testing a hard known puzzle and solution.
	 */
	public void testHardPuzzle() {
		byte[][] hard = { { 0, 0, 0, 0, 0, 0, 0, 3, 2 },
				{ 3, 6, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 5, 0, 8 },
				{ 8, 7, 0, 0, 0, 0, 0, 0, 0 }, { 0, 9, 0, 0, 0, 3, 0, 4, 0 },
				{ 6, 0, 0, 8, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 3 },
				{ 5, 0, 1, 6, 3, 0, 4, 0, 0 }, { 0, 3, 9, 1, 4, 8, 7, 5, 6 } };

		long startTime = System.nanoTime();
		byte[][] test_solution = testDFSSolver(hard);
		long endTime = System.nanoTime();

		System.out.printf("\nhard:\t\t"
				+ String.format("%,d", endTime - startTime) + " ms");

		byte[][] solution = { { 9, 1, 8, 4, 7, 5, 6, 3, 2 },
				{ 3, 6, 5, 2, 8, 1, 9, 7, 4 }, { 4, 2, 7, 3, 9, 6, 5, 1, 8 },
				{ 8, 7, 4, 5, 2, 9, 3, 6, 1 }, { 1, 9, 2, 7, 6, 3, 8, 4, 5 },
				{ 6, 5, 3, 8, 1, 4, 2, 9, 7 }, { 7, 4, 6, 9, 5, 2, 1, 8, 3 },
				{ 5, 8, 1, 6, 3, 7, 4, 2, 9 }, { 2, 3, 9, 1, 4, 8, 7, 5, 6 } };
		assertTrue(test_solution == solution);
	}

	/**
	 * Testing an evil known puzzle and solution.
	 */
	public void testEvilPuzzle() {
		byte[][] evil = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 5, 2, 3 }, { 0, 0, 0, 0, 0, 0, 0, 1, 8 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 9, 0, 7, 4, 0, 6, 0 },
				{ 0, 0, 4, 6, 1, 0, 0, 0, 7 }, { 0, 5, 8, 0, 4, 3, 0, 0, 0 },
				{ 0, 4, 0, 0, 2, 0, 0, 3, 0 }, { 0, 6, 7, 0, 8, 1, 0, 9, 4 } };

		long startTime = System.nanoTime();
		byte[][] test_solution = testDFSSolver(evil);
		long endTime = System.nanoTime();

		System.out.printf("\nevil:\t\t"
				+ String.format("%,d", endTime - startTime) + " ms");

		byte[][] solution = { { 7, 2, 5, 1, 3, 8, 6, 4, 9 },
				{ 1, 8, 6, 4, 9, 7, 5, 2, 3 }, { 4, 9, 3, 2, 6, 5, 7, 1, 8 },
				{ 6, 7, 2, 3, 5, 9, 4, 8, 1 }, { 5, 1, 9, 8, 7, 4, 3, 6, 2 },
				{ 8, 3, 4, 6, 1, 2, 9, 5, 7 }, { 2, 5, 8, 9, 4, 3, 1, 7, 6 },
				{ 9, 4, 1, 7, 2, 6, 8, 3, 5 }, { 3, 6, 7, 5, 8, 1, 2, 9, 4 } };
		assertTrue(test_solution == solution);
	}
}
