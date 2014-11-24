package net.antineutrino.SudokuAlgos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for various terminal pattern functions.
 */
public class TerminalPatternTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public TerminalPatternTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TerminalPatternTest.class);
	}

	public static byte[][] testBasicTerminalCreator() {
		try {
			return TerminalPattern.create(new DFSSolver());
		} catch (NoSolutionExistsException e) {
			System.out.println(e);
			return new byte[9][9];
		}
	}

	/**
	 * Test of terminal pattern creator (and validator)
	 */
	public void testBasicCreate() {
		byte[][] terminal_pattern = new byte[9][9];
		for (int i = 0; i < 10; i++){
			terminal_pattern = testBasicTerminalCreator();
			assertTrue(TerminalPattern.validate(terminal_pattern));
		}
	}

	/**
	 * Test of terminal pattern validator (and creator)
	 */
	public void testBasicValidate() {
		byte[][] terminal_pattern = testBasicTerminalCreator();
		assertTrue(TerminalPattern.validate(terminal_pattern));
		terminal_pattern[0][0] = terminal_pattern[0][1];
		assertFalse(TerminalPattern.validate(terminal_pattern));
	}
	
	/**
	 * Test of a terminal pattern with errors in a row.
	 */
	public void testBadPatternRow() {
		// fifth row has two 4s
		byte[][] terminal_pattern = { { 2, 4, 1, 6, 5, 7, 3, 8, 9 },
			{ 6, 3, 5, 8, 9, 1, 4, 7, 2 }, { 7, 8, 9, 2, 4, 3, 5, 1, 6 },
			{ 5, 7, 3, 1, 8, 9, 2, 6, 4 }, { 4, 1, 2, 5, 7, 6, 9, 4, 8 },
			{ 8, 9, 6, 3, 2, 4, 1, 5, 7 }, { 3, 5, 4, 9, 6, 8, 7, 2, 1 },
			{ 9, 2, 8, 7, 1, 5, 6, 4, 3 }, { 1, 6, 7, 4, 3, 2, 8, 9, 5 } };
		assertFalse(TerminalPattern.validate(terminal_pattern));
	}
	
	/**
	 * Test of a terminal pattern with errors in a column.
	 */
	public void testBadPatternCol() {
		// first column has two 4s
		byte[][] terminal_pattern = { { 2, 4, 1, 6, 5, 7, 3, 8, 9 },
			{ 6, 3, 5, 8, 9, 1, 4, 7, 2 }, { 7, 8, 9, 2, 4, 3, 5, 1, 6 },
			{ 5, 7, 3, 1, 8, 9, 2, 6, 4 }, { 4, 1, 2, 5, 7, 6, 9, 3, 8 },
			{ 8, 9, 6, 3, 2, 4, 1, 5, 7 }, { 3, 5, 4, 9, 6, 8, 7, 2, 1 },
			{ 9, 2, 8, 7, 1, 5, 6, 4, 3 }, { 4, 6, 7, 4, 3, 2, 8, 9, 5 } };
		assertFalse(TerminalPattern.validate(terminal_pattern));
	}
	
	/**
	 * Test of a terminal pattern with errors in a block.
	 */
	public void testBadPatternBlock() {
		// first block has two 2s
		byte[][] terminal_pattern = { { 2, 4, 1, 6, 5, 7, 3, 8, 9 },
			{ 6, 3, 5, 8, 9, 1, 4, 7, 2 }, { 7, 8, 2, 2, 4, 3, 5, 1, 6 },
			{ 5, 7, 3, 1, 8, 9, 2, 6, 4 }, { 4, 1, 2, 5, 7, 6, 9, 3, 8 },
			{ 8, 9, 6, 3, 2, 4, 1, 5, 7 }, { 3, 5, 4, 9, 6, 8, 7, 2, 1 },
			{ 9, 2, 8, 7, 1, 5, 6, 4, 3 }, { 4, 6, 7, 4, 3, 2, 8, 9, 5 } };
		assertFalse(TerminalPattern.validate(terminal_pattern));
	}
	
	/**
	 * Test of a terminal pattern with invalid numbers
	 */
	public void testBadPatternValues() {
		// pattern includes to invalid values: -9 and 19
		byte[][] terminal_pattern = { { 2, 4, 1, 6, 5, 7, 3, 8, -9 },
			{ 6, 3, 5, 8, 9, 1, 4, 7, 2 }, { 7, 8, 2, 2, 4, 3, 5, 1, 6 },
			{ 5, 7, 3, 1, 8, 9, 2, 6, 4 }, { 4, 1, 2, 5, 7, 6, 9, 3, 8 },
			{ 8, 9, 6, 3, 2, 4, 1, 5, 7 }, { 3, 5, 4, 9, 6, 8, 7, 2, 1 },
			{ 9, 2, 8, 7, 1, 5, 6, 4, 3 }, { 4, 6, 7, 4, 3, 2, 8, 9, 15 } };
		assertFalse(TerminalPattern.validate(terminal_pattern));
	}
}
