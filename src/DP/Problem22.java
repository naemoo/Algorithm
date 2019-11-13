package DP;
/*
 * Chained Matrix Multiplication
*/

import java.util.Arrays;

public class Problem22 {
	static int[][] M;//최소 행렬 곱 갯수 저장
	static int INF = 1000000;
	static int[][] p;//최소 행렬 곱 갯수 일 때 최적의 k
	//INF 채우기
	public static void fillINF(int[][] arr) {
		for(int i = 1;i<arr.length;i++)
			Arrays.fill(arr[i], INF);
	}
	//배열 출력
	public static void printArray(int[][] arr) {
		for(int i = 1 ; i < arr.length;i++) {
			for(int j = 1;j<arr.length;j++) {
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
	//Chained Matrix Multiplication 구하기
	public static int minmult(int n, int[] d) {
		int j;
		for(int i = 1 ; i <=n;i++) {
			M[i][i] = 0;
		}
		for(int diagonal = 1;diagonal<=n-1;diagonal++) {
			for(int i = 1;i<=n-diagonal;i++) {
				j = i+ diagonal;
				for(int k = i;k<=j-1;k++) {
					int num = M[i][k] + M[k+1][j] + d[i-1]*d[k]*d[j];
					if(M[i][j] > num) {
						M[i][j] = num;
						p[i][j] = k;
					}
				}
			}
		}
		
		return M[1][n];
	}
	
	public static void order(int i, int j) {
		int k;
		if(i==j)
			System.out.print("A"+ i);
		else {
			k = p[i][j];
			System.out.print("(");
			order(i,k);
			order(k+1,j);
			System.out.print(")");
		}
	}
	
	
	public static void main(String[] args) {
		int n = 6;
		//0번 인덱스 더미 값
		M = new int[n+1][n+1];
		p = new int[n+1][n+1];
		int[] d = new int[n+1];
		d[0] = 5;	d[1] = 2;
		d[2] = 3;	d[3] = 4;
		d[4] = 6;	d[5] = 7;
		d[6] = 8;
		fillINF(M);
		minmult(n, d);
		
		System.out.println("1번");
		System.out.println("Algorithm 3.6 Minimum Multiplications");
		printArray(M);
		System.out.println();
		
		System.out.println("3.7 Print Optimal Order");
		order(1,6);
		
		
		System.out.println("3번");
		System.out.println("자작 데이터 입력");
		p = new int[n+1][n+1];
		d[0] = 2;	d[1] = 7;
		d[2] = 3;	d[3] = 2;
		d[4] = 5;	d[5] = 10;
		d[6] = 4;
		fillINF(M);
		minmult(n, d);
		
		printArray(M);
		System.out.println();
		
		System.out.println("자작데이터 Print Optimal Order");
		order(1,6);
	}
}
