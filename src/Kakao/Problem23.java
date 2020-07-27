/*
 * 2017 카카오코드 본선
 * https://programmers.co.kr/learn/courses/30/lessons/1835
 * 단체 사진찍기
*/
package Kakao;

public class Problem23 {
    static int answer;
    static int[] visit;
    static String[] map = new String[] { "A", "C", "F", "J", "M", "N", "R", "T" };
    static StringBuilder candidate = new StringBuilder();

    public static int solution(int n, String[] data) {
	answer = 0;
	String[] conditions = new String[n];
	visit = new int[8];
	for (int i = 0; i < n; i++) {
	    conditions[i] = makeRE(data[i]);
	}
	DFS(0, conditions);
	return answer;
    }

    private static void DFS(int d, String[] conditions) {
	if (d == visit.length) {
	    if (isAnswer(candidate.toString(), conditions)) {
		answer += 1;
	    }
	    return;
	}

	for (int i = 0; i < visit.length; i++) {
	    if (visit[i] == 0) {
		candidate.append(map[i]);
		visit[i] = 1;
		DFS(d + 1, conditions);
		visit[i] = 0;
		candidate.deleteCharAt(d);
	    }
	}
    }

    private static boolean isAnswer(String string, String[] conditions) {
	for (String condition : conditions) {
	    if (!string.matches(condition))
		return false;
	}
	return true;
    }

    private static String makeRE(String data) {
	String mid = "";
	switch (data.charAt(3)) {
	case '<':
	    mid = 0 + "," + (Character.getNumericValue(data.charAt(4)) - 1);
	    break;
	case '=':
	    mid = data.substring(4, 5);
	    break;
	case '>':
	    mid = (Character.getNumericValue(data.charAt(4)) + 1) + ",";
	}
	return ".*[" + data.charAt(0) + data.charAt(2) + "].{" + mid + "}[" 
		+ data.charAt(0) + data.charAt(2) + "].*";
    }

    public static void main(String[] args) {
	int ans = solution(2, new String[] { "N~F=0", "R~T>2" });
	System.out.println(ans);
	answer = 0;
	ans = solution(2, new String[] { "M~C<2", "C~M>1]" });
	System.out.println(ans);
    }

}
