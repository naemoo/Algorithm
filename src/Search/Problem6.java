package Search;

/*
 * 백준 : 경로찾기(BFS탐색)
 * https://www.acmicpc.net/problem/11403
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem6 {
	static Queue<Integer> q = new LinkedList<Integer>(); 
	static int n;
	static int[][] map;
	static int[][] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		map = new int[n][n];
		visit = new int[n][n];
		for(int i= 0;i<n;i++)
			for(int j= 0;j<n;j++)
				map[i][j] = s.nextInt();
		for(int i = 0 ; i <n;i++)
			BFS(i);
		for(int i = 0 ; i < n;i++) {
			for(int j = 0 ; j<n;j++)
				System.out.print(visit[i][j] + " ");
			System.out.println();
		}
	}
	public static void BFS(int num) {
		int idx;
		q.add(num);
		while(!q.isEmpty()) {
			idx = q.poll();
			for(int i = 0 ; i < n ;i++) {
				if(map[idx][i] == 1 && visit[num][i] == 0) {
					q.add(i);
					visit[num][i] = 1;
				}
			}
		}
	}
}
