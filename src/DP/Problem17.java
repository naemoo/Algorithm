package DP;

/*
 *백준 : 동전1(2293번)
 *https://www.acmicpc.net/problem/2293 
*/
import java.io.*;
import java.util.StringTokenizer;

public class Problem17 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n+1];
		int[] dp = new int[k+1];
		dp[0] = 1;
		for(int i = 1;i<=n;i++)
			coin[i] = Integer.parseInt(br.readLine());
		for(int i = 1;i<=n;i++)
			for(int j = 1;j<=k;j++)
				if(j>=coin[i])
					dp[j] += dp[j-coin[i]];
		System.out.println(dp[k]);
	}
}
