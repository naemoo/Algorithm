/*
 * https://programmers.co.kr/learn/courses/30/lessons/67259
 * 경주로 건설
*/
package Kakao;

import java.util.*;

public class Problem36 {
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int[][] map;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static int solution(int[][] board) {
	map = board;
	n = board.length;
	setMinByShortest();
	return min;
    }

    private static void setMinByShortest() {
	Queue<int[]> q = new LinkedList<>();
	q.add(new int[] { 0, 0, 0, 0});
	map[0][0] = 1;

	while (!q.isEmpty()) {
	    int x = q.peek()[0];
	    int y = q.peek()[1];
	    int type = q.peek()[2];
	    int cost = q.poll()[3];

	    for (int i = 0; i < 4; i++) {
		int nx = x + d[i][0];
		int ny = y + d[i][1];
		int newType = i < 2 ? 0 : 1;
		try {
		    int newCost = ((x == 0 && y == 0) || type == newType) ? 100 : 600;
		    if (map[nx][ny] == 0 || map[nx][ny] >= cost + newCost) {
			q.add(new int[] { nx, ny, newType, cost + newCost });
			map[nx][ny] = cost + newCost;
		    }

		    if (nx == n - 1 && ny == n - 1) {
			min = Integer.min(min, map[nx][ny]);
		    }

		} catch (ArrayIndexOutOfBoundsException e) {

		}
	    }
	}
    }

    public static void main(String[] args) {
	solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
		{ 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 },
		{ 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } });
	solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
    }

}
