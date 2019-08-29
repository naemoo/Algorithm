package Search;

/*
 * 백준 : 벽 부수고 이동하기(BFS탐색)
 * https://www.acmicpc.net/problem/2206
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class MapPoint{
	int x;
	int y;
	int flag;
	public MapPoint(int x, int y, int flag) {
		this.x = x;
		this.y = y;
		this.flag = flag;
	}
}

public class Problem16 {
	static int n,m;
	static Queue<MapPoint> q = new LinkedList<>();
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int[][] map;
	static int[][][] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		map = new int[n][m];
		visit = new int[n][m][2];
		for(int i =0;i<n;i++) {
			String str = s.next();
			for(int j = 0;j<m;j++) {
				map[i][j] = str.charAt(j)- '0';
			}
		}
		BFS();
	}
	public static void print() {
		for(int i = 0;i<n;i++) {
			for(int j = 0 ; j <m;j++) {
				System.out.print(visit[i][j][0] + " ");
			}
			System.out.println();
		}
		for(int i = 0;i<n;i++) {
			for(int j = 0 ; j <m;j++) {
				System.out.print(visit[i][j][1] + " ");
			}
			System.out.println();
		}
	}

	public static void BFS() {
		q.add(new MapPoint(0,0,0));
		visit[0][0][0] = 1;
		int x,y;
		int flag;
		int nx,ny;
		while(!q.isEmpty()) {
			MapPoint p = q.poll();
			x = p.x;
			y = p.y;
			flag = p.flag;
			if(x == n-1 && y == m-1) {
				System.out.println(visit[x][y][flag]);
				return;
			}
			for(int i=0;i<4;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx && nx< n && 0<= ny&& ny < m) {
					if(map[nx][ny] == 0 && visit[nx][ny][flag] == 0) {
						visit[nx][ny][flag] = visit[x][y][flag] +1;
						q.add(new MapPoint(nx, ny, flag));
					}
					else if(map[nx][ny] == 1 && flag == 0) {
						visit[nx][ny][1] = visit[x][y][0] + 1;
						q.add(new MapPoint(nx, ny, 1));
					}
				}
			}
		}
		System.out.println(-1);
	}
}