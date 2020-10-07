package Search;

import java.util.*;
import java.io.*;

public class Problem28 {
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine().split("\\s")[0]);
	int[][] map = new int[n][];

	for (int i = 0; i < n; i++) {
	    map[i] = br.readLine().chars().map(e -> e - '0').toArray();
	}
	System.out.println(getShortestPath(map));
    }

    private static int getShortestPath(int[][] map) {
	Queue<int[]> q = new LinkedList<>();
	boolean[][] visit = new boolean[map.length][map[0].length];
	q.add(new int[] { 0, 0, 1 });
	visit[0][0] = true;

	while (!q.isEmpty()) {
	    int[] cur = q.poll();
	    for (int i = 0; i < 4; i++) {
		int[] next = new int[] { cur[0] + d[i][0], cur[1] + d[i][1], cur[2] + 1 };
		if (0 <= next[0] && next[0] < map.length && 0 <= next[1] && next[1] < map[0].length) {
		    if (!visit[next[0]][next[1]] && map[next[0]][next[1]] == 1) {
			visit[next[0]][next[1]] = true;
			q.add(next);
		    }
		    if (next[0] == map.length - 1 && next[1] == map[0].length - 1) {
			return next[2];
		    }

		}
	    }
	}
	return -1;
    }

}
