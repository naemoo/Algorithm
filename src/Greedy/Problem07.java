package Greedy;

import java.util.*;

public class Problem07 {

    private static int solution(int n, int[][] costs) {
	HashMap<Integer, List<int[]>> map = new HashMap<>();
	HashSet<Integer> e = new HashSet<>();
	List<Integer> answer = new LinkedList<>();

	for (int[] cost : costs) {
	    map.computeIfAbsent(cost[0], k -> new LinkedList<>()).add(new int[] { cost[1], cost[2] });
	    map.computeIfAbsent(cost[1], k -> new LinkedList<>()).add(new int[] { cost[0], cost[2] });
	}

	e.add(0);
	while (e.size() < n) {
	    int vertex = getNearest(e, map, answer);
	    e.add(vertex);
	}

	return answer.stream().mapToInt(Integer::intValue).sum();
    }

    private static int getNearest(HashSet<Integer> e, HashMap<Integer, List<int[]>> map, List<Integer> answer) {
	int min = Integer.MAX_VALUE;
	int ver = -1;
	for (int vertex : e) {
	    for (int[] info : map.get(vertex)) {
		if (min > info[1] && !e.contains(info[0])) {
		    min = info[1];
		    ver = info[0];
		}
	    }
	}
	answer.add(min);
	return ver;
    }

    public static void main(String[] args) {
	int ans = solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } });
	System.out.println(ans);
    }

}
