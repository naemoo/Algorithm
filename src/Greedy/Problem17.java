package Greedy;

import java.io.*;
import java.util.*;

public class Problem17 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int test = Integer.parseInt(br.readLine());

	for (int i = 0; i < test; i++) {
	    int width = Integer.parseInt(br.readLine());
	    int num = Integer.parseInt(br.readLine());
	    int[][] points = new int[num][];

	    for (int j = 0; j < num; j++) {
		points[j] = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	    }

	    double answer = kruskal(points, num, width);
	    System.out.println(String.format("%.6f", answer / 2));
	}

    }

    private static double kruskal(int[][] points, int num, int width) {
	UnionFind union = new UnionFind(num + 2);
	Queue<double[]> q = new PriorityQueue<>((a, b) -> Double.compare(a[2], b[2]));
	q.add(new double[] { num, num + 1, width });

	for (int i = 0; i < num; i++) {
	    q.add(new double[] { i, num, Math.max(0, points[i][0] - points[i][2]) });
	    q.add(new double[] { i, num + 1, Math.max(0, width - points[i][0] - points[i][2]) });

	    for (int j = 0; j < i; j++) {
		q.add(new double[] { i, j,
			Math.max(0, getDistance(points[i], points[j])) - points[i][2] - points[j][2] });
	    }
	}

	int cnt = 0;
	double answer = 0;
	while (union.find(num) != union.find(num + 1)) {
	    int start = (int) q.peek()[0];
	    int end = (int) q.peek()[1];
	    double len = q.poll()[2];

	    start = union.find(start);
	    end = union.find(end);

	    if (start != end) {
		cnt++;
		union.merge(start, end);
		answer = len;
	    }
	}
	return answer;

    }

    static double getDistance(int[] p1, int[] p2) {
	double sum = 0;
	for (int i = 0; i < 2; i++) {
	    sum += Math.pow(p1[i] - p2[i], 2);
	}
	return Math.sqrt(sum);
    }

    static class UnionFind {

	private class Node {
	    int parent;
	    int depth;

	    public Node(int parent) {
		this.parent = parent;
		this.depth = 0;
	    }
	}

	Node[] U;
	final int n; // 정점

	public UnionFind(int n) {
	    this.n = n;
	    U = new Node[n];

	    for (int i = 0; i < n; i++)
		U[i] = new Node(i);
	}

	// Path Compression
	public int find(int i) {
	    return U[i].parent == i ? i : (U[i].parent = find(U[i].parent));
	}

	// Rank System
	public void merge(int p, int q) {
	    p = find(p);
	    q = find(q);

	    if (U[p].depth == U[q].depth) {
		U[p].depth += 1;
		U[q].parent = p;
	    } else if (U[p].depth < U[q].depth)
		U[p].parent = q;
	    else
		U[q].parent = p;
	}
    }

}
