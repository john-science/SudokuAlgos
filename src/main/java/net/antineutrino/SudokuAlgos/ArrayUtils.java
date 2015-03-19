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
	
	public static byte[][] swapTwoRows(byte[][] pattern, int row1, int row2) {
		// TODO: Testing is key. Do I *have* to use Arrays.copy, or clone(), or something?
		byte[] temp_row = pattern[row1];
		pattern[row1] = pattern[row2];
		pattern[row2] = temp_row;
		
		return pattern;
	}

	public static int[][] swapTwoRows(int[][] pattern, int row1, int row2) {
		// TODO: Testing is key. Do I *have* to use Arrays.copy, or clone(), or something?
		int[] temp_row = pattern[row1];
		pattern[row1] = pattern[row2];
		pattern[row2] = temp_row;
		
		return pattern;
	}

	public static <E> E[][] swapTwoRows(E[][] pattern, int row1, int row2) {
		// TODO: Testing is key. Do I *have* to use Arrays.copy, or clone(), or something?
		E[] temp_row = pattern[row1];
		pattern[row1] = pattern[row2];
		pattern[row2] = temp_row;
		
		return pattern;
	}
	
	public static int[][] swapTwoBlocks(int[][] pattern, int block1, int block2) {
        // TODO: Testing is key. Do I *have* to use Arrays.copy, or clone(), or something?
        int[] temp_row1 = pattern[0 + 3 * block1];
        int[] temp_row2 = pattern[1 + 3 * block1];
        int[] temp_row3 = pattern[2 + 3 * block1];

        pattern[0 + 3 * block1] = pattern[0 + 3 * block2];
        pattern[1 + 3 * block1] = pattern[1 + 3 * block2];
        pattern[2 + 3 * block1] = pattern[2 + 3 * block2];

        pattern[0 + 3 * block2] = temp_row1;
        pattern[1 + 3 * block2] = temp_row2;
        pattern[2 + 3 * block2] = temp_row3;

        return pattern;
    }
   
    public static <E> E[][] swapTwoBlocks(E[][] pattern, int block1, int block2) {
        // TODO: Testing is key. Do I *have* to use Arrays.copy, or clone(), or something?
        E[] temp_row1 = pattern[0 + 3 * block1];
        E[] temp_row2 = pattern[1 + 3 * block1];
        E[] temp_row3 = pattern[2 + 3 * block1];

        pattern[0 + 3 * block1] = pattern[0 + 3 * block2];
        pattern[1 + 3 * block1] = pattern[1 + 3 * block2];
        pattern[2 + 3 * block1] = pattern[2 + 3 * block2];

        pattern[0 + 3 * block2] = temp_row1;
        pattern[1 + 3 * block2] = temp_row2;
        pattern[2 + 3 * block2] = temp_row3;

        return pattern;
    }
    
	public static int[][] rotateSquareClockwise(int[][] array) {
		// TODO: Testing is key.
	        int size = array.length;
	        int[][] newArray = new int[size][size];
	
	        for(int i=0; i<size; i++){
	            for(int j=size-1; j>=0; j--){
	                newArray[i][j] = array[j][i];
	            }
	        }
	
	        return newArray;
    }
   
    public static <E> E[][] rotateSquareClockwise(E[][] array) {
    	// TODO: Testing is key.
        int size = array.length;
        E[][] newArray = new E[size][size];

        for(int i=0; i<size; i++){
            for(int j=size-1; j>=0; j--){
                newArray[i][j] = array[j][i];
            }
        }

        return newArray;
    }
}
