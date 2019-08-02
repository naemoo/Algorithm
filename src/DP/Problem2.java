package DP;
/*
 *백준:2579번(계단 오르기) 
*/
import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		int[] d;
		int[] stairs;
		int n;
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		stairs = new int[n];
		d = new int[n];
		for(int i = 0 ; i<n;i++)
			stairs[i] = s.nextInt();
		d[0] = stairs[0];
		d[1] = stairs[1] + d[0];
		d[2] = Math.max(stairs[0] + stairs[2],stairs[1]+stairs[2]);
		for(int i = 3; i< n;i++) {
			d[i] = Math.max(stairs[i] + stairs[i-1] + d[i-3], stairs[i] + d[i-2]);
		}
		System.out.print(d[n-1]);
	}
}
