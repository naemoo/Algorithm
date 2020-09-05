package Greedy;

/*
 * https://www.acmicpc.net/problem/10282
 * 해킹(다익스트라 알고리즘)
*/

import java.io.*;
import java.util.*;

public class Problem08 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
	int testCase = Integer.parseInt(br.readLine());

	for (int i = 0; i < testCase; i++) {
	    String[] info = br.readLine().split(" ");
	    for (int j = 0; j < Integer.parseInt(info[1]); j++) {
		String[] dependency = br.readLine().split(" ");
		map.computeIfAbsent(Integer.parseInt(dependency[1]), k -> new HashMap<>())
			.put(Integer.parseInt(dependency[0]), Integer.parseInt(dependency[2]));
	    }
	    int[] answer = spreadVirus(Integer.parseInt(info[0]), Integer.parseInt(info[2]), map);
	    System.out.println(answer[0] + " " + answer[1]);
	    map.clear();
	}
    }

    private static int[] spreadVirus(int computerNumber, int start, HashMap<Integer, HashMap<Integer, Integer>> map) {
	int[] length = new int[computerNumber + 1];
	boolean[] visit = new boolean[computerNumber + 1];
	int[] answer = new int[] { 1, 0 };
	int near = start;
	Arrays.fill(length, Integer.MAX_VALUE);
	length[start] = 0;
	visit[start] = true;
	while (near > 0) {
	    reNew(length, start, near, map);
	    near = getSmallIdx(length, visit);
	    if (near > 0) {
		answer[0]++;
		answer[1] = Math.max(answer[1], length[near]);
		visit[near] = true;
	    }
	}
	return answer;
    }

    private static void reNew(int[] length, int start, int near, HashMap<Integer, HashMap<Integer, Integer>> map) {
	if (map.containsKey(near)) {
	    for (Map.Entry<Integer, Integer> entry : map.get(near).entrySet()) {
		Integer des = entry.getKey();
		length[des] = Integer.min(length[des],
			length[near] + map.get(near).getOrDefault(des, Integer.MAX_VALUE - length[near]));
	    }
	}

    }

    private static int getSmallIdx(int[] length, boolean[] visit) {
	int min = Integer.MAX_VALUE - 1;
	int idx = -1;
	for (int i = 1; i < length.length; i++) {
	    if (!visit[i] && length[i] <= min) {
		idx = i;
		min = length[i];
	    }
	}
	return idx;
    }
}
