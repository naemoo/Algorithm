package BackTracking;
/*
 * 백준(백트래킹) : N과 M(2) (15650번)
 * https://www.acmicpc.net/problem/15650 
*/
import java.util.Scanner;

public class Problem1 {
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int n = s.nextInt();
		int r = s.nextInt();
		arr = new int[r];
		per(1,n,r,0);
		System.out.print(sb);
	}
	public static void per(int start,int n,int r,int depth) {
		if(depth == r) {
			for(int i= 0 ; i < r;i++)
				sb.append(arr[i] + " ");
			sb.append('\n');
			return;
		}
		for(int i = start;i<=n;i++) {
			arr[depth] = i;
			per(i,n,r,depth+1);
		}
	}
}


