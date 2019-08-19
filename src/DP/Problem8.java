package DP;
/*
 * 백준(11054번) : 가장 긴 바이토닉 부분 수열(DP + LIS)
 * https://www.acmicpc.net/problem/11054
*/
import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n];
		int[][] dp = new int[2][n];
		dp[0][0] = 1;
		dp[1][n-1] = 1;
		int max = 1;
		for(int i = 0 ; i <n;i++)
			arr[i] = s.nextInt();
		
		for(int i = 1 ; i <n;i++) {
			dp[0][i] = 1;
			for(int j = 0;j<i;j++) {
				if(arr[i] > arr[j]&&dp[0][j]+1 > dp[0][i]) {
					dp[0][i] = dp[0][j]+1;
				}
			}
		}
		for(int i = n-2;i>=0;i--) {
			dp[1][i] = 1;
			for(int j = n-1 ;j>i;j--) {
				if(arr[i] > arr[j]&& dp[1][j] +1>dp[1][i]) {
					dp[1][i] = dp[1][j] +1;
				}
			}
		}
		for(int i = 0 ; i <n;i++)
			max = Math.max(max, dp[0][i]+dp[1][i]);
		System.out.println(max-1);
	}
}
