/*
 * Summer/Winter Coding(~2018)
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 * 소수 만들기
*/
package Kakao;

import java.util.*;

public class Problem27 {
    static int answer = 0;
    static int sum = 0;
    static int[] map;
    static int[] visit;
    static HashMap<Integer, Integer> dp = new HashMap<>();

    public static int solution(int[] nums) {
	map = nums;
	visit = new int[map.length];
	makePrime(3000);
	DFS(0, 0);
	return answer;
    }

    static void DFS(int next, int d) {
	if (d == 3) {
	    if (isPrime(sum)) {
		answer++;
	    }
	    return;
	}

	for (int i = next; i < map.length; i++) {
	    if (visit[i] == 0) {
		visit[i] = 1;
		sum += map[i];
		DFS(i + 1, d + 1);
		visit[i] = 0;
		sum -= map[i];
	    }
	}
    }

    static boolean isPrime(int num) {
	return dp.get(num) == 1 ? true : false;
    }

    static void makePrime(int num) {
	for (int i = 2; i <= num; i++) {
	    if (dp.containsKey(i))
		continue;
	    else
		dp.put(i, 1);
	    for (int j = i + i; j <= num; j += i) {
		dp.put(j, 0);
	    }
	}
    }

    public static void main(String[] args) {
	int answer = solution(new int[] { 1, 2, 3, 4 });
	System.out.println(answer);
	answer = solution(new int[] { 1, 2, 7, 6, 4 });
	System.out.println(answer);
    }
}
