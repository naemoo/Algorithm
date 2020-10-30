package Greedy;

import java.io.*;
import java.util.*;

public class Problem10 {
    static int[][] map;
    static int[][] d = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int gamesCnt = Integer.parseInt(br.readLine());

	for (int i = 0; i < gamesCnt; i++) {
	    int n = Integer.parseInt(br.readLine());
	    map = new int[n][];
	    for (int j = 0; j < n; j++) {
		map[j] = br.readLine().chars().map(ele -> ele - '0').toArray();
	    }
	    System.out.print("#" + (i + 1) + " ");
	    dijkstra(0, 0);
	}

    }

    private static void dijkstra(int startX, int startY) {
	Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
	pq.add(new int[] { startX, startY, 0 });
	int[][] dist = new int[map.length][map[0].length];
	for (int i = 0; i < dist.length; i++) {
	    Arrays.fill(dist[i], Integer.MAX_VALUE);
	}

	dist[startX][startY] = 0;

	while (!pq.isEmpty()) {
	    int curX = pq.peek()[0];
	    int curY = pq.peek()[1];
	    int weight = pq.poll()[2];

	    if (dist[curX][curY] < weight)
		continue;

	    for (int i = 0; i < 4; i++) {
		int nextX = curX + d[i][0];
		int nextY = curY + d[i][1];
		if (0 <= nextX && nextX < map.length && 0 <= nextY && nextY < map[0].length) {
		    int nextWeight = weight + map[nextX][nextY];
		    if (nextWeight < dist[nextX][nextY]) {
			pq.add(new int[] { nextX, nextY, nextWeight });
			dist[nextX][nextY] = nextWeight;
		    }
		}
	    }
	}

	System.out.println(dist[dist.length - 1][dist[0].length - 1]);
    }

}
