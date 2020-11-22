package Greedy;

import java.util.*;
import java.io.*;

public class Problem22 {
    class Solution {
	HashMap<Integer, HashMap<Integer, Integer>> map;

	public int solution(int N, int[][] road, int K) {
	    map = new HashMap<>();

	    for (int[] edge : road) {
		int v1 = edge[0];
		int v2 = edge[1];
		int weight = edge[2];
		if (map.computeIfAbsent(v1, k -> new HashMap<>()).containsKey(v2)) {
		    weight = Integer.min(weight, map.get(v1).get(v2));
		}
		map.get(v1).put(v2, weight);
		map.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], weight);
		map.computeIfAbsent(edge[1], k -> new HashMap<>()).put(edge[0], weight);
	    }

	    PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
	    int[] length = new int[N + 1];
	    Arrays.fill(length, Integer.MAX_VALUE);
	    q.add(new int[] { 1, 0 });

	    while (!q.isEmpty()) {
		int cur = q.peek()[0];
		int len = q.poll()[1];

		if (length[cur] < len)
		    continue;

		length[cur] = len;

		if (map.containsKey(cur)) {
		    for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
			int next = entry.getKey();
			int nLen = entry.getValue() + len;

			if (nLen < length[next]) {
			    q.add(new int[] { next, nLen });
			}
		    }
		}
	    }
	    int answer = 0;
	    for (int len : length) {
		if (len <= K)
		    answer++;
	    }

	    return answer;
	}
    }
}
