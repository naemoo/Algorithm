package DP;

/*
 * 백준 : 9461번(파도반 수열)
 * https://www.acmicpc.net/problem/9461
*/
import java.io.*;
import java.util.Arrays;

public class Problem11 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		Arrays.fill(dp, 1,4,1);
		Arrays.fill(dp, 4,6,2);
		for(int i = 6;i<=100;i++) {
			dp[i] = dp[i-1] + dp[i-5];
		}
		for(int i = 0 ; i <t;i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
}
