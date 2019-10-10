package DivideAndConquer;

import java.util.Arrays;

//기본 퀵소트
/* 시간 복잡도
 * 최악의 경우 : 정렬된 데이터 -> O(n^2)
 * 평균적 경우 : O(nlogn)
 */

public class Problem4 {
	static int[] S;
	static int pivotpoint = 1;
	public static void swap(int i1,int i2) {
		int tmp = S[i1];
		S[i1] = S[i2];
		S[i2] = tmp;
	}
	
	public static void quicksort(int low, int high) {
		if(high > low) {
			partition(low, high);
			quicksort(low, pivotpoint-1);
			quicksort(pivotpoint+1,high);
		}
	}
	public static void partition(int low, int high) {
		int i,j;
		int pivotItem= S[low];
		j = low;
		for(i = low +1;i<=high;i++) {
			if(S[i]< pivotItem) {
				j++;
				swap(i,j);
			}
		}
		pivotpoint = j;
		swap(low,pivotpoint);
	}
	//배열 S 인덱스 0값 = 더미 값
	public static void main(String[] args) {
		S = new int[] {111,4,7,6,2,1,5,3};
		quicksort(1, S.length-1);
		for(int i = 1;i<=S.length-1;i++)
			System.out.print(S[i]+ " ");
	}
}
