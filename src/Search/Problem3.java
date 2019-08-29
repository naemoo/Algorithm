package Search;

/*
 * 백준 : 적록색약(BFS탐색)
 * https://www.acmicpc.net/problem/10026
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem3 {
	static Queue<Point> q = new LinkedList<>();
	static int n ;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static char[][] map;
	static char[][] map1;
	static int[][] visit;
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		n = s.nextInt();
		map = new char[n][n];
		map1 = new char[n][n];
		visit = new int[n][n];
		String str;
		for(int i = 0 ; i < n ; i++) {
			str = s.next();
			for(int j = 0 ; j < n;j++) {
				map[i][j] = str.charAt(j);
				map1[i][j] = str.charAt(j);
				if(map1[i][j] == 'G')
					map1[i][j] = 'R';
			}
		}
		
		int bld_cnt = 0;//색맹인사람 
		int nol_cnt = 0;//일반 사람
		
		for(int i = 0 ; i <n;i++)
			for(int j = 0 ; j<n;j++) 
				if(visit[i][j] == 0) {
					BFS(i,j);
					nol_cnt++;
				}
		map = map1;
		visit = new int[n][n];
		for(int i = 0 ; i <n;i++)
			for(int j = 0 ; j<n;j++) 
				if(visit[i][j] == 0) {
					BFS(i, j);
					bld_cnt++;
				}
		System.out.print(nol_cnt+ " ");
		System.out.print(bld_cnt);
	}
	public static void BFS(int x,int y) {
		char color;
		q.add(new Point(x,y));
		visit[x][y] = 1;
		int nx,ny;
		
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.poll().y;
			color = map[x][y];
			for(int i =0;i<4;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx && nx < n && 0<=ny && ny<n) { 
					if(visit[nx][ny] == 0 && color == map[nx][ny]) {
						q.add(new Point(nx,ny));
						visit[nx][ny] = 1;
					}
				}
			}
		}
	}
}
