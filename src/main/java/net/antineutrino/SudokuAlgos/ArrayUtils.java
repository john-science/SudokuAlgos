package net.antineutrino.SudokuAlgos;

import java.util.Arrays;

public class ArrayUtils {

	/**
	 * Find the index of an element in a 1D byte array.
	 */
	public static int findIndex(byte[] array, byte val) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == val) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Find the index of an element in a 1D integer array.
	 */
	public static int findIndex(int[] array, int val) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == val) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Generic method to find an index of an element in a 1D array.
	 */
	public static <E> int findIndex(E[] a, E val) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == val) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param a
	 */
	public static <E> void printArray(E[] a) {
		System.out.println(Arrays.deepToString(a).replace("],", "],\n"));
	}
}