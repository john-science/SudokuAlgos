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
}
