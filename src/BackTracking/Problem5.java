package BackTracking;

/*
 * 백준 : 좋은수열(2661번)
 * https://www.acmicpc.net/problem/2661 
*/

import java.util.Scanner;

public class Problem5 {
	static int n;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		per(0);
	}
	public static boolean isRepeat(int d,int num) {
		int len = d+1;
		int start = d;
		if(len == 1)
			return true;
		for(int i = 1 ; i <=len/2;i++) {
			if(sb.substring(start-i,len-i).equals(sb.substring(start,len)))
				return false;
			start--;
		}
		return true;
	}
	public static void per(int d) {
		if(d == n) {
			System.out.println(sb);
			System.exit(0);
		}
		for(int i = 1;i<=3;i++) {
			sb.append(i);
			if(isRepeat(d,i)) 
				per(d+1);
			sb.delete(d, d+1);
		}
	}
}
