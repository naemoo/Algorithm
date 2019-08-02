package BackTracking;

import java.util.Arrays;
import java.util.Scanner;

public class Problem4 {
	static int concnt= 0;
	static int vowcnt = 0;
	static String vowel = "aeiou";
	static String[] arr;
	static int[] visit;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int r = s.nextInt();
		int n = s.nextInt();
		s.nextLine();
		visit = new int[n];
		arr = Arrays.stream(s.nextLine().split(" ")).sorted().toArray(String[]::new);
		DFS(0,r,n,0);
	}
	public static void DFS(int start,int r, int n, int d) {
		if(d == r) {
			if(concnt>=2 && vowcnt >= 1) {
				for(int i = 0 ; i < n; i++) {
					if(visit[i] == 1) 
						System.out.print(arr[i]);
				}
				System.out.println();
				return;
			}
			return;
		}
		
		for(int i = start;i<n;i++) {
			if(visit[i] == 0) {
				if(vowel.contains(arr[i]))
					vowcnt++;
				else 
					concnt++;
				visit[i] = 1;
				DFS(i+1,r,n,d+1);
				if(vowel.contains(arr[i]))
					vowcnt--;
				else 
					concnt--;
				visit[i] = 0;
			}
		}
	}
}
