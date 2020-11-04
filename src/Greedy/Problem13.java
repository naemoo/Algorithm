/*
 * 백준 1197 : 최소 스패닝 트리 (Kruskal) 
*/
package Greedy;

import java.util.*;
import java.io.*;

public class Problem13 {
    static int n;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int[] ve = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
	n = ve[0];

	for (int i = 0; i < ve[1]; i++) {
	    q.add(Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray());
	}
	System.out.println(kruskal(q));
    }

    private static int kruskal(Queue<int[]> q) {
	UnionFind union = new UnionFind(n);
	int answer = 0;

	while (!q.isEmpty()) {
	    int[] cur = q.poll();
	    int a = cur[0] - 1;
	    int b = cur[1] - 1;
	    if (union.find(a) != union.find(b)) {
		answer += cur[2];
		union.merge(a, b);
	    }
	}
	return answer;
    }
}

class UnionFind {

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
