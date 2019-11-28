package Greedy;

/*
 * Dijksta's Algorithm(다익스트라 알고리즘)
*/

import java.util.*;


class Edge implements Comparable<Edge>{
	int i;
	int j;
	int w;
	public Edge(int i, int j,int w) {
		this.i = i;
		this.j = j;
		this.w = w;
	}
	public int compareTo(Edge e) {
		return w - e.w;
	};
	@Override
	public String toString() {
		return "i : " + i + " j : "+j + " W : " +w;
	}
}

public class Problem3 {
	static int INF = 1000000;
	
	public static void dijkstra(int n,int W[][],List<Edge> F) {
		int i;
		int vnear = 1;
		Edge e;
		int[] touch = new int[n+1];
		int[] length = new int[n+1];
		
		for(i = 2; i <=n;i++) {
			touch[i] = 1;
			length[i] = W[1][i];
		}
		
		//기본 구조 최소 거리를 가지는 정점을 구한 뒤 거리를 갱신한다
		for(int t = 1;t<=n-1;t++) {
			int min = INF;
			for(i =2;i<=n;i++) {
				if(0<= length[i]&& length[i] <=min) {
					min = length[i];
					vnear = i;
				}//최소 거리를 가지는 정점을 구한다.
			}
			e = new Edge(touch[vnear],vnear,W[touch[vnear]][vnear]);
			F.add(e);
			for(i= 2;i<=n;i++) 
				if(length[vnear]+W[vnear][i] < length[i]) {
					length[i] = length[vnear] + W[vnear][i];
					touch[i] = vnear;

				}//각 거리를 갱신한다.
			length[vnear] = -1;
		}
	}
	
	public static void main(String[] args) {
		int n = 5;
		System.out.println("교재의 입력 데이터(Figure 4.8)");
		int[][] W = new int[n+1][n+1];
		for(int i = 0;i<W.length;i++) {
			Arrays.fill(W[i], INF);
		}
		W[1][2] = 7;
		W[1][3] = 4;
		W[1][4] = 6;
		W[1][5] = 1;
		W[3][2] = 2;
		W[3][4] = 5;
		W[4][2] = 3;
		W[5][4] = 1;
		List<Edge> F = new LinkedList<Edge>();
		dijkstra(n, W, F);
		for(Iterator<Edge> i = F.iterator();i.hasNext();) {
			System.out.println(i.next());
		}
		System.out.println();
		
		System.out.println("자작 데이터");
		n = 5;
		for(int i = 0;i<W.length;i++) {
			Arrays.fill(W[i], INF);
		}
		W[1][3] = 6;
		W[1][4] = 3;
		W[2][1] = 3;
		W[3][4] = 2;
		W[4][2] = 1;
		W[4][3] = 1;
		W[5][2] = 4;
		W[5][4] = 2;
		F = new LinkedList<Edge>();
		dijkstra(n, W, F);
		for(Iterator<Edge> i = F.iterator();i.hasNext();) {
			System.out.println(i.next());
		}

	}
}
