package Search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Problem7 {
	static Queue<Point> q = new LinkedList<>();
	static List<Point> v_list = new LinkedList<>(); 
	static int[] dx= {0,1,0,-1};
	static int[] dy= {1,0,-1,0};
	static int[][] map;
	static int[][] visit;
	static int[][] copy;
	static int n,m;
	static int max;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		map = new int[n][m];
		copy = new int[n][m];
		for(int i = 0 ;i<n;i++)
			for(int j = 0 ; j<m;j++) {
				map[i][j] = s.nextInt();
				if(map[i][j] == 2)
					v_list.add(new Point(i,j));
			}
		copyMap();
		setWall(0, 0, 0);
		System.out.println(max);
	}
	public static void setWall(int x,int y,int depth) {
		if(depth ==3) {
			copyMap();
			visit = new int[n][m];
			for(Point p:v_list) {
				BFS(p.x,p.y);
			}
			max = Math.max(max, getSafeArea());
			return;
		}
		for(int i = x;i<n;i++) {
			for(int j=y;j<m;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					setWall(x,y,depth+1);
					map[i][j] = 0;//�ٽ� �ǵ�����
				}
			}
		}
	}
	public static void copyMap() {
		for(int i = 0;i<n;i++)
			copy[i] = Arrays.copyOf(map[i], m);
	}
	public static int getSafeArea() {
		int cnt = 0;
		for(int i = 0 ; i < n;i++)
			for(int j = 0; j< m; j++)
				if(copy[i][j] == 0)
					cnt++;
		return cnt;
	}
	public static void BFS(int x,int y) {
		q.add(new Point(x,y));
		int nx,ny;
		visit[x][y] = 1;
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.poll().y;
			for(int i = 0 ; i < 4;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx && nx <n && 0<=ny && ny <m) {
					if(copy[nx][ny] == 0 && visit[nx][ny] == 0) {
						q.add(new Point(nx,ny));
						visit[nx][ny] = 1;
						copy[nx][ny] = 2;
					}
				}
			}
		}
	}
}
