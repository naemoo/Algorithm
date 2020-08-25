/*
 * https://programmers.co.kr/learn/courses/30/lessons/43236
*/
package Search;

import java.util.*;

public class Problem22 {
    public static int solution(int distance, int[] rocks, int n) {
	Arrays.parallelSort(rocks);
	int answer = 0;
	int left = 0;
	int right = distance;
	int prev = 0;
	int cnt = 0;

	while (left < right) {
	    int mid = (left + right) / 2;

	    for (int end = 0; end < rocks.length; end++) {
		if (mid > rocks[end] - prev)
		    cnt++;
		else
		    prev = rocks[end];
	    }

	    if (distance - prev < mid)
		cnt++;

	    if (cnt <= n) {
		answer = Math.max(answer, mid);
		left = mid + 1;
	    } else {
		right = mid;
	    }
	    
	    cnt = 0;
	    prev = 0;
	}
	System.out.println(answer);
	return answer;
    }

    public static void main(String[] args) {
	solution(25, new int[] { 2, 14, 11, 21, 17 }, 2);
    }
}
