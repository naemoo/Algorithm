package BFS_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem8 {
	static Queue<Integer> q = new LinkedList<>();
	static int[][] graph;
	static int[] visit;
	static int v;//정점
	static int e;//간선
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		v = s.nextInt();
		e = s.nextInt();
		int start = s.nextInt() -1;
		graph = new int[v][v];
		for(int i = 0;i<e;i++) {
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
		visit = new int[v];
		DFS(start);
		System.out.println();
		visit = new int[v];
		BFS(start);
	}
	public static void DFS(int n) {
		System.out.print(n+1 + " ");
		visit[n] = 1;
		for(int i = 0 ; i < v;i++) {
			if(graph[n][i] == 1 && visit[i] == 0) {
				DFS(i);
			}
		}
	}
	public static void BFS(int n) {
		int idx;
		visit[n] = 1;
		q.add(n);
		System.out.print(n+1 + " ");
		while(!q.isEmpty()) {
			idx = q.poll();
			for(int i = 0 ; i < v;i++) {
				if(graph[idx][i] ==1 && visit[i] == 0) {
					q.add(i);
					visit[i] = 1;
					System.out.print(i + 1 +" ");
				}
			}
		}
	}
}
