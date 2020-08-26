package Greedy;

import java.util.*;

/*
 * Kruskal's Algorithm 
*/



class Node{
	int parent;
	int depth;
	@Override
	public String toString() {
		return "parent : "+ parent +" depth : "+depth;
	}
}

class KruskalDS2{
	static Node[] U;
	
	//생성자 - 기초 셋팅
	public KruskalDS2(int n,int m,List<Edge> E, List<Edge> F) {//n : Vertex 갯수 m: Edge갯수 
		U = new Node[n+1];
		for(int i=1;i<=n;i++)
			U[i] = new Node();
		kruskal(n, m, E, F);
	}

	static void makeset(int i) {
		U[i].parent = i;
		U[i].depth = 0;
	}
	
	static int find(int i) {
		int j = i;
		while(U[j].parent!=j)
			j=U[j].parent;
		return j;
	}
	
	static void merge(int p,int q) {
		if(U[p].depth == U[q].depth) {
			U[p].depth += 1;
			U[q].parent = p;
		}
		else if(U[p].depth<U[q].depth)
			U[p].parent = q;
		else
			U[q].parent = p;
	}
	
	static boolean equal(int p, int q) {
		if(p==q)
			return true;
		else
			return false;
	}
	
	//n개의 서로소 부분집합을 초기화
	static void initial(int n) {
		for(int i = 1;i<=n;i++)
			makeset(i);
	}
	
	static void kruskal(int n,int m,List<Edge> E,List<Edge> F) {
		int i,j;//i,j = vertex ,w= weight
		int p,q;//p,q = root Vertex
		Edge e;
		Collections.sort(E);
		initial(n);
		int num = 0;
		Queue<Edge> que = new LinkedList<Edge>(E);
		while(num < n-1) {
			e = que.poll();  
			i = e.i;
			j = e.j;
			p = find(i);
			q = find(j);
			if(!equal(p, q)) {
				merge(p,q);
				F.add(e);
				num++;
			}
		}
		for(int k = 1 ;k<U.length;k++) {
			System.out.println(U[k]);
		}
	}
}

public class Problem02 {
	public static void main(String[] args) {
		//Algorithm 4.2 Kruskal's Algorithm과 Appendix C의 Disjoint Set Data Structure II
		int n = 5;
		int m = 7;
		List<Edge> E = new LinkedList<Edge>();
		List<Edge> F = new LinkedList<Edge>();
		//Edge, Vertex 설정
		E.add(new Edge(1,2,1));
		E.add(new Edge(1,3,3));
		E.add(new Edge(2,3,3));
		E.add(new Edge(2,4,6));
		E.add(new Edge(3,4,4));
		E.add(new Edge(3,5,2));
		E.add(new Edge(4,5,5));
		KruskalDS2 k = new KruskalDS2(n, m, E, F);
		System.out.println("Algorithm 4.2 Kruskal's Algorithm과 Appendix C의 Disjoint Set Data Structure II");
		for(int i = 0 ;i<F.size();i++) {
			System.out.println(F.get(i));
		}
		System.out.println();
		
		//자작 데이터
		/*
		n = 6;
		m = 9;
		E = new LinkedList<Edge>();
		F = new LinkedList<Edge>();
		//Edge, Vertex 설정
		E.add(new Edge(1,2,5));
		E.add(new Edge(1,3,4));
		E.add(new Edge(2,3,2));
		E.add(new Edge(2,4,7));
		E.add(new Edge(3,4,6));
		E.add(new Edge(3,5,11));
		E.add(new Edge(4,6,8));
		E.add(new Edge(4,5,3));
		E.add(new Edge(5,6,8));
		k = new KruskalDS2(n, m, E, F);
		System.out.println("자작 데이터");
		for(int i = 0 ;i<F.size();i++) {
			System.out.println(F.get(i));
		}
		*/
	}
}



