package DP;

/*
 *프로그래머스 : 등굣길(DP) 
*/

import java.util.LinkedList;
import java.util.Queue;

class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


class Solution {
	int[] dx = {1,0,-1,0};
	int[] dy = {0,1,0,-1};
	int[][] dp;
	int[][] map;
	int n,m;
	Queue<Point> q = new LinkedList<Point>();
    public int solution(int m, int n, int[][] puddles) {
    	dp = new int[n+1][m+1];
    	map = new int[n+1][m+1];
    	this.n = n;
    	this.m = m;
    	if(puddles[0].length>=2)
    		for(int i = 0 ; i < puddles.length;i++)
    			dp[puddles[i][1]][puddles[i][0]] = -1;
    	getRoute(1, 1);
    	return dp[n][m];
    }
    public void getRoute(int x,int y) {
    	q.add(new Point (x,y));
    	dp[x][y] = 1;
    	map[x][y] = 1;
    	int nx,ny;
    	while(!q.isEmpty()) {
    		x = q.peek().x;
    		y = q.poll().y;
    		for(int i = 0 ; i <4;i++) {
    			nx = x + dx[i];
    			ny = y + dy[i];
    			if(0<nx&&nx<=n&&0<ny&&ny<=m&&dp[nx][ny] >=0) {
    				if(map[nx][ny] == 0 || map[nx][ny] - map[x][y] == 1) {
    					dp[nx][ny] = (dp[nx][ny] +dp[x][y]) % 1000000007;
    					if(map[nx][ny] == 0) {
    						map[nx][ny] = map[x][y] +1;
    						q.add(new Point(nx,ny));
    					}
    				}
    			}
    		}
    	}
    }
}

public class Problem14 {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(100, 1, new int[][]{{,1}}));
	}
}
