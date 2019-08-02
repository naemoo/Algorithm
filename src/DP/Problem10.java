package DP;

/*
 * 백준 : 2156번(포도주 시식)
 * 비슷한 문제 : 백준(2579번)계단오르기
*/import java.util.Scanner;

public class Problem10 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[10000];
		int[] dp = new int[10000];
		for(int i = 0 ; i < n;i++)
			arr[i] = s.nextInt();
		int max = 0;
		dp[0] = arr[0];
		dp[1] = arr[1] + arr[0];
		dp[2] = Math.max(arr[2]+arr[1],arr[2]+arr[0]);
		dp[2] = Math.max(dp[2], dp[1]);
		max = Math.max(dp[0], Math.max(dp[1], dp[2]));
		for(int i = 3;i<n;i++) {
			dp[i] = Math.max(arr[i]+arr[i-1]+dp[i-3],arr[i]+dp[i-2]);
			dp[i] = Math.max(dp[i],dp[i-1]);
			max = Math.max(dp[i],max);
		}
		System.out.println(max);
	}
}
