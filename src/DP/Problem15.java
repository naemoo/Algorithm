package DP;
/*
 * 프로그래머스 :타일 장식물(DP)
*/

import java.io.*;

class Solution {
    public long solution(int N) {
    	long[] dp = new long[82];
    	long sum = 0;
    	dp[1] = 1;
    	dp[2] = 1;
    	for(int i = 3;i<=81;i++) {
    		dp[i] = dp[i-2] + dp[i-1];
    	}
    	sum = dp[N] *2 +dp[N+1]*2;
    	return sum;
    }
}
public class Problem15 {
	public static void main(String[] args) throws IOException{
		Solution s = new Solution();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(s.solution(N));
	}
}
