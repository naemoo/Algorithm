package Greedy;

import java.util.*;
import java.io.*;

public class Problem14 {
    static class Point {
	int idx;
	int x;
	int y;

	public Point(int idx, int x, int y) {
	    this.idx = idx;
	    this.x = x;
	    this.y = y;
	}
    }

    static class Edge implements Comparable<Edge> {
	Point v1;
	Point v2;
	double weight;

	public Edge(Point v1, Point v2) {
	    this.v1 = v1;
	    this.v2 = v2;
	    this.weight = getDistance(v1, v2);
	}

	static double getDistance(Point p1, Point p2) {
	    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	@Override
	public int compareTo(Edge o) {
	    return Double.compare(weight, o.weight);
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] nm = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	Point[] points = new Point[nm[0] + 1];
	UnionFind union = new UnionFind(nm[0] + 1);

	for (int i = 1; i <= nm[0]; i++) {
	    int[] infos = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	    points[i] = new Point(i, infos[0], infos[1]);
	}

	List<Edge> edges = new LinkedList<>();

	for (int i = 1; i < points.length; i++) {
	    for (int j = 1; j < points.length; j++) {
		if (i == j)
		    continue;
		edges.add(new Edge(points[i], points[j]));
	    }
	}

	Collections.sort(edges);
	double answer = 0;

	for (int i = 0; i < nm[1]; i++) {
	    int[] infos = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	    union.merge(infos[0], infos[1]);
	}

	for (Edge edge : edges) {
	    int p = union.find(edge.v1.idx);
	    int q = union.find(edge.v2.idx);

	    if (p != q) {
		union.merge(p, q);
		answer += edge.weight;
	    }
	}
	System.out.println(String.format("%.2f", answer));
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
