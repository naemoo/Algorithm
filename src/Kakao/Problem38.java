/*
 * 2019 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/challenges?selected_part_id=12286
 * 실패율 
*/
package Kakao;

import java.util.stream.*;

public class Problem38 {
    public static int[] solution(int N, int[] stages) {
	int[] assessCnt = new int[N + 1];
	int[] failCnt = new int[N + 1];
	double[] failuers = new double[N + 1];

	for (int stage : stages) {
	    updateAssess(assessCnt, stage);
	    if (stage != N + 1)
		failCnt[stage] += 1;
	}

	for (int i = 1; i <= N; i++) {
	    if (assessCnt[i] != 0) {
		failuers[i] = failCnt[i] / (double) assessCnt[i];
	    }
	}

	return IntStream.range(1, N + 1).mapToObj(Integer::valueOf)
		.sorted((a, b) -> Double.compare(failuers[b], failuers[a])).mapToInt(Integer::intValue).toArray();
    }

    private static void updateAssess(int[] assessCnt, int stage) {
	for (int i = 1; i <= stage; i++) {
	    if (i < assessCnt.length)
		assessCnt[i]++;
	}
    }

    public static void main(String[] args) {
	solution(5, new int[] { 2, 1, 2, 6, 2, 4, 3, 3 });
	solution(4, new int[] { 4, 4, 4, 4, 4 });
    }
}
