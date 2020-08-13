package SamsungSWTest;

import java.io.*;
import java.util.*;

public class Problem08 {
    static int[][] scores;
    static int[][][] tetrominoes = new int[][][] { { { 1, 1, 1, 1 } }, { { 1, 1 }, { 1, 1 } },
	    { { 1 }, { 1 }, { 1, 1 } }, { { 1 }, { 1, 1 }, { 0, 1 } }, { { 1, 1, 1 }, { 0, 1, 0 } } };

    public static void main(String[] args) throws IOException {
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	int n = Integer.parseInt(br.readLine().split(" ")[0]);
//	scores = new int[n][];
//	for (int i = 0; i < n; i++) {
//	    scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//	}

	printArray();

    }

    private static void printArray() {
	for (int[][] tetromino : tetrominoes) {
	    for (int i = 0; i < tetromino.length; i++) {
		for (int j = 0; j < tetromino[i].length; j++) {
		    System.out.print(tetromino[i][j] + " ");
		}
		System.out.println();
	    }
	    System.out.println();
	}
    }
}
