package Greedy;

import java.io.*;
import java.util.*;

public class Problem20 {
    static HashMap<Integer, HashMap<Integer, Double>> map;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	double[] start = Arrays.stream(br.readLine().split("\\s")).mapToDouble(Double::parseDouble).toArray();
	double[] end = Arrays.stream(br.readLine().split("\\s")).mapToDouble(Double::parseDouble).toArray();
	int n = Integer.parseInt(br.readLine());
	map = new HashMap<>();

	double[][] canons = new double[n + 2][];
	canons[0] = start;
	canons[n + 1] = end;
	for (int i = 1; i <= n; i++) {
	    canons[i] = Arrays.stream(br.readLine().split("\\s")).mapToDouble(Double::parseDouble).toArray();
	}

	for (int i = 1; i < n + 2; i++) {
	    double distance = getDistance(canons[0], canons[i]);
	    double time = getTime(distance, 5.0);
	    map.computeIfAbsent(0, k -> new HashMap<>()).put(i, time);
	    map.computeIfAbsent(i, k -> new HashMap<>()).put(0, time);
	}

	for (int i = 1; i < n + 2; i++) {
	    for (int j = 1; j < i; j++) {
		if (i != j) {
		    double distance = getDistance(canons[i], canons[j]);
		    double time = Math.min(getTime(distance, 5.0), getTime(distance - 50, 5.0) + 2);
		    map.get(i).put(j, time);
		    map.get(j).put(i, time);
		}
	    }
	}
	dijkstra(start);

    }

    private static void dijkstra(double[] start) {
	double[] length = new double[map.size()];
	Arrays.fill(length, Double.MAX_VALUE);
	length[0] = 0;
	Queue<double[]> q = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
	q.add(new double[] { 0, 0 });

	while (!q.isEmpty()) {
	    int cur = (int) q.peek()[0];
	    double time = q.poll()[1];

	    if (Double.compare(time, length[cur]) > 0) {
		continue;
	    }

	    if (map.containsKey(cur)) {
		for (Map.Entry<Integer, Double> entry : map.get(cur).entrySet()) {
		    int next = entry.getKey();
		    double nLen = entry.getValue() + time;

		    if (Double.compare(nLen, length[next]) < 0) {
			length[next] = nLen;
			q.add(new double[] { next, nLen });
		    }
		}
	    }
	}
	System.out.println(length[length.length - 1]);
    }

    static double getDistance(double[] p1, double[] p2) {
	double sum = 0;
	for (int i = 0; i < 2; i++) {
	    sum += Math.pow(p1[i] - p2[i], 2);
	}
	return Math.sqrt(sum);
    }

    static double getTime(double distance, double v) {
	return Math.abs(distance) / v;
    }

}
