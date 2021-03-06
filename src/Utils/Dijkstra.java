package Utils;

import java.util.*;

public class Dijkstra {
    static int n = 0;
    static HashMap<Integer, HashMap<Integer, Integer>> map = null;

    public static void dijkstra(int start) {
	// length : start와 각 간선 까지의 거리
	int[] length = new int[n + 1];
	Arrays.fill(length, Integer.MAX_VALUE);
	length[start] = 0;
	// { v, length} : start와 v 까지 거리
	Queue<int[]> q = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[1], b[1]));
	q.add(new int[] { start, 0 });

	while (!q.isEmpty()) {
	    int cur = q.peek()[0];
	    int dis = q.poll()[1];
	    // 갱신이 된 상태라면 무시하고 넘어감
	    if (length[cur] < dis)
		continue;

	    // 현재 위치에서 갈 곳이 없으면 다음 큐로
	    if (map.containsKey(cur)) {
		for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
		    int next = entry.getKey();
		    int nxtDis = dis + entry.getValue(); // (start ~ cur ~ next 거리)

		    if (nxtDis < length[next]) {
			length[next] = nxtDis;
			q.add(new int[] { next, nxtDis });
		    }
		}
	    }
	}
    }
}
