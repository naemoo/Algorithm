package DP;
/*
 *백준 : 2656번(전깃줄) - LIS(최장 증가 수열 응용) 
*/
import java.util.Scanner;

public class Problem4{
	static int[] arr = new int[501];
	static int[] dp= new int[501];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int idx =0;
		for(int i = 1 ; i <=n;i++) {
			idx = s.nextInt();
			arr[idx] = s.nextInt();
		}
		n -= getLIS();
		System.out.println(n);
	}
	public static int getLIS() {
		int max  = 0;
		for(int i = 1;i<=500;i++) {
			if(arr[i] == 0)
				continue;
			dp[i] = 1;
			for(int j = 1;j<i;j++) {
				if(arr[i] > 0 &&arr[i]>arr[j] && dp[j]+1 >dp[i]) { 
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
