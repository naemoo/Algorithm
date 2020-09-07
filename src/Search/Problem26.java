package Search;

import java.util.*;

public class Problem26 {
    public static int solution(int n, int[][] edge) {
	HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

	for (int[] e : edge) {
	    map.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
	    map.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
	}

	return getLongestNumber(n, map);
    }

    private static int getLongestNumber(int n, HashMap<Integer, HashSet<Integer>> map) {
	boolean[] visit = new boolean[n + 1];
	Queue<int[]> q = new LinkedList<>();
	int answer = 0;
	int max = -1;

	visit[1] = true;
	q.add(new int[] { 1, 0 });

	while (!q.isEmpty()) {
	    int[] cur = q.poll();

	    if (map.containsKey(cur[0])) {
		for (int adj : map.get(cur[0])) {
		    if (!visit[adj]) {
			q.add(new int[] { adj, cur[1] + 1 });
			visit[adj] = true;

			if (max < cur[1] + 1) {
			    max = cur[1] + 1;
			    answer = 1;
			} else if(max == cur[1]+1) {
			    answer++;
			}
		    }
		}
	    }
	}
	return answer;
    }

    public static void main(String[] args) {
	solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } });
    }

}
