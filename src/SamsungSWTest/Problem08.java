/*
 * https://www.acmicpc.net/problem/14500
 * 테트로미노
*/
package SamsungSWTest;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Problem08 {

    public static void main(String[] args) throws IOException {
	int[][][] tetrominoes = new int[][][] { { { 1, 1, 1, 1 } }, { { 1, 1 }, { 1, 1 } },
		{ { 1, 0 }, { 1, 0 }, { 1, 1 } }, { { 1, 0 }, { 1, 1 }, { 0, 1 } }, { { 1, 1, 1 }, { 0, 1, 0 } } };
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine().split(" ")[0]);
	int[][] scores = new int[n][];
	for (int i = 0; i < n; i++) {
	    scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	tetrominoes = getEveryShape(tetrominoes);
	tetrominoes = getEveryCase(tetrominoes);
	int score = getMaxScore(scores, tetrominoes);
	System.out.println(score);
    }

    private static int getMaxScore(int[][] scores, int[][][] tetrominoes) {
	int max = 0;
	for (int[][] tetromino : tetrominoes) {
	    for (int i = 0; i < scores.length + 1 - tetromino.length; i++) {
		for (int j = 0; j < scores[0].length + 1 - tetromino[0].length; j++) {
		    max = Math.max(max, sumScore(scores, tetromino, i, j));
		}
	    }
	}
	return max;
    }

    private static int sumScore(int[][] scores, int[][] tetromino, int row, int col) {
	int sum = 0;
	for (int i = 0; i < tetromino.length; i++) {
	    for (int j = 0; j < tetromino[i].length; j++) {
		if (tetromino[i][j] == 1)
		    sum += scores[i + row][j + col];
	    }
	}
	return sum;
    }

    private static int[][][] getEveryCase(int[][][] tetrominoes) {
	int[][][] newArr = new int[tetrominoes.length * 4][][];
	for (int i = 0; i < tetrominoes.length; i++) {
	    newArr[i * 4] = rotateArr(tetrominoes[i]);
	    for (int j = 1; j < 4; j++) {
		newArr[i * 4 + j] = rotateArr(newArr[i * 4 + (j - 1)]);
	    }
	}
	return newArr;
    }

    private static int[][][] getEveryShape(int[][][] tetrominoes) {
	int[][][] newArr = new int[tetrominoes.length + 2][][];
	int idx = 0;
	for (int k = 0; k < tetrominoes.length; k++) {
	    newArr[idx] = Arrays.stream(tetrominoes[k]).map(int[]::clone).toArray(int[][]::new);
	    if (k == 2 || k == 3) {
		newArr[++idx] = getReverse(tetrominoes[k]);
	    }
	    idx++;
	}
	return newArr;
    }

    private static int[][] getReverse(int[][] arr) {
	int[][] newArr = new int[arr.length][arr[0].length];
	for (int i = 0; i < newArr.length; i++) {
	    for (int j = 0; j < newArr[0].length; j++) {
		newArr[i][j] = arr[i][arr[0].length - 1 - j];
	    }
	}
	return newArr;
    }

    private static int[][] rotateArr(int[][] arr) {
	int[][] newArr = new int[arr[0].length][];
	for (int i = 0; i < newArr.length; i++) {
	    newArr[i] = getCols(arr, i);
	}
	return newArr;
    }

    private static int[] getCols(int[][] tetromino, int i) {
	return IntStream.range(0, tetromino.length).map(ele -> tetromino.length - ele - 1).map(idx -> tetromino[idx][i])
		.toArray();
    }
}
