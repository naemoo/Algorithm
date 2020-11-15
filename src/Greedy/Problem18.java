package Greedy;

import java.util.*;
import java.io.*;

public class Problem18 {
    static int N;
    static HashMap<Integer, HashMap<Integer, Integer>> map;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] nv = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	N = nv[0];
	map = new HashMap<>();

	for (int i = 0; i < nv[1]; i++) {
	    int[] infos = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	    map.computeIfAbsent(infos[0], k -> new HashMap<>()).put(infos[1], infos[2]);
	    map.computeIfAbsent(infos[1], k -> new HashMap<>()).put(infos[0], infos[2]);
	}

	int[] essential = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	int a = essential[0];
	int b = essential[1];

	int answer = getWeight(a, b);
	answer = Integer.min(answer, getWeight(b, a));
	System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	map.clear();
    }

    private static int getWeight(int a, int b) {
	int stToA = dijkstra(1, a);
	int aToB = dijkstra(a, b);
	int bToEnd = dijkstra(b, N);
	return (stToA | aToB | bToEnd) >= 0 ? stToA + aToB + bToEnd : -1;
    }

    private static int dijkstra(int start, int end) {
	int[] length = new int[N + 1];
	Arrays.fill(length, Integer.MAX_VALUE);
	length[start] = 0;
	Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
	q.add(new int[] { start, 0 });

	while (!q.isEmpty()) {
	    int cur = q.peek()[0];
	    int len = q.poll()[1];

	    if (length[cur] < len)
		continue;

	    if (map.containsKey(cur)) {
		for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
		    int next = entry.getKey();
		    int nDis = len + entry.getValue();

		    if (nDis < length[next]) {
			length[next] = nDis;
			q.add(new int[] { next, nDis });
		    }
		}
	    }
	}

	return length[end] != Integer.MAX_VALUE ? length[end] : -1;
    }

}
