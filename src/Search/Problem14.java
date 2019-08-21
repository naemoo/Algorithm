package Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class DPoint {
	int x,y,z;
	DPoint(int z,int x,int y){
		this.z = z;
		this.x = x;
		this.y = y;
	}
}

public class Problem14 {
	static Queue<DPoint> q = new LinkedList<>();
	static int cnt = 0;
	static int[] dx = {1,0,-1,0,0,0};
	static int[] dy = {0,1,0,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};//�� �Ʒ�
	static int[][][] map;
	static int[][][] visit;
	static int m,n,h;
	static int max;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		m = s.nextInt();
		n = s.nextInt();
		h = s.nextInt();
		map = new int[h][n][m];
		for(int t=0;t<h;t++)
			for(int i =0;i<n;i++)
				for(int j= 0;j<m;j++) {
					map[t][i][j] = s.nextInt();
					if(map[t][i][j] == 1) 
						q.add(new DPoint(t,i,j));
				}
		BFS();
		if(cnt == 0)
			System.out.println(0);
		else if(isRipe())
			System.out.println(max-1);
	}
	public static boolean isRipe() {
		for(int t=0;t<h;t++)
			for(int i =0;i<n;i++) 
				for(int j= 0;j<m;j++) 
					if(map[t][i][j] == 0) {
						System.out.println(-1);
						return false;
					}
		return true;
	}
	public static void BFS() {
		int x,y,z;
		int nx,ny,nz;
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.peek().y;
			z = q.poll().z;
			for(int i = 0 ; i <6;i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				nz = z + dz[i];
				if(0<=nx&&nx<n&&0<=ny&&ny<m&&0<=nz&&nz<h) {
					if(map[nz][nx][ny] == 0) {
						cnt++;
						q.add(new DPoint(nz,nx,ny));
						max = map[nz][nx][ny] = map[z][x][y] + 1;
					}
				}
			}
		}
	}
}
