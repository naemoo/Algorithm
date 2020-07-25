/*
 * 2019 카카오 개발자 겨울 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/64064
 * 불량 사용자
*/
package Kakao;

import java.util.*;

public class Problem21 {
    static List<String> banList = new LinkedList<>();
    static HashSet<List<String>> banCase = new HashSet<>();
    static String[] banIds;
    static String[] users;
    static int[] visit;

    public static int solution(String[] user_id, String[] banned_id) {
	banIds = Arrays.stream(banned_id).map(str -> str.replaceAll("\\*", ".")).toArray(String[]::new);
	users = user_id;
	visit = new int[users.length];

	DFS(0);
	return banCase.size();
    }

    private static void DFS(int d) {
	if (d == banIds.length) {
	    List<String> newCase = new LinkedList<>(banList);
	    Collections.sort(newCase);
	    banCase.add(newCase);
	    System.out.println(newCase);
	    return;
	}

	for (int i = 0; i < users.length; i++) {
	    if (visit[i] == 0 && users[i].matches(banIds[d])) {
		banList.add(users[i]);
		visit[i] = 1;
		DFS(d + 1);
		banList.remove(users[i]);
		visit[i] = 0;
	    }
	}
    }

    public static void main(String[] args) {
	solution(new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" },
		new String[] { "fr*d*", "*rodo", "******", "******" });
    }
}
