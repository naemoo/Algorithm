package DivideAndConquer;

// 병합정렬 구현

import java.util.Arrays;

public class Problem3 {
	public static void Merge(int h, int m,int[] arrL, int[] arrR,int[] s) {
		int l = 0;
		int r = 0;
		int k = 0;
		
		while(l<h && r<m) {
			if(arrL[l] <= arrR[r]) {
				s[k] = arrL[l];
				l++;
			}
			else {
				s[k] = arrR[r];
				r++;
			}
			k++;
		}
		if(l > h) 
			for(int i = r;i <m;i++)
				s[k++] = arrR[i];
		else
			for(int i = l;i<h;i++)
				s[k++] = arrL[i];
	}
	
	public static void MergeSort(int n ,int[] arr) {
		if(n >1) {
			int h = n/2;
			int m = n-h;
			
			int[] arrL = Arrays.copyOfRange(arr, 0, h); 
			int[] arrR = Arrays.copyOfRange(arr, h, n);
			
			MergeSort(h,arrL);
			MergeSort(m,arrR);
			
			Merge(h, m, arrL, arrR, arr);
		}
	}
	
	public static void main(String[] args) {
		int[] arr =new int[]{10,8,9,3,6,5,4,7,2,1};
		MergeSort(10,arr);
		for(int i = 0 ; i <arr.length;i++)
			System.out.print(arr[i]+" ");
	}
}
