/*
 * https://programmers.co.kr/learn/courses/30/lessons/42861
 * 섬 연결하기
*/
package Greedy;

import java.util.*;

public class Problem05 {

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

	    kruskal(E, F);
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

	void kruskal(List<int[]> E, List<int[]> F) {
	    int p, q;// 속한 집합
	    int idx = 0;
	    while (F.size() < n -1) {
		int[] edge = E.get(idx++);
		p = find(edge[0]);
		q = find(edge[1]);
		if (p != q) {
		    merge(p, q);
		    F.add(edge);
		}
	    }
	}

    }

    public static int solution(int n, int[][] costs) {
	Arrays.sort(costs, (i1, i2) -> i1[2] - i2[2]);
	List<int[]> E = new LinkedList<>(Arrays.asList(costs));
	List<int[]> F = new LinkedList<>();
	Union u = new Union(n, E, F);
	return F.stream().mapToInt(i -> i[2]).sum();
    }

    public static void main(String[] args) {
	int ans = solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } });
	System.out.println(ans);
    }

}
