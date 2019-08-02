package DP;
/*
 * 백준:1003(피보나치 함수 2)
 * n을 입력했을 시  f(0)과 f(1)이 몇번 주어지나 구하는 문제
*/
import java.util.Scanner;

public class Problem5 {
	static int d[][] = new int[41][2];
	public static void main(String[] args) {
		d[0][0] = 1;
		d[1][1] = 1;
		Scanner s = new Scanner(System.in);
		int test_num = s.nextInt();
		for(int i = 0 ; i < test_num;i++) {
			int num = s.nextInt();
			for(int j = 2;j<=num;j++) {
				d[j][0] = d[j-1][0] + d[j-2][0];
				d[j][1] = d[j-1][1] + d[j-2][1];
			}
			System.out.println(d[num][0] +" " +d[num][1]);
		}
	}
}
