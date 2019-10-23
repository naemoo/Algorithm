package DP;

/*
 * FloydWarshall 알고리즘
 * 가중치가 있는 그래프 일 때, 최단 경로 구하는 알고리즘
*/

public class Problem19 {
	static int INF = 1000000;// 갈 수 없는 곳은 무한대
	public static void floyd(int n, int[][] D) {
		int i,j,k;
		// k = 거쳐 가는 노드, i = 출발 노드, j = 도착 노드
		for(k =0 ;k<n;k++) 
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					D[i][j] = Math.min(D[i][j],D[i][k]+D[k][j]);
	}
	
	public static void main(String[] args) {
		int[][] W = new int[][] {{0,5,INF,8},{7,0,9,INF},{2,INF,0,4},{INF,INF,3,0}};
		floyd(W.length, W);
		for(int i = 0 ; i <W.length;i++) {
			for(int j=0;j<W.length;j++) {
				System.out.print(W[i][j] +" ");
			}
			System.out.println();
		}
	}

}
