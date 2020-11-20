package Greedy;

import java.util.*;
import java.io.*;

public class Problem21 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	int v = Integer.parseInt(st.nextToken());
	int e = Integer.parseInt(st.nextToken());
	int start = Integer.parseInt(br.readLine());
	HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

	for (int i = 0; i < e; i++) {
	    st = new StringTokenizer(br.readLine(), " ");
	    int v1 = Integer.parseInt(st.nextToken());
	    int v2 = Integer.parseInt(st.nextToken());
	    int weight = Integer.parseInt(st.nextToken());
	    if (map.computeIfAbsent(v1, k -> new HashMap<>()).containsKey(v2)) {
		weight = Integer.min(weight, map.get(v1).get(v2));
	    }
	    map.get(v1).put(v2, weight);
	}
	dijkstra(start, map, v);
	map.clear();
    }

    private static void dijkstra(int start, HashMap<Integer, HashMap<Integer, Integer>> map, int v) {
	int[] length = new int[v + 1];
	boolean[] visit = new boolean[v + 1];
	Arrays.fill(length, Integer.MAX_VALUE);
	PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
	q.add(new int[] { start, 0 });

	while (!q.isEmpty()) {
	    int cur = q.peek()[0];
	    int len = q.poll()[1];

	    if (visit[cur]) {
		continue;
	    } else {
		visit[cur] = true;
		length[cur] = len;
	    }

	    if (map.containsKey(cur)) {
		for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
		    int next = entry.getKey();
		    int nLen = len + entry.getValue();

		    if (nLen < length[next]) {
			q.add(new int[] { next, nLen });
		    }
		}
	    }
	}

	StringBuilder sb = new StringBuilder();
	for (int i = 1; i <= v; i++) {
	    sb.append(length[i] != Integer.MAX_VALUE ? length[i] : "INF");
	    sb.append("\n");
	}
	System.out.println(sb);
    }

}
