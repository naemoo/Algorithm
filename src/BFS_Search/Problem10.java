package BFS_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem10 {
	static Queue<Point> q = new LinkedList<>();
	static int cnt = 0;
	static int n;
	static int m;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] map;
	static int max;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		m = s.nextInt();
		n = s.nextInt();
		map = new int[n][m];
		for(int i = 0 ; i<n;i++) {
			for(int j = 0;j<m;j++) {
				map[i][j] = s.nextInt();
				if(map[i][j] == 1)
					q.add(new Point(i,j));
			}
		}
		BFS();
		if(cnt == 0)
			System.out.println(0);
		else if(Is_ripe())
			System.out.println(max-1);
	}
	public static boolean Is_ripe() {
		for(int i = 0 ; i < n;i++) 
			for(int j=0;j<m;j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return false;
				}
			}
		return true;
	}
	public static void BFS() {
		int x,y;
		int nx,ny;
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.poll().y;
			for(int i = 0 ; i < 4;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx&&nx<n&&0<=ny&&ny<m) {
					if(map[nx][ny] == 0) {
						cnt++;
						q.add(new Point(nx,ny));
						map[nx][ny] = map[x][y] +1;
						max = map[nx][ny];
					}
				}
			}
		}
	}
}
