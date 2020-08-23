/*
 * https://www.acmicpc.net/problem/1182
 * 부분 집합의 합(부분 집합 문제)
 */

package DataStructure;

import java.util.*;
import java.io.*;

public class Problem02 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int sum = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[] arr = new int[num];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ;i<num;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i< 1<<num;i++) {
			int subsetSum = 0;
			for(int subset = 0;subset < num;subset++) {
				if((i&1<<subset) != 0) {
					subsetSum += arr[subset];
				}
			}
			if(subsetSum == sum)
				answer++;
		}
		
		System.out.println(answer);
	}
}
