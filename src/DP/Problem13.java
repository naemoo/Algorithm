package DP;
/*
 * 백준 : 1520(내리막 길)
 * https://www.acmicpc.net/problem/1520
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem13 {
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int n;
	static int m;
	static int[][] map;
	static long[][] dp;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new long[n][m];
		
		for(int i = 0 ; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i],-1);
			for(int j = 0 ; j<m;j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(DFS(0, 0));
		/*for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j<m;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}*/
	}
	public static long DFS(int x,int y) {
		if(x == n-1&& y == m-1) {
			return 1;
		}
		if(dp[x][y] == -1) {
			dp[x][y] = 0;
			
			for(int i = 0 ; i< 4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0<=nx&&nx<n&&0<=ny&&ny<m&&map[nx][ny] < map[x][y]) {
					dp[x][y] += DFS(nx,ny);
				}
			}
		}
		return dp[x][y];
	}
}
