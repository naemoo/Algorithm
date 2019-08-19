package DP;
/*
 *백준(1463번) : 1로 만들기(DP)
 *https://www.acmicpc.net/problem/1463
*/
import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] d= new int[n+1];
		for(int i = 2;i<=n;i++) {
			if(i%3 == 0) 
				d[i] = Math.min(d[i-1], d[i/3]) + 1;
			else if(i%2 == 0)
				d[i] = Math.min(d[i-1], d[i/2]) + 1;
			else
				d[i] = d[i-1] +1;
		}
		System.out.println(d[n]);
	}
}
