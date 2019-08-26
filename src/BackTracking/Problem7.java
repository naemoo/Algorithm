package BackTracking;
/*
 * 백준 : 스타트와 링크(14889번)
 * https://www.acmicpc.net/problem/14889
*/
import java.io.*;
import java.util.StringTokenizer;

public class Problem7 {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int[][] arr;
	static int[] visit;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		visit = new int[n];
		StringTokenizer st;
		for(int i = 0 ; i < n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit[0] = 1; 
		dfs(1,1);
		System.out.println(min);
	}
	static void dfs(int start,int d) {
		if(d == n/2) {
			int sum1 = 0;
			int sum0 = 0;
			for(int i = 0 ; i < n;i++) {
				for(int j = 0 ; j < n;j++) {
					if(visit[i] == 1 && visit[j] ==1) 
						sum1 += arr[i][j];
					else if(visit[i] == 0 && visit[j] == 0)
						sum0 += arr[i][j];
				}
			}
			min = Math.min(min, Math.abs(sum1-sum0));
			return;
		}
		for(int i = start;i<n;i++) {
			visit[i] = 1;
			dfs(i+1,d+1);
			visit[i] = 0;
		}
	}
}
