package DP;

import java.io.*;
import java.util.*;

/* https://www.acmicpc.net/problem/11403
 * 백준 : 경로찾기(11403번) 
 * DFS,BFS 탐색이 아닌 DP를 사용한 FloydWarshall 사용
 * 기존 FloyWarshall은 자기 자신을 0으로 설정했지만 이 문제에서는 자기 자신을 갈 수 있는지 파악하여야 한다.
*/
public class Problem21{
	static int Inf = 1000000;
	public static void makeAnswer(int[][] arr) {
		for(int i = 0 ; i < arr.length;i++)
			for(int j = 0 ; j <arr.length;j++)
				if(arr[i][j] <Inf)
					arr[i][j] = 1;
				else
					arr[i][j] = 0;
	}
	public static void floyd(int n,int[][] graph) {
		for(int k = 0 ; k<n;k++)
			for(int i=0;i<n;i++)
				for(int j = 0 ;j<n;j++) {
					if(graph[i][j] == 0) {//자기자신인 경우를 0-> 사이클을 통하여 갈 수 있는 수로 바꾼다. 
						graph[i][j] = graph[i][k]+graph[k][j];
					}
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int v = Integer.parseInt(br.readLine());
		int[][] graph = new int[v][v];
		
		for(int i = 0 ;i <v;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ;j<v;j++) {
				int edge = Integer.parseInt(st.nextToken());
				if(i!=j && edge ==0)
					graph[i][j] = Inf;
				else if(edge!=0)
					graph[i][j] = 1;
			}
		}
		floyd(v, graph);
		makeAnswer(graph);
		for(int i = 0 ; i < v;i++) {
			for(int j = 0 ; j<v;j++)
				System.out.print(graph[i][j] +" ");
			System.out.println();
		}
	}
}
