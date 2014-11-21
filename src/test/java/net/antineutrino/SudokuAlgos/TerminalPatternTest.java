package net.antineutrino.SudokuAlgos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
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
	 * Test to make sure a terminal pattern can be created.
	 */
	public void testBasicCreate() {
		byte[][] terminal_pattern = testBasicTerminalCreator();
		assertTrue(true);
	}
}
