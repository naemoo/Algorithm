package Greedy;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Problem09 {
    static HashMap<Integer, HashMap<Integer, Integer>> infos;
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;
    static int n;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] nm = br.readLine().split("\\s");
	n = Integer.parseInt(nm[0]);
	int m = Integer.parseInt(nm[1]);
	visit = new boolean[n + 1];
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
	dfs(0, 1);

	System.out.println(min != Integer.MAX_VALUE ? min : "go home");
    }

    private static void dfs(int d, int start) {
	if (start == n) {
	    min = Integer.min(min, d);
	}

	if (infos.containsKey(start)) {
	    for (Entry<Integer, Integer> info : infos.get(start).entrySet()) {
		if (!visit[info.getKey()]) {
		    visit[info.getKey()] = true;
		    dfs(d + info.getValue(), info.getKey());
		    visit[info.getKey()] = false;
		}
	    }
	}
    }
}
