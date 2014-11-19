package net.antineutrino.SudokuAlgos;

// TODO: How to make this a Maven library, not app:
//       http://malalanayake.wordpress.com/2014/03/10/create-simple-maven-repository-on-github/

/**
 * This is a quick test of the speed of the DFS Solver for Sudoku puzzles of
 * various difficulties.
 */
public class DFSSolverSpeedTesting {
	public static void main(String[] args) {
		byte[][] veasy = { { 0, 6, 7, 4, 2, 5, 0, 0, 9 },
				{ 0, 0, 0, 1, 8, 0, 0, 6, 0 }, { 8, 9, 0, 6, 0, 7, 0, 5, 2 },
				{ 4, 0, 0, 0, 6, 0, 9, 1, 3 }, { 6, 0, 2, 3, 9, 4, 5, 7, 0 },
				{ 9, 7, 3, 8, 0, 1, 6, 2, 0 }, { 0, 0, 0, 2, 4, 3, 7, 9, 5 },
				{ 0, 2, 4, 9, 7, 6, 8, 0, 0 }, { 0, 3, 0, 5, 1, 8, 2, 0, 0 } };
		long startTime = System.nanoTime();
		testDFSSolver(veasy);
		long endTime = System.nanoTime();
		System.out.println("\nvery easy");
		System.out.println(String.format("%,d", endTime - startTime));

		byte[][] easy = { { 2, 0, 0, 0, 5, 7, 3, 8, 9 },
				{ 0, 3, 0, 8, 9, 1, 0, 0, 0 }, { 7, 0, 9, 0, 0, 3, 0, 1, 6 },
				{ 0, 7, 3, 0, 8, 9, 2, 6, 0 }, { 0, 0, 2, 5, 0, 6, 0, 0, 0 },
				{ 0, 9, 0, 3, 0, 4, 0, 5, 7 }, { 0, 5, 0, 9, 0, 8, 0, 2, 0 },
				{ 9, 2, 8, 7, 0, 5, 6, 0, 0 }, { 0, 0, 0, 0, 3, 0, 0, 0, 0 } };
		startTime = System.nanoTime();
		testDFSSolver(easy);
		endTime = System.nanoTime();
		System.out.println("\neasy");
		System.out.println(String.format("%,d", endTime - startTime));

		byte[][] medium = { { 0, 9, 0, 6, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 3, 0, 9, 0, 1 }, { 0, 3, 0, 2, 0, 8, 0, 0, 0 },
				{ 7, 0, 9, 0, 0, 0, 0, 0, 4 }, { 0, 4, 0, 3, 0, 7, 0, 9, 0 },
				{ 8, 0, 3, 0, 1, 0, 5, 0, 7 }, { 0, 5, 0, 7, 0, 2, 0, 1, 0 },
				{ 9, 0, 4, 0, 5, 0, 7, 0, 6 }, { 0, 1, 0, 9, 0, 6, 0, 5, 8 } };
		startTime = System.nanoTime();
		testDFSSolver(medium);
		endTime = System.nanoTime();
		System.out.println("\nmedium");
		System.out.println(String.format("%,d", endTime - startTime));

		byte[][] hard = { { 0, 0, 0, 0, 0, 0, 0, 3, 2 },
				{ 3, 6, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 5, 0, 8 },
				{ 8, 7, 0, 0, 0, 0, 0, 0, 0 }, { 0, 9, 0, 0, 0, 3, 0, 4, 0 },
				{ 6, 0, 0, 8, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 3 },
				{ 5, 0, 1, 6, 3, 0, 4, 0, 0 }, { 0, 3, 9, 1, 4, 8, 7, 5, 6 } };
		startTime = System.nanoTime();
		testDFSSolver(hard);
		endTime = System.nanoTime();
		System.out.println("\nhard");
		System.out.println(String.format("%,d", endTime - startTime));

		byte[][] evil = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 5, 2, 3 }, { 0, 0, 0, 0, 0, 0, 0, 1, 8 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 9, 0, 7, 4, 0, 6, 0 },
				{ 0, 0, 4, 6, 1, 0, 0, 0, 7 }, { 0, 5, 8, 0, 4, 3, 0, 0, 0 },
				{ 0, 4, 0, 0, 2, 0, 0, 3, 0 }, { 0, 6, 7, 0, 8, 1, 0, 9, 4 } };
		startTime = System.nanoTime();
		testDFSSolver(evil);
		endTime = System.nanoTime();
		System.out.println("\nevil");
		System.out.println(String.format("%,d", endTime - startTime));
	}

	public static void testDFSSolver(byte[][] problem) {
		DFSSolver dfs = new DFSSolver();
		try {
			//ArrayUtils.printArray(problem);
			byte[][] solution = dfs.solve(problem);
			//System.out.println("\nSolution:");
			//ArrayUtils.printArray(solution);
		} catch (NoSolutionExistsException e) {
			System.out.println(e);
		}
	}
}
