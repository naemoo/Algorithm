package DP;

/*
 * 백준:1904번(01타일)
*/
import java.util.Scanner;

public class Problem6 {
	static int[] d = new int[1000001];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		d[1] = 1;
		d[2] = 2;
		for(int i= 3 ; i <=n ;i++) {
			d[i] = d[i-1] + d[i-2];
			if(d[i] > 15746)
				d[i] %= 15746;
		}
		System.out.println(d[n]%15746);
	}
}
