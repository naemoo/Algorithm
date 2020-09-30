package BinarySearch;

import java.util.*;
import java.io.*;

public class Problem07 {
    static HashMap<Integer, HashMap<Integer, Integer>> bridges;
    static int n;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] nm = br.readLine().split("\\s");
	n = Integer.parseInt(nm[0]);
	int m = Integer.parseInt(nm[1]);
	int min = Integer.MAX_VALUE;
	int max = 0;
	bridges = new HashMap<>();
	for (int i = 0; i < m; i++) {
	    Integer[] info = Arrays.stream(br.readLine().split("\\s")).map(Integer::valueOf).toArray(Integer[]::new);
	    bridges.computeIfAbsent(info[0], k -> new HashMap<>()).put(info[1], info[2]);
	    bridges.computeIfAbsent(info[1], k -> new HashMap<>()).put(info[0], info[2]);
	    min = Math.min(min, info[2]);
	    max = Math.max(max, info[2]);
	}
	String[] info = br.readLine().split("\\s");
	int fact1 = Integer.parseInt(info[0]);
	int fact2 = Integer.parseInt(info[1]);
	int answer = getMax(fact1, fact2, min, max);
	System.out.println(answer);
	bridges.clear();
    }

    private static int getMax(int fact1, int fact2, int min, int max) {
	int left = min;
	int right = max;
	while (left < right) {
	    int mid = (left + right + 1) / 2;
	    if (canGo(fact1, fact2, mid)) {
		left = mid;
	    } else {
		right = mid - 1;
	    }
	}
	return right;

    }

    private static boolean canGo(int start, int end, int mid) {
	boolean[] visit = new boolean[n + 1];
	Queue<Integer> q = new LinkedList<>();
	q.add(start);
	visit[start] = true;
	while (!q.isEmpty()) {
	    int cur = q.poll();
	    if (cur == end) {
		return true;
	    }
	    for (Map.Entry<Integer, Integer> bridge : bridges.get(cur).entrySet()) {
		if (!visit[bridge.getKey()] && mid <= bridge.getValue()) {
		    q.add(bridge.getKey());
		    visit[bridge.getKey()] = true;
		}
	    }
	}
	return false;
    }

}
