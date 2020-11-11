package Greedy;

import java.util.*;
import java.io.*;

public class Problem15 {
    static int getCost(int[] p1, int[] p2) {
	int cost = Integer.MAX_VALUE;
	for (int i = 0; i < 3; i++) {
	    cost = Integer.min(cost, Math.abs(p1[i] - p2[i]));
	}
	return cost;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	int[][] planets = new int[n + 1][];

	for (int i = 1; i <= n; i++) {
	    planets[i] = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	}

	PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
	boolean[] visit = new boolean[n + 1];
	q.add(new int[] { 1, 0 });

	int answer = 0;
	while (!q.isEmpty()) {
	    int cur = q.peek()[0];
	    int dis = q.poll()[1];

	    if (visit[cur]) {
		continue;
	    } else {
		answer += dis;
		visit[cur] = true;
	    }
	    for (int i = 1; i <= n; i++) {
		if (!visit[i]) {
		    q.add(new int[] { i, getCost(planets[cur], planets[i]) });
		}
	    }
	}
	System.out.println(answer);
    }
}
