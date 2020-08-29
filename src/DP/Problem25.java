/*
 * https://programmers.co.kr/learn/courses/30/lessons/12907#
 * 거스름돈
*/
package DP;

import java.util.*;

public class Problem25 {
    public static int solution(int n, int[] money) {
	int[] dp = new int[n + 1];
	dp[0] = 1;

	for (int j = 0; j < money.length; j++) {
	    for (int i = 1; i <= n; i++) {
		if (i - money[j] >= 0) {
		    dp[i] += dp[i - money[j]];
		}
	    }
	}
	return dp[n];
    }

    public static void main(String[] args) {
	solution(5, new int[] { 1, 2, 5 });
    }
}
