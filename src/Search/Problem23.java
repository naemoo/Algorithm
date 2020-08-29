package Search;

import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Problem23 {
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int[][] map;

    static class Point {
	int x;
	int y;
	int d;

	Point(int x, int y, int d) {
	    this.x = x;
	    this.y = y;
	    this.d = d;
	}
    }

    public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	Queue<Point> q = new LinkedList<>();
	map = new int[n][n];

	for (int i = 0; i < n; i++) {
	    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}

	map[0][0] = 2;
	q.add(new Point(0, 0, 1));
	BFS(q);

	System.out.println(Arrays.deepToString(map));
    }

    static void BFS(Queue<Point> q) {
	int n = map.length;

	while (!q.isEmpty()) {

	    int x = q.peek().x;
	    int y = q.peek().y;
	    int depth = q.poll().d;

	    for (int i = 0; i < 4; i++) {
		int nx = x + d[i][0];
		int ny = y + d[i][1];
		int nd = depth + 1;
		try {
		    if (map[nx][ny] == 1) {
			q.add(new Point(nx, ny, nd));
			map[nx][ny] = 2;
			if (nx == n - 1 && ny == n - 1) {
			    System.out.print(nd);
			}
		    }

		} catch (ArrayIndexOutOfBoundsException e) {
		}
	    }
	}
    }

}
