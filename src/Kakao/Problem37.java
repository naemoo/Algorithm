package Kakao;

import java.util.*;

public class Problem37 {

    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
	HashMap<Integer, HashSet<Integer>> edgeInfo = new HashMap<>();
	int[][] dp = new int[n + 1][n + 1];

	for (int[] edge : edge_list) {
	    edgeInfo.computeIfAbsent(edge[0], key -> new HashSet<>()).add(edge[1]);
	    edgeInfo.computeIfAbsent(edge[1], key -> new HashSet<>()).add(edge[0]);
	}

	for (int i = 0; i < dp.length; i++)
	    Arrays.fill(dp[i], Integer.MAX_VALUE);
	dp[0][gps_log[0]] = 0;

	for (int i = 1; i < k - 1; i++) {
	    for (int j = 1; j <= n; j++) {
		dp[i][j] = Integer.min(dp[i - 1][j], dp[i][j]);

		for (int adj : edgeInfo.get(j)) {
		    dp[i][j] = Integer.min(dp[i - 1][adj], dp[i][j]);
		}
		dp[i][j] += (gps_log[i] == j) ? 0 : 1;
	    }
	}

	if (dp[k - 1][gps_log[k - 1]] >= Integer.MAX_VALUE)
	    return -1;
	System.out.println(dp[k - 1][gps_log[k - 1]]);
	return dp[k - 1][gps_log[k - 1]];
    }

    public static void main(String[] args) {
	solution(7, 10, new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 }, { 2, 4 }, { 3, 4 }, { 3, 5 }, { 4, 6 }, { 5, 6 },
		{ 5, 7 }, { 6, 7 } }, 6, new int[] { 1, 2, 3, 5, 6, 7 });
	solution(7, 10, new int[][] { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 4 }, { 4, 6 }, { 5, 6 }, { 5, 7 }, { 6, 7 } },
		6, new int[] { 1, 1, 1, 5, 6, 7 });
    }
}
