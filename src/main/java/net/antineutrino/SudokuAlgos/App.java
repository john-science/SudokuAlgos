package net.antineutrino.SudokuAlgos;

import java.util.Arrays;

// TODO: How to make this a Maven library, not app:
//       http://malalanayake.wordpress.com/2014/03/10/create-simple-maven-repository-on-github/

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		int[][] start = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 5, 2, 3 }, { 0, 0, 0, 0, 0, 0, 0, 1, 8 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 9, 0, 7, 4, 0, 6, 0 },
				{ 0, 0, 4, 6, 1, 0, 0, 0, 7 }, { 0, 5, 8, 0, 4, 3, 0, 0, 0 },
				{ 0, 4, 0, 0, 2, 0, 0, 5, 0 }, { 0, 6, 7, 0, 8, 1, 0, 9, 4 } };
		DFSSolver dfs = new DFSSolver();
		try {
			int[][] solution = dfs.solve(start);
			System.out.println(Arrays.deepToString(solution));
		} catch (NoSolutionExistsException e) {
			System.out.println(e);
		}
	}
}
