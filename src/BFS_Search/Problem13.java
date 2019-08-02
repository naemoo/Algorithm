package BFS_Search;

import java.util.Scanner;

public class Problem13 {
	static int r;
	static int c;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int max = 0;
	static int[] check = new int[26];
	static char[][] map;
	static int[][] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		r= s.nextInt();
		c= s.nextInt();
		map = new char[r][c];
		visit = new int[r][c];
		for(int i = 0 ; i < r;i++) 
			map[i] = s.next().toCharArray();
		check[map[0][0] - 'A'] = 1;
		DFS(0,0,0);
		System.out.println(max);
	}
	
	static void DFS(int x, int y,int depth) {
		int nx,ny;
		for(int i = 0;i < 4;i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(0<=nx&&nx<r&&0<=ny&& ny<c) {
				int idx = map[nx][ny] - 'A';
				if(visit[nx][ny] == 0 && check[idx] == 0){
					visit[nx][ny] = 1;
					check[idx] = 1;
					DFS(nx,ny,depth+1);
					visit[nx][ny] = 0;
					check[idx] = 0;
				}
			}
		}
		max = Math.max(max, depth+1); 
	}
}
