package Utils;

import java.util.List;

public class UnionFind {

    static class Union {
	class Node {
	    int parent;
	    int depth;

	    public Node(int parent) {
		this.parent = parent;
		this.depth = 0;
	    }
	}

	Node[] U;
	final int n; // 정점
	final int m; // 간선

	public Union(int n, List<int[]> E, List<int[]> F) {
	    this.n = n;
	    this.m = E.size();
	    U = new Node[n];

	    for (int i = 0; i < n; i++)
		U[i] = new Node(i);
	}

	private int find(int i) {
	    int j = i;
	    while (U[j].parent != j)
		j = U[j].parent;
	    return j;
	}

	private void merge(int p, int q) {
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
