package BackTracking;

/*
 * 백준 : 스도쿠(2580번)
 * https://www.acmicpc.net/problem/2580 
*/

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Problem3 {
	static List<Point> list = new LinkedList<Point>();
	static int[][] map = new int[9][9];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		for(int i = 0 ; i < 9;i++)
			for(int j = 0; j <9;j++) {
				map[i][j] = s.nextInt();
				if(map[i][j] == 0)
					list.add(new Point(i,j));
			}
		DFS(0);
	}
	
	public static void print() {
		for(int i =0; i <9;i++) {
			for(int j=0; j<9;j++)
				System.out.print(map[i][j] +" ");
			System.out.println();
		}
	}
	public static boolean check_vertical(int x,int num) {
		for(int i = 0 ; i <9;i++) 
			if(map[x][i] == num)
				return false;
		return true;
	}
	public static boolean check_horizon(int y,int num) {
		for(int i = 0 ; i < 9;i++)
			if(map[i][y] == num)
				return false;
		return true;
	}
	public static boolean check_square(int x,int  y,int num) {
		int rx = x / 3 * 3;
		int ry = y / 3 * 3;
		for(int i = rx ;i<rx+3;i++)
			for(int j = ry; j<ry+3;j++) 
				if(map[i][j] == num)
					return false;
		return true;
	}
	public static void DFS(int d) {
		if(d == list.size()) {
			print();
			System.exit(0);
		}
		int x = list.get(d).x;
		int y = list.get(d).y;
		for(int num =1 ; num < 10 ;num++) {
			if(check_horizon(y, num)&&check_square(x, y, num)&&check_vertical(x, num)) {
				map[x][y] = num;
				DFS(d+1);
				map[x][y] = 0;
			}
		}
	}
}
