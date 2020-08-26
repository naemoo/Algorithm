/*
 * https://programmers.co.kr/learn/courses/30/lessons/42884
 * 단속 카메라
*/
package Greedy;

import java.util.*;

public class Problem04 {
    public static int solution(int[][] routes) {
	int answer = 0;
	int last = Integer.MIN_VALUE;
	
	Arrays.parallelSort(routes, (r1, r2) -> r1[1] - r2[1]);
	
	for(int[] route: routes) {
	    if(last < route[0]) {
		last = route[1];
		answer++;
	    } 
	}
	System.out.println(answer);
	return answer;
    }

    public static void main(String[] args) {
	solution(new int[][] { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } });
    }
}
