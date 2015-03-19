package net.antineutrino.SudokuAlgos;

import java.util.*;

public class TerminalPattern {

	private static int[][] allCells = { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 },
			{ 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 1, 0 }, { 2, 1 },
			{ 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 },
			{ 2, 8 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 },
			{ 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 }, { 3, 0 }, { 4, 1 },
			{ 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 },
			{ 4, 8 }, { 4, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 },
			{ 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 }, { 5, 0 }, { 6, 1 },
			{ 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
			{ 6, 8 }, { 6, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 },
			{ 7, 5 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 7, 0 }, { 8, 1 },
			{ 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 },
			{ 8, 8 }, { 8, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 },
			{ 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 0, 0 } };

	/**
	 * Create a valid terminal pattern for a Sudoku puzzle, by randomly starting
	 * 9 cells and solving with a provided solving algorithm.
	 * 
	 * @return - a 2D byte array, terminal puzzle pattern
	 */
	public static byte[][] create(Solver s) throws NoSolutionExistsException {
		// start with a blank puzzle
		byte[][] pattern = new byte[9][9];

		// create a randomized list of all the cells in the puzzle
		int[][] cells = allCells.clone();
		Collections.shuffle(Arrays.asList(cells));

		// set the first 9 cells in the list to 1 to 9
		for (byte i = 1; i < 10; i++) {
			pattern[cells[i - 1][0]][cells[i - 1][1]] = i;
		}

		// solve using the provided solving pattern
		return s.solve(pattern);
	}

	/**
	 * Verify that a final 9x9 grid is indeed a valid Sudoku solution.
	 * 
	 * @param puzzle to be tested
	 * @return true if it is a valid puzzle, otherwise false
	 */
	public static boolean validate(byte[][] puzzle) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (!Rules.isPossible(puzzle, r, c, puzzle[r][c])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static byte[][] randomize(byte[][] pattern, int iterations) {
	    // quick validation, so that all subsequent static method calls won't have to validate.
	    if (pattern.length != 9 or pattern[0].length != 9){
	        throw new InvalidSolutionException("Pattern was the wrong dimensions.");
	    }
	   
	    int r = 0;
	    for (int i=0; i < iterations) {
	        // Min + (int)(Math.random() * ((Max - Min) + 1))
	        r = (int)(Math.random() * 5;
	
	        if (r == 1) {
	            TerminalPattern.randomizeRows(pattern);
	        } else if (r == 2) {
	            TerminalPattern.randomizeTranspose(pattern);
	        } else if (r == 3) {
	            TerminalPattern.randomizeRotateClockwise(pattern);
	        } else if (r == 4) {
	            TerminalPattern.randomizeBlockRows(pattern);
	        } else {
	            TerminalPattern.randomizeNumbers(pattern);
	        }
	        // Also, could random columns and column blocks
	    }
	   
	    // Java, call by reference or value? Do I need to return this?
	    return pattern;
	}

	private static byte[][] randomizeRows(byte[][] pattern) {
	    // pick one of 3 blocks, swap two rows
	   
	    // choose block row
	    byte block_num = (byte)(Math.random() * 3);
	   
	    // choose rows in block
	    byte[][] row_swaps = {{0, 1}, {0, 2}, {1, 2}};
	    byte[] rows = Collections.shuffle(row_swaps)[0];
	   
	    // calculate actual row numbers
	    byte row1 = rows[0] + block_num;  // TODO: probably should be int.
	    byte row2 = rows[1] + block_num;
	   
	    // do the swap
	   
	    return pattern;
	}
	
	// Possibly the same as rotating by 90 degrees, then reversing each row
	// TODO: If so, this is unncessary.
	private static byte[][] randomizeTranspose(byte[][] pattern) {
	    /**
	     * swap:
	     * (0, 0) & (8, 8)
	     * (0, 1) & (8, 7)
	     * (0, 2) & (8, 6)
	     * ...
	     * (3, 0) & (5, 8)
	     */
	    // only one option
	    int r = 0;
	    int c = -1;
	    int temp_val = 0;
	    int row_max = 7;
	    for (int i = 0; i < 36; i++) {
	        c += 1;
	        if (c > row_max) {
	            c = 0;
	            r += 1;
	            row_max -= 1;
	        }
	       
	        temp_val = pattern(r, c);
	        pattern(r, c) = pattern(8 - r, 8 - c);
	        pattern(8 - r, 8 - c) = temp_val;
	    }
	   
	    return pattern;
	}

	private static byte[][] randomizeRotateClockwise(byte[][] pattern) {
	    // 3 options
	    // TODO: Everything
	    return pattern;
	}
	
	private static byte[][] randomizeBlockRows(byte[][] pattern) {
	    // 5 options
	    // TODO: Everything
	    return pattern;
	}

	private static byte[][] randomizeNumbers(byte[][] pattern) {
	    // pick two numbers to switch
	    Random rand = new Random();
	    byte first = 1 + (byte)(Math.random() * 9);
	    byte second = first;
	    while (first == second) {
	        second = 1 + (byte)(Math.random() * 9);
	    }
	   
	    // switch those two numbers in the puzzle
	    for (int r = 0; r < 9; r ++) {
	        for (int c = 0; r < c; c ++) {
	            if (pattern[r][c] == first) {
	                pattern[r][c] = second;
	            } else if (pattern[r][c] == second) {
	                // TODO: make sure this second thing isn't hit, if the first is.
	                pattern[r][c] = first;
	            }
	        }
	    }
	    return pattern;
	}
}
