package SamsungSWTest;

/*
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWJHmLraeEwDFAUH
 * 이상한 피라미드
*/

import java.util.*;
import java.io.*;

public class Problem03 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	for (int i = 0; i < n; i++) {
	    int[] ab = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	    int answer = findTreasure(ab[0], ab[1]);
	    System.out.println("#" + (i + 1) + " " + answer);
	}
    }

    private static int findTreasure(int a, int b) {
	if (a == b)
	    return 0;

	int start = Math.min(a, b);
	int end = Math.max(a, b);
	int startLevel = findLevel(start);
	int endLevel = findLevel(end);
	int h = endLevel - startLevel;
	int[] tri = getTriangle(startLevel, endLevel, start);

	if (tri[0] == tri[1])
	    return end - start;
	else {
	    return tri[0] <= end && end <= tri[1] ? h
		    : Math.min(Math.abs(end - tri[1]), Math.abs(tri[0] - end)) + h;
	}
    }

    private static int[] getTriangle(int startLevel, int endLevel, int cur) {
	int curLeft = getLeft(startLevel);
	int treLeft = getLeft(endLevel);
	int triLeft = treLeft + cur - curLeft;
	int triRight = triLeft + Math.abs(startLevel - endLevel);
	return new int[] { triLeft, triRight };
    }

    private static int findLevel(int num) {
	for (int i = 2; i <= 150; i++) {
	    int left = getLeft(i);
	    int right = getRight(i);
	    if (left <= num && num <= right) {
		return i;
	    }
	}
	return 1;
    }

    private static int getRight(int num) {
	return (((int) Math.pow(num, 2) + num) / 2);
    }

    private static int getLeft(int num) {
	return ((int) Math.pow(num, 2) - num + 2) / 2;
    }
}
