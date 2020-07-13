package Kakao;

import java.util.*;

/*
 * 2019 KAKAO BLIND RECRUITMENT
 * 오픝 채팅방 문제
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 */

public class Problem01 {
	static public String[] solution(String[] record) {
		HashMap<String, String> user_map = new HashMap<>();
		List<String> ans = new LinkedList<>();
		for(String cmd:record) {
			String[] tok = cmd.split(" ");
			if(tok[0].equals("Enter")||tok[0].equals("Change")) {
				user_map.put(tok[1], tok[2]);
			}
		}
		for(String cmd:record) {
			String[] tok = cmd.split(" ");
			if(tok[0].equals("Enter")) {
				ans.add(user_map.get(tok[1])+"님이 들어왔습니다.");
			}
			else if(tok[0].equals("Leave")) {
				ans.add(user_map.get(tok[1])+"님이 나갔습니다.");
			}
		}
		return ans.toArray(String[]::new);
    }
	
	public static void main(String[] args) {
		String[] record = new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] strs = solution(record);
		for(String str:strs) {
			System.out.println(str);
		}
	}
}
