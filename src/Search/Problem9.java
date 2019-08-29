package Search;

/*
 * 백준 : 나이트의 이동(BFS탐색)
 * https://www.acmicpc.net/problem/7562
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem9 {
	static Queue<Point> q = new LinkedList<>();
	static int test_num;
	static int[] dx = {-2,-1,1,2,2,1,-1,-2};
	static int[] dy = {1,2,2,1,-1,-2,-2,-1};
	static int n;
	static int destx;
	static int desty;
	static int[][] map;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x,y;
		test_num = s.nextInt();
		int[] arr = new int[test_num];
		for(int i= 0;i <test_num;i++) {
		n = s.nextInt();
		map = new int[n][n];
		x = s.nextInt();
		y = s.nextInt();
		destx = s.nextInt();
		desty = s.nextInt();
		BFS(x,y);
		arr[i] = map[destx][desty] -1;
		}
		for(int i = 0 ; i < test_num;i++)
			System.out.println(arr[i]);

	}
	public static void BFS(int x,int y) {
		int nx,ny;
		q.add(new Point(x,y));
		map[x][y] = 1;
		while(!q.isEmpty()){
			x = q.peek().x;
			y = q.poll().y;
			for(int i = 0 ; i < 8;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(0<=nx&&nx<n&&0<=ny&&ny<n) {
					if(map[nx][ny] == 0) {
						q.add(new Point(nx,ny));
						map[nx][ny] = map[x][y] + 1;
					}
				}
			}
		}
	}
}
