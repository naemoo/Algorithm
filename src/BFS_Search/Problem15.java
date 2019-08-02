package BFS_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem15 {
	static Queue<Integer> q = new LinkedList<>();
	static int n;
	static int e;//°£¼±
	static int cnt = 0;
	static int[][] graph;
	static int[] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		e = s.nextInt();
		graph = new int[n][n];
		visit = new int[n];
		for(int i = 0;i<e;i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			graph[x-1][y-1] = 1;
			graph[y-1][x-1] = 1;
		}
		BFS(0);
		System.out.println(cnt);
	}
	public static void BFS(int idx) {
		q.add(idx);
		visit[idx] = 1;
		while(!q.isEmpty()) {
			idx = q.poll();
			for(int i = 0 ; i < n;i++) {
				 if(graph[idx][i] == 1 && visit[i] == 0) {
					 cnt++;
					 q.add(i);
					 visit[i] = 1;
				 }
			}
		}
	}
}
