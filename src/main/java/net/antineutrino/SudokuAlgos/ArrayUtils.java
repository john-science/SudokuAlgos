package net.antineutrino.SudokuAlgos;

public class ArrayUtils{
	public static int findIntIndex(byte[] array, byte val){
		for (int i=0; i < array.length; i++){
			if (array[i] == val) {
				return i;
			}
		}
		
		return -1;
	}
}