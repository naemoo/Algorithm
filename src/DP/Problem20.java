package DP;

import java.io.*;
import java.util.*;
/*
 * 백준 바이러스(https://www.acmicpc.net/problem/2606)
 * 기본의 DFS,BFS방식말고 DP로 푸는 방식 
 * 이때, FloydWshall 알고리즘을 사용한다. -> 가중치 있는 그래프에서 최단경로를 사용할 때 사용한다.
 * floyd 알고리즘 결과 -> Inf = 연결 x, 0 = 자기 자신, 숫자 = 거쳐간 정점
*/
public class Problem20{
	static int[][] graph;
	static int Inf = 1000000;//연결 안되어있다면 무한대
	
	public static void floyd(int n) {
		for(int k = 0 ; k<n;k++)
			for(int i =0 ;i<n;i++)
				for(int j = 0;j<n;j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int v = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		
		graph = new int[v][v];
		//graph 값 설정 
		for(int i = 0;i<v;i++) {
			Arrays.fill(graph[i], Inf);
			graph[i][i] = 0;
		}
		
		for(int i = 0 ; i < e;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken())-1;
			int v2 = Integer.parseInt(st.nextToken())-1;
			graph[v1][v2] = 1;
			graph[v2][v1] = 1;//역방향 설정
		}
		floyd(v);
		int num = 0;
		for(int i= 0; i < v;i++) {
			if(0<graph[0][1] && graph[0][i] < Inf)
				num++;//1에 연결된 네트워크 찾기
		}
		System.out.println(num-1);
	}
}
