package BackTracking;
/*
 * 백준 : N-Queen(9663번) 
 * https://www.acmicpc.net/problem/9663 
*/
import java.util.Scanner;

public class Problem2 {
	static int n;
	static int cnt = 0;
	static int[] col;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		col = new int[n+1];
		DFS(0);
		System.out.println(cnt);
	}
	public static boolean isProper(int r,int c) {
		if(col[c] != 0)
			return false;
		for(int i =1 ; i <= n;i++) {
			if(col[i] != 0 && Math.abs(col[i] - r) == Math.abs(i-c))
				return false;
		}
		return true;
	}
	public static void DFS(int d) {
		if(d == n) {
			cnt++;
			return;
		}
		for(int i = 1; i <= n;i++) {
			if(isProper(d+1, i)) {
				col[i] = d+1;
				DFS(d+1);
				col[i] = 0;
			}
		}
	}
}
