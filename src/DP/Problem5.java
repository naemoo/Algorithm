package DP;
/*
 * ����:1003(�Ǻ���ġ �Լ� 2)
 * n�� �Է����� ��  f(0)�� f(1)�� ��� �־����� ���ϴ� ����
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
