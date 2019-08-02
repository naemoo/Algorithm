package DP;
/*
 *백준 1149문제 : RGB거리 
*/
import java.util.Scanner;

public class Problem7 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.nextLine();
		int[][] cost = new int[1001][3];
		int[][] dp = new int[1001][3];
		for(int i = 1 ; i <=n;i++) {
			String[] str = s.nextLine().split(" ");
			for(int j = 0 ; j <3;j++)
				cost[i][j] = Integer.parseInt(str[j]);
		}
		for(int i = 1;i<=n;i++) {
			for(int j = 0;j<3;j++) {
				if(j == 0)
					dp[i][j] = cost[i][j] + Math.min(dp[i-1][1], dp[i-1][2]);
				else if(j == 1)
					dp[i][j] = cost[i][j] + Math.min(dp[i-1][0], dp[i-1][2]);
				else
					dp[i][j] = cost[i][j] + Math.min(dp[i-1][0], dp[i-1][1]);
			}
		}
		System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
	}
}
