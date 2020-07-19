/*
 * 2019 카카오 개발자 겨울 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 * 튜플
*/
package Kakao;

import java.util.*;
import java.util.regex.*;

public class Problem15 {
	public static int[] solution(String s) {
		HashMap<String,Integer> map = new HashMap<>();
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(s);
		while(matcher.find()) 
			map.put(matcher.group(),map.getOrDefault(matcher.group(), 0)+1);
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.map(set->set.getKey()).mapToInt(Integer::parseInt).toArray();
	}
	 
	public static void main(String[] args) {
		int[] ans = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		System.out.println(Arrays.toString(ans));
	}}
