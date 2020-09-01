/*
 * https://programmers.co.kr/learn/courses/30/lessons/42885
 * 구명보트
*/
package Greedy;

import java.util.*;

public class Problem06 {

    public static int solution(int[] people, int limit) {
	int answer = 0;
	int passenger = 0;
	int first = 0;
	int last = people.length - 1;
	Arrays.parallelSort(people);

	while (passenger < people.length) {
	    if (last - first > 0 && people[first] + people[last] > limit) {
		last--;
		passenger += 1;
	    } else {
		first++;
		last--;
		passenger += 2;
	    }
	    answer++;
	}
	return answer;
    }

    public static void main(String[] args) {
	solution(new int[] { 70, 80, 50 }, 100);
    }
}
