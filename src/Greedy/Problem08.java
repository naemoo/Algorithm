package Greedy;

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
	    for (int j = 1; j <= Integer.parseInt(info[0]); j++) {
		map.computeIfAbsent(j, k -> new HashMap<>()).put(j, 0);
	    }

	    int[] answer = spreadVirus(Integer.parseInt(info[0]), Integer.parseInt(info[2]), map);
	    System.out.println(answer[0] + " " + answer[1]);
	}
    }

    private static int[] spreadVirus(int computerNumber, int start, HashMap<Integer, HashMap<Integer, Integer>> map) {
	int[] length = new int[computerNumber + 1];
	int[] answer = new int[] { 1, 0 };
	int near = start;
	Arrays.fill(length, Integer.MAX_VALUE);
	length[start] = -1;
	while (near > 0) {
	    reNew(length, start, near, map);
	    near = getSmallIdx(length);
	    if (near > 0) {
		answer[0]++;
		answer[1] = Math.max(answer[1], length[near]);
		length[near] = -1;
	    }
	}
	return answer;
    }

    private static void reNew(int[] length, int start, int near, HashMap<Integer, HashMap<Integer, Integer>> map) {
	for (Map.Entry<Integer, Integer> entry : map.get(near).entrySet()) {
	    Integer des = entry.getKey();
	    length[des] = Math.min(length[des],
		    map.get(start).getOrDefault(near, 0) + map.get(near).getOrDefault(des, 0));
	}
    }

    private static int getSmallIdx(int[] length) {
	int min = Integer.MAX_VALUE - 1;
	int idx = -1;
	for (int i = 1; i < length.length; i++) {
	    if (0 < length[i] && length[i] <= min) {
		idx = i;
		min = length[i];
	    }
	}
	return idx;
    }
}
