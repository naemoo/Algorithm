/*
 * https://www.acmicpc.net/problem/1717
 * 집합의 표현(UNION & FIND) 
*/
package Greedy;

import java.util.*;
import java.io.*;

public class Problem11 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	int n = Integer.parseInt(st.nextToken());
	int m = Integer.parseInt(st.nextToken());
	UnionFind union = new UnionFind(n);
	StringBuilder sb = new StringBuilder();

	for (int i = 0; i < m; i++) {
	    int[] infos = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

	    if (infos[0] == 0) {
		union.merge(infos[1], infos[2]);
	    } else {
		if (union.find(infos[1]) == union.find(infos[2]))
		    sb.append("YES\n");
		else
		    sb.append("NO\n");
	    }
	}
	System.out.println(sb);
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
	    U = new Node[n + 1];

	    for (int i = 0; i <= n; i++)
		U[i] = new Node(i);
	}

	private int find(int i) {
	    return U[i].parent == i ? i : (U[i].parent = find(U[i].parent));
	}

	private void merge(int p, int q) {
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
