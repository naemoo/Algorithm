package Greedy;

/*
 * 백준 : 동전0(11047번, 그리디 알고리즘)
 * https://www.acmicpc.net/problem/11047
*/
import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int total = 0;
		int[] arr = new int[n];
		for(int i = 0 ; i < n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for(int i = arr.length-1;i>=0;i--) {
			if(k>=arr[i]) {
				total += (k/arr[i]);
				k-= (k/arr[i])*arr[i];
			}
			if(k == 0)
				break;
		}		
		System.out.println(total);
	}
}
