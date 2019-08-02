package DP;
/*
 * 백준 : 10844번(쉬운 계단 수)
*/
import java.util.Arrays;
import java.util.Scanner;

public class Problem9 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[][] dp = new int[n+1][10];
		Arrays.fill(dp[1],1);
		dp[1][0] = 0;
		for(int i= 2;i<=n;i++) 
			for(int j = 0; j <10;j++) 
				if(j==0) 
					dp[i][j] = dp[i-1][j+1];
				else if(j == 9) 
					dp[i][j] = dp[i-1][j-1];
				else
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
		long sum = 0;
		for(int i =0;i<10;i++) {
			sum += dp[n][i];
		}
		System.out.println(sum%1000000000);
	}
}
