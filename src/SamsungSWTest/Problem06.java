/*
 * https://www.acmicpc.net/problem/14501
 * 퇴사
*/
package SamsungSWTest;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Problem06 {
    static int[][] map;
    static int[] visit;
    static int n;
    static int max = 0;
    static int profit = 0;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	n = Integer.parseInt(br.readLine());
	map = new int[n][2];
	visit = new int[n];
	for (int i = 0; i < n; i++) {
	    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	DFS(0);
	System.out.println(max);
    }

    private static void DFS(int d) {
	if (d == n) {
	    max = Math.max(max, profit);
	    return;
	}

	if (visit[d] != 0) {
	    DFS(d + 1);
	}

	else {
	    for (int i = 0; i < 2; i++) {
		if (i == 0 && (d + map[d][0] <= n)) {
		    IntStream.range(d, map[d][0] + d).forEach(idx -> visit[idx] = 1);
		    profit += map[d][1];
		    DFS(d + 1);
		    IntStream.range(d, map[d][0] + d).forEach(idx -> visit[idx] = 0);
		    profit -= map[d][1];

		} else {
		    DFS(d + 1);
		}
	    }
	}
    }
}
