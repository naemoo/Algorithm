package DP;

/*
 *백준(1932번) : 정수 삼각형(DP)
 *https://www.acmicpc.net/problem/1932
*/
import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int max = 0;
		int n = s.nextInt();
		int[][] tree = new int[n+1][n+1];
		int[][] d = new int[n+1][n+1];
		for(int i = 1;i<=n;i++)
			for(int j = 1;j<=i;j++)
				tree[i][j] = s.nextInt();
		for(int i = 1;i<=n;i++)
			for(int j = 1; j<= i ;j++) {
				if(j==1)
					d[i][j] = d[i-1][j] + tree[i][j];
				else if(j == i)
					d[i][j] = d[i-1][j-1] + tree[i][j]; 
				else
					d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+tree[i][j];
			}
		for(int i = 1 ; i <=n;i++)
			max = Math.max(max, d[n][i]);
		System.out.println(max);
	}
}
