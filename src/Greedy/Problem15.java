package Greedy;

import java.util.*;
import java.io.*;

public class Problem15 {
    static class Edge implements Comparable<Edge> {
	int[] v1;
	int[] v2;
	int weight;

	public Edge(int[] v1, int[] v2) {
	    this.v1 = v1;
	    this.v2 = v2;
	    this.weight = getCost(v1, v2);
	}

	@Override
	public int compareTo(Edge o) {
	    return Integer.compare(weight, o.weight);
	}
    }

    static int getCost(int[] p1, int[] p2) {
	int cost = Integer.MAX_VALUE;
	for (int i = 1; i < 4; i++) {
	    cost = Integer.min(cost, Math.abs(p1[i] - p2[i]));
	}
	return cost;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	int[][] planets = new int[n][];

	for (int i = 0; i < n; i++) {
	    planets[i] = Arrays.stream((i + " " + br.readLine()).split("\\s")).mapToInt(Integer::parseInt).toArray();
	}

	UnionFind union = new UnionFind(n);

	Queue<Edge> q = new PriorityQueue<>();

	Arrays.sort(planets, (a, b) -> a[1] - b[1]);
	for (int i = 0; i < planets.length - 1; i++) {
	    q.add(new Edge(planets[i], planets[i + 1]));
	}
	Arrays.sort(planets, (a, b) -> a[2] - b[2]);
	for (int i = 0; i < planets.length - 1; i++) {
	    q.add(new Edge(planets[i], planets[i + 1]));
	}
	Arrays.sort(planets, (a, b) -> a[3] - b[3]);
	for (int i = 0; i < planets.length - 1; i++) {
	    q.add(new Edge(planets[i], planets[i + 1]));
	}

	int answer = 0;
	int cnt = 0;
	while (!q.isEmpty()) {
	    int weight = q.peek().weight;
	    int a = q.peek().v1[0];
	    a = union.find(a);
	    int b = q.poll().v2[0];
	    b = union.find(b);

	    if (a != b) {
		union.merge(a, b);
		answer += weight;
		cnt++;
		if (cnt == n - 1) {
		    break;
		}
	    }
	}
	System.out.println(answer);
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

	public int find(int i) {
	    return U[i].parent == i ? i : (U[i].parent = find(U[i].parent));
	}

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
