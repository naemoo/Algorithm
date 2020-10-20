package Utils;

import java.util.*;
import java.util.stream.*;

public class Array {
    public static void main(String[] args) {
	int[][] arr = { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
	System.out.println(Arrays.deepToString(zeroPadding(2, arr)));
    }

    // 시계방향으로 회전(90도)
    public static int[][] rotateArray(int[][] arr) {
	int[][] newArr = new int[arr[0].length][];
	for (int i = 0; i < newArr.length; i++) {
	    newArr[i] = getColReverse(arr, i);
	}
	return newArr;
    }

    // 반시계방향으로 회전(90도)
    public static int[][] reverseRotateArray(int[][] arr) {
	int[][] newArr = new int[arr[0].length][];
	for (int i = 0; i < newArr.length; i++) {
	    newArr[i] = getColReverse(arr, newArr.length - 1 - i);
	}
	return newArr;
    }

    /*
     * @param i : col 인덱스
     */
    public static int[] getColReverse(int[][] arr, int i) {
	return IntStream.range(0, arr.length).map(ele -> arr.length - ele - 1).map(idx -> arr[idx][i]).toArray();
    }

    // 행렬 좌우 반전
    public static int[][] getReverse(int[][] arr) {
	int[][] newArr = new int[arr.length][arr[0].length];
	for (int i = 0; i < newArr.length; i++) {
	    for (int j = 0; j < newArr[i].length; j++) {
		newArr[i][j] = arr[i][arr[i].length - 1 - j];
	    }
	}
	return newArr;
    }
    
    private static int[][] zeroPadding(int m, int[][] arr) {
	int n = arr.length;
	int[][] newArr = new int[2 * m + n - 2][2 * m + n - 2];
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		newArr[m - 1 + i][m - 1 + j] = arr[i][j];
	    }
	}
	return newArr;
    }

}
