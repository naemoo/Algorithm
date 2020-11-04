package Utils;

/*
 * Union & Find -> Path Compression + Rank
*/
public class UnionFind {

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
