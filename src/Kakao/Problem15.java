/*
 * 2019 카카오 개발자 겨울 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 * 튜플
*/
package Kakao;

import java.util.*;
import java.util.stream.*;

public class Problem15 {
	 public static int[] solution(String s) {
		 String[] subsets = s.substring(2,s.length()-2).split("\\},\\{");
		 HashMap<String,Integer> elementDict = new HashMap<>();
		 
		 for(String subset:subsets) 
			 for(String element: subset.split(",")) 
				 elementDict.put(element,elementDict.getOrDefault(element, 0)+1);
			 
	     return elementDict.entrySet().stream()
	    		 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	    		 .map(set->set.getKey()).mapToInt(i->Integer.valueOf(i)).toArray();
	    }
	 
	public static void main(String[] args) {
		int[] ans = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
		System.out.println(Arrays.toString(ans));
	}
}
