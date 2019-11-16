package DP;

/*
 * Optimal Binary Search Trees
*/

import java.util.*;
import java.util.stream.*;

public class Problem23 {
	static double minavg;
	static double INF = 1000000;
	
	public static void fillINF(double[][] A) {
		for(int i = 0;i<A.length;i++) {
			Arrays.fill(A[i], INF);
		}
	}
	
	public static void printArrays(double[][] A) {
		for(int i = 1 ; i < A.length;i++) {
			for(int j = 0 ; j <A[0].length;j++)
				System.out.print(A[i][j]+" ");
			System.out.println();
		}
	}
	public static void printArrays(int[][] R) {
		for(int i = 1 ; i < R.length;i++) {
			for(int j = 0 ; j <R[0].length;j++)
				System.out.print(R[i][j]+" ");
			System.out.println();
		}
	}
	
	public static void optSearchTree(int n,double[] p,int[][] R) {
		int i,j,k,diagonal;
		double[][] A = new double[n+2][n+1];//행 1~n+1 인덱스 열 0~n인덱스 사용
		fillINF(A);
		
		for(i = 1; i<=n;i++) {
			A[i][i-1] = 0;//루트 i의 왼쪽 트리 루트가 없을 때
			A[i][i] = p[i];//노드가 1개이고 루트가 i 일때(자기 자신 밖에 없을 때)
			R[i][i] = i;
			R[i][i-1] = 0;
		}
		A[n+1][n] = 0;//가장 큰 수가 루트 노드일때 오른쪽 서브 트리 없음
		R[n+1][n] = 0;
		
		for(diagonal = 1; diagonal<=n-1;diagonal++) {
			for(i =1 ;i<=n-diagonal;i++) {
				j = i + diagonal;
				double sumP = IntStream.range(i, j+1).mapToDouble(idx -> p[idx]).sum();//시그마(i~j)
				for(k = i;k<=j;k++) {
					double tmp = A[i][k-1] + A[k+1][j] + sumP;
					if(A[i][j] > tmp) {
						A[i][j] = tmp;
						R[i][j] = k;
					}
				}
			}
		}
		minavg = A[1][n];
		printArrays(A);
		System.out.println();
		printArrays(R);
	}
	
	public static void main(String[] args) {
		System.out.println("교재 Algorithm 3.9 Optimal Binary Search Tree");
		int n = 4;
		double [] p = new double[] {0,3.0/8,3.0/8,1.0/8,1.0/8};
		int[][] R = new int[n+2][n+1];
		
		optSearchTree(n, p, R);//minavg 전역으로 선언
		System.out.println("M[1][4] : " + minavg);
		System.out.println();
		
		System.out.println("자작 데이터");
		p = new double[] {0,0.1,0.4,0.2,0.3};
		R = new int[n+2][n+1];
		
		optSearchTree(n, p, R);//minavg 전역으로 선언
		System.out.println("M[1][4] : "+ minavg);
	}
}
