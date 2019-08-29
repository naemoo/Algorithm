package Search;

/*
 * 백준 : 로또(DFS탐색)
 * https://www.acmicpc.net/problem/6603
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem12{
	static Queue<Integer> q = new LinkedList<>();
	static int n;
	static int[] arr;
	static int[] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		while(n !=0) {
			arr = new int[n];
			visit = new int[n];
			for(int i = 0 ; i < n ;i++)
				arr[i] = s.nextInt();
			DFS(0,0);
			System.out.println();
			n = s.nextInt();
		}
	}
	public static void DFS(int start,int depth) {
		if(depth == 6) {
			for(int i = 0 ; i < n;i++)
				if(visit[i] == 1)
					System.out.print(arr[i] + " ");
			System.out.println();
			return;
		}
		else
		for(int i = start; i< n;i++) {
			if(visit[i] == 0) {
				visit[i] = 1;
				DFS(i,depth+1);
				visit[i] = 0;
			}
		}
	}
}
