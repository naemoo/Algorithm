/*
 * https://www.acmicpc.net/problem/2805
 * 나무자르기
*/
package BinarySearch;

import java.io.*;
import java.util.*;

public class Problem04 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	long goal = Integer.parseInt(br.readLine().split(" ")[1]);
	long[] trees = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();

	System.out.println(getMaxLen(trees, goal));

    }

    private static long getMaxLen(long[] trees, long goal) {
	long min = 0;
	long max = Arrays.stream(trees).max().getAsLong();

	while (min < max) {
	    long mid = (min + max + 1) / 2;
	    if (isProperLen(trees, goal, mid)) {
		min = mid;
	    } else {
		max = mid - 1;
	    }
	}

	return max;
    }

    private static boolean isProperLen(long[] trees, long goal, long mid) {
	long sum = 0;
	for (long tree : trees) {
	    sum += (tree - mid) > 0 ? tree - mid : 0;
	}
	return sum >= goal ? true : false;
    }

}
