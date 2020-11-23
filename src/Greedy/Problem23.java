package Greedy;

import java.util.*;
import java.io.*;

public class Problem23 {
    static HashMap<Integer, HashMap<Integer, Integer>> infos;
    static int min = Integer.MAX_VALUE;
    static int n;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] nm = br.readLine().split("\\s");
	n = Integer.parseInt(nm[0]);
	int m = Integer.parseInt(nm[1]);
	infos = new HashMap<>();

	for (int i = 0; i < m; i++) {
	    String[] info = br.readLine().split("\\s");
	    int start = Integer.parseInt(info[0]);
	    int end = Integer.parseInt(info[1]);
	    int weight = Integer.parseInt(info[2]);

	    HashMap<Integer, Integer> airplane = infos.computeIfAbsent(start, k -> new HashMap<>());

	    if (airplane.containsKey(end)) {
		airplane.put(end, Integer.min(airplane.get(end), weight));
	    } else {
		airplane.put(end, weight);
	    }
	}

	dijkstra(1);

    }

    private static void dijkstra(int start) {
	int[] length = new int[n + 1];
	Arrays.fill(length, Integer.MAX_VALUE);
	Queue<int[]> q = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[1], b[1]));
	q.add(new int[] { start, 0 });

	while (!q.isEmpty()) {
	    int cur = q.peek()[0];
	    int dis = q.poll()[1];
	    // 갱신이 된 상태라면 무시하고 넘어감
	    if (length[cur] < dis)
		continue;

	    if (infos.containsKey(cur)) {
		for (Map.Entry<Integer, Integer> entry : infos.get(cur).entrySet()) {
		    int next = entry.getKey();
		    int nxtDis = dis + entry.getValue();

		    if (nxtDis < length[next]) {
			length[next] = nxtDis;
			q.add(new int[] { next, nxtDis });
		    }
		}
	    }
	}
	System.out.println(length[n] != Integer.MAX_VALUE ? length[n] : "go home");
    }
}
