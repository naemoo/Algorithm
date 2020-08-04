/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60059
 * 자물쇠와 열쇠
*/
package Kakao;

import java.util.*;

public class Problem30 {

    public static boolean solution(int[][] key, int[][] lock) {
	int[][][] keys = getRotatedKey(key);
	int n = lock.length;
	int m = key.length;

	lock = zeroPadding(m, lock);
	for (int[][] newKey : keys) {
	    for (int i = 0; i < n + m - 1; i++) {
		for (int j = 0; j < m + n - 1; j++) {
		    if (isMatchKey(newKey, lock, m, n, i, j))
			return true;
		}
	    }
	}
	return false;
    }

    private static boolean isMatchKey(int[][] newKey, int[][] lock, int m, int n, int row, int col) {
	lock = Arrays.stream(lock).map(int[]::clone).toArray(int[][]::new);

	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < m; j++) {
		lock[i + row][j + col] += newKey[i][j];
	    }
	}
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (lock[m - 1 + i][m - 1 + j] != 1)
		    return false;
	    }
	}
	return true;
    }

    private static int[][] zeroPadding(int m, int[][] lock) {
	int n = lock.length;
	int[][] arr = new int[2 * m + n - 2][2 * m + n - 2];
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		arr[m - 1 + i][m - 1 + j] = lock[i][j];
	    }
	}
	return arr;
    }

    private static int[][][] getRotatedKey(int[][] key) {
	int[][][] keys = new int[4][][];
	keys[0] = rotateKey(key);
	keys[1] = rotateKey(keys[0]);
	keys[2] = rotateKey(keys[1]);
	keys[3] = rotateKey(keys[2]);
	return keys;
    }

    private static int[][] rotateKey(int[][] key) {
	int n = key.length;
	int[][] arr = new int[n][n];
	for (int i = 0; i < key.length; i++) {
	    for (int j = 0; j < key[i].length; j++) {
		arr[i][j] = key[n - 1 - j][i];
	    }
	}
	return arr;
    }

    public static void main(String[] args) {
	boolean ans = solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
		new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } });
	System.out.println(ans);
    }
}
