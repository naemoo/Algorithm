/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17681
 * 비밀지도
*/
package Kakao;

public class Problem39 {

    public static String[] solution(int n, int[] arr1, int[] arr2) {
	int[] map = new int[n];

	for (int i = 0; i < n; i++) {
	    map[i] = arr1[i] | arr2[i];
	}
	
	String[] answer = new String[n];
	
	for (int i = 0; i < answer.length; i++) {
	    answer[i] = decodeMap(map[i],n);
	}
	return answer;
    }

    private static String decodeMap(int code, int n) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < n; i++) {
	    sb.append((code & 1) == 1 ? "#" : " ");
	    code = (code >> 1);
	}
	return sb.reverse().toString();
    }

    public static void main(String[] args) {
	solution(5, new int[] { 9, 20, 28, 18, 11 }, new int[] { 30, 1, 21, 17, 28 });
    }

}
