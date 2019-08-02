package BFS_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class problem5{
	static Queue<Point> q = new LinkedList<Point>();
	static int n,m;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int[][] map;
	static int[][] visit;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str;
		n = s.nextInt();
		m = s.nextInt();
		map = new int[n][m];
		visit = new int[n][m];
		for(int i= 0 ; i < n;i++) {
			str = s.next();
			for(int j = 0 ; j < m;j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		BFS(0, 0);//시작
		System.out.println(map[n-1][m-1]);
	}
	
	public static void BFS(int x,int y) {
		int nx,ny;
		q.add(new Point(x,y));
		while(!q.isEmpty()) {
			Point now = q.poll();
			x = now.x;
			y = now.y;
			for(int i = 0 ; i < 4 ;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<= nx && nx < n&&0<=ny && ny<m) {
					if(map[nx][ny] == 1 && visit[nx][ny] == 0) {
						visit[nx][ny] = 1;//방문
						map[nx][ny] = map[x][y] + 1;
						q.add(new Point(nx,ny));
					}
				}
			}
		}
	}
}



