/*
 * https://www.acmicpc.net/problem/14502
 * 연구소 (DFS,BFS + 백트래킹)
*/
package SamsungSWTest;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Problem02 {
    static int[][] d = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int[][] map;
    static int max = 0;
    static List<Point> virus;

    static class Point {
	int x;
	int y;

	public Point(int x, int y) {
	    super();
	    this.x = x;
	    this.y = y;
	}

	@Override
	public int hashCode() {
	    int result = 31 + x;
	    result = 31 * result + y;
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    Point other = (Point) obj;
	    if (x != other.x || y != other.y)
		return false;
	    return true;
	}

	public Point next(int i) {
	    return new Point(x + d[i][0], y + d[i][1]);
	}

    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	int n = Integer.parseInt(st.nextToken());
	int m = Integer.parseInt(st.nextToken());
	map = new int[n][m];
	for (int i = 0; i < n; i++) {
	    map[i] = Stream.of(br.readLine()).flatMap(s -> Arrays.stream(s.split(" "))).mapToInt(Integer::parseInt)
		    .toArray();
	}
	virus = findVirus();
	DFS(0);
	System.out.println(max);
    }

    private static LinkedList<Point> findVirus() {
	LinkedList<Point> list = new LinkedList<>();
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[0].length; j++) {
		if (map[i][j] == 2)
		    list.add(new Point(i, j));
	    }
	}
	return list;
    }

    private static void DFS(int depth) {
	if (depth == 3) {
	    max = Math.max(max, getSafeArea(spread()));
	    return;
	}

	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[0].length; j++) {
		if (map[i][j] == 0) {
		    map[i][j] = 1;
		    DFS(depth + 1);
		    map[i][j] = 0;
		}
	    }
	}
    }

    private static int[][] spread() {
	HashSet<Point> visit = new HashSet<>(virus);
	Queue<Point> q = new LinkedList<>(virus);
	int[][] clonedMap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
	while (!q.isEmpty()) {
	    Point cur = q.poll();
	    for (int i = 0; i < 4; i++) {
		Point next = cur.next(i);
		if (0 <= next.x && next.x < map.length && 0 <= next.y && next.y < map[0].length) {
		    if (map[next.x][next.y] == 0 && !visit.contains(next)) {
			clonedMap[next.x][next.y] = 2;
			visit.add(next);
			q.add(next);
		    }
		}
	    }
	}
	return clonedMap;
    }

    private static int getSafeArea(int[][] cloneMap) {
	int answer = 0;
	for (int[] mapRow : cloneMap) {
	    for (int area : mapRow) {
		if (area == 0)
		    answer++;
	    }
	}
	return answer;
    }
}
