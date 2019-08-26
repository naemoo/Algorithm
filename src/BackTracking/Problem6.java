package BackTracking;

/*
 * 백준 : 연산자 끼워넣기(14888번)
 * https://www.acmicpc.net/problem/14888 
*/

import java.io.*;
import java.util.StringTokenizer;

public class Problem6 {
	static int n;
	static int[] num;
	static int[] op = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <n;i++)
			num[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<4;i++)
			op[i] = Integer.parseInt(st.nextToken());
		calculate(num[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void calculate(int sum, int d) {
		if(d == n) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		for(int i = 0 ; i < 4;i++) {
			if(op[i] != 0) {
				switch(i) {
				case 0:
					op[i] -= 1;
					calculate(sum+num[d], d+1);
					op[i] += 1;
					break;
				case 1:
					op[i] -= 1;
					calculate(sum-num[d], d+1);
					op[i] += 1;
					break;
				case 2:
					op[i] -= 1;
					calculate(sum*num[d], d+1);
					op[i] += 1;
					break;
				case 3:
					op[i] -= 1;
					calculate(sum/num[d], d+1);
					op[i] += 1;
				}
			}
		}
	}
}
