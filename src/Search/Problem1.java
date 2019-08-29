package Search;

/*
 * 백준 : 연결 요소의 개수(11724번)
 * https://www.acmicpc.net/problem/11724
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Problem1{
	static int[] visit;
	static int[][] graph;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) {
		int cnt = 0 ;
		Scanner s = new Scanner(System.in);
		int v = s.nextInt();
		int n = s.nextInt();
		graph = new int[v][v];
		visit = new int[v];
		for(int i=0;i<n;i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			graph[x-1][y-1] = 1;
			graph[y-1][x-1] = 1;
		}
		
		for(int i = 0 ; i < v;i++)
			if(visit[i] == 0) {
				BFS(i);
				cnt++;
			}
		System.out.println(cnt);
	}
	public static void BFS(int n) {
		int idx;
		q.add(n);
		visit[n] =1;
		while(!q.isEmpty()) {
			idx = q.poll();
			for(int i= 0 ; i < graph.length;i++) {
				if(visit[i] == 0 &&graph[idx][i] == 1) {
					q.add(i);
					visit[i] = 1;
				}
			}
		}
	}
}

