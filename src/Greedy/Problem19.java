package Greedy;

import java.util.*;
import java.io.*;

public class Problem19 {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	n = Integer.parseInt(br.readLine());
	Queue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
	map = new int[n][];
	int sum = 0;

	for (int i = 0; i < n; i++) {
	    map[i] = br.readLine().chars().map(e -> {
		if (e == '0')
		    return 0;
		if (Character.isUpperCase(e))
		    return e - 'A' + 27;
		return e - 'a' + 1;
	    }).toArray();

	    for (int j = 0; j < n; j++) {
		q.add(new int[] { i, j, map[i][j] });
	    }
	    sum += Arrays.stream(map[i]).sum();
	}

	int cost = 0;
	int cnt = 0;
	UnionFind unionFind = new UnionFind(n);
	while (!q.isEmpty()) {
	    int a = q.peek()[0];
	    int b = q.peek()[1];
	    int len = q.poll()[2];

	    if (len == 0)
		continue;

	    a = unionFind.find(a);
	    b = unionFind.find(b);

	    if (a != b) {
		unionFind.merge(a, b);
		cost += len;
		cnt++;
	    }
	}
	System.out.println(cnt == n - 1 ? sum - cost : -1);
    }

    static public class UnionFind {

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
