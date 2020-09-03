package Search;

import java.io.*;
import java.util.*;

public class Problem24 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	String[] line = br.readLine().split(" ");
	Queue<int[]> q = new LinkedList<>();
	int[][] map = new int[Integer.parseInt(line[1])][Integer.parseInt(line[0])];

	for (int i = 0; i < map.length; i++) {
	    st = new StringTokenizer(br.readLine(), " ");
	    for (int j = 0; j < map[0].length; j++) {
		map[i][j] = Integer.parseInt(st.nextToken());
		if (map[i][j] == 1)
		    q.add(new int[] { i, j, 1 });
	    }
	}
	int answer = reapTomato(map, q);
	System.out.println(isAllReapTomato(map) ? answer : -1);
    }

    private static int reapTomato(int[][] map, Queue<int[]> q) {
	int max = 0;
	int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	while (!q.isEmpty()) {
	    int x = q.peek()[0];
	    int y = q.peek()[1];
	    int day = q.poll()[2];
	    for (int i = 0; i < 4; i++) {
		int nx = x + d[i][0];
		int ny = y + d[i][1];
		int nextDay = day + 1;
		try {
		    if (map[nx][ny] == 0) {
			q.add(new int[] { nx, ny, nextDay });
			map[nx][ny] = 1;
			max = Integer.max(max, nextDay - 1);
		    }
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	    }
	}
	return max;
    }

    private static boolean isAllReapTomato(int[][] map) {
	for (int[] tomatos : map)
	    for (int tomato : tomatos)
		if (tomato == 0)
		    return false;
	return true;
    }
}
