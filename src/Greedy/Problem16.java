package Greedy;

import java.util.*;
import java.io.*;

public class Problem16 {
    static double getDistance(double[] p1, double[] p2) {
	double sum = 0;
	for (int i = 0; i < 2; i++) {
	    sum += Math.pow(p1[i] - p2[i], 2);
	}
	return Math.sqrt(sum);
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	double[][] points = new double[n + 1][];

	for (int i = 1; i <= n; i++) {
	    points[i] = Arrays.stream(br.readLine().split("\\s")).mapToDouble(Double::parseDouble).toArray();
	}
	Queue<double[]> q = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
	boolean[] visit = new boolean[n + 1];
	double answer = 0;
	int cur = 1;
	q.add(new double[] { 1, 0 });

	while (!q.isEmpty()) {
	    cur = (int) q.peek()[0];
	    double len = q.poll()[1];

	    if (!visit[cur]) {
		visit[cur] = true;
		answer += len;
	    } else {
		continue;
	    }

	    for (int i = 1; i <= n; i++) {
		if (!visit[i] && cur != i) {
		    q.add(new double[] { i, getDistance(points[i], points[cur]) });
		}
	    }
	}
	System.out.println(String.format("%.2f", answer));
    }
}
