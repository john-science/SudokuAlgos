package net.antineutrino.SudokuAlgos;

public class ArrayUtils{
	public static int findIntIndex(int[] array, int val){
		for (int i=0; i < array.length; i++){
			if (array[i] == val) {
				return i;
			}
		}
		
		return -1;
	}
}