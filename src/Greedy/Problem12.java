package Greedy;

import java.io.*;
import java.util.*;

public class Problem12 {
    static int n;
    static HashMap<Integer, HashMap<Integer, Integer>> map;
    static int answer;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] nv = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	map = new HashMap<>();
	n = nv[0];
	answer = 0;

	for (int i = 0; i < nv[1]; i++) {
	    int[] infos = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	    map.computeIfAbsent(infos[0], k -> new HashMap<>()).put(infos[1], infos[2]);
	    map.computeIfAbsent(infos[1], k -> new HashMap<>()).put(infos[0], infos[2]);
	}

	prim(1);
	System.out.println(answer);
    }

    private static void prim(int start) {
	int[] length = new int[n + 1];
	boolean[] visit = new boolean[n + 1];
	Arrays.fill(length, Integer.MAX_VALUE);
	length[start] = 0;
	PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
	q.add(new int[] { start, start, 0 });

	while (!q.isEmpty()) {
	    int st = q.peek()[0];
	    int cur = q.peek()[1];
	    int dis = q.poll()[2];

	    if (visit[cur])
		continue;

	    visit[cur] = true;
	    answer += dis;

	    if (map.containsKey(cur)) {
		for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
		    int next = entry.getKey();
		    int nDis = entry.getValue();

		    if (nDis < length[next]) {
			length[next] = nDis;
			q.add(new int[] { cur, next, nDis });
		    }
		}
	    }
	}
    }
}
