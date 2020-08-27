/*
 * 2020 카카오 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/67258
 * 보석 쇼핑(투포인터)
*/
package Kakao;

import java.util.*;

public class Problem35 {

    public static int[] solution(String[] gems) {
	int[] answer = new int[2];
	HashMap<String, Integer> kinds = getKinds(gems);
	int[] visit = new int[kinds.size()];
	int end = 0;
	int min = Integer.MAX_VALUE;

	for (int start = 0; start < gems.length; start++) {
	    while (!canVisit(visit) && end < gems.length) {
		visit[kinds.get(gems[end])] += 1;
		end++;
	    }

	    if (canVisit(visit) && min > end - start + 1) {
		min = end - start + 1;
		answer[0] = start + 1;
		answer[1] = end;
	    }

	    visit[kinds.get(gems[start])] -= 1;
	}
	return answer;
    }
    
    private static HashMap<String, Integer> getKinds(String[] gems) {
	HashMap<String, Integer> kinds = new HashMap<>();
	for (String gem : gems) {
	    kinds.put(gem, kinds.getOrDefault(gem, 0) + 1);
	}

	int pk = 0;
	for (String key : kinds.keySet()) {
	    kinds.put(key, pk++);
	}

	return kinds;
    }

    private static boolean canVisit(int[] visit) {
	for (int i = 0; i < visit.length; i++) {
	    if (visit[i] == 0)
		return false;
	}
	return true;
    }

    public static void main(String[] args) {
	solution(new String[] { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" });
    }

}
