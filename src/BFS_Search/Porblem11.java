package BFS_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x ;
		this.y = y ;
	}
}

public class Porblem11 {
	static Queue<Point> q = new LinkedList<>();
	static int test;
	static int n;
	static int m;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] map;
	static int[][] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		test = s.nextInt();
		int x,y;
		int num;
		for(int i = 0;i<test;i++) {
			LinkedList<Point> list = new LinkedList<Point>();
			int cnt = 0;
			m = s.nextInt();
			n = s.nextInt();
			map = new int[n][m];
			visit = new int[n][m];
			num = s.nextInt();
			for(int j =0;j<num;j++) {
				y = s.nextInt();
				x = s.nextInt();
				map[x][y] = 1;
				list.add(new Point(x,y));
			}
			for(int j = 0 ;j <list.size();j++) {
				Point p = list.get(j);
				if(visit[p.x][p.y]== 0) {
					BFS(p.x,p.y);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
	public static void BFS(int x,int y) {
		int nx,ny;
		q.add(new Point(x,y));
		visit[x][y] = 1;
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.poll().y;
			for(int i = 0 ; i < 4;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx && nx<n&&0<= ny&& ny<m) {
					if(visit[nx][ny] == 0 && map[nx][ny] == 1) {
						q.add(new Point(nx,ny));
						visit[nx][ny] = 1;
					}
				}
			}
		}
	}
}
