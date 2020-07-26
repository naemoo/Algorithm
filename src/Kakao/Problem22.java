/*
 * 2019 카카오 개발자 겨울 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/64062
 * 징검다리 건너기 
*/
package Kakao;

import java.util.*;

public class Problem22 {
    static int answer = 0;
    
    public static int solution(int[] stones, int k) {
	int min = Arrays.stream(stones).min().getAsInt();
	int max = Arrays.stream(stones).max().getAsInt();
	findMaxAnswer(stones, min, max, k);
	return answer;
    }

    private static void findMaxAnswer(int[] stones, int min, int max, int k) {
	if (min > max)
	    return;
	
	int mid = (min + max) / 2;

	if (isValidStep(stones, mid, k)) {
	    answer = mid;
	    findMaxAnswer(stones, mid + 1, max, k);
	} else {
	    findMaxAnswer(stones, min, mid - 1, k);
	}
    }

    private static boolean isValidStep(int[] stones, int min, int k) {
	int cnt = 0;
	for (int stone : stones) {
	    if (stone < min)
		cnt++;
	    else
		cnt = 0;

	    if (cnt >= k)
		return false;
	}
	return true;
    }

    public static void main(String[] args) {
	int ans = solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 }, 3);
	System.out.println(ans);
	ans = solution(new int[] { 1, 1, 1, 1 }, 4);
	System.out.println(ans);
    }
}
