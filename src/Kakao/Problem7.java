/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17684
 * 압축 문제
*/
package Kakao;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String msg) {
    	Queue<String> queue = new LinkedList<>(Arrays.stream(msg.split("")).collect(Collectors.toList()));
    	List<Integer> answer = new LinkedList<>();
    	HashMap<String, Integer> dictionary = new HashMap<>();
    	int dictIdx = 1;
    	
    	for(char c = 'A';c<='Z';c++) {
    		dictionary.put(String.valueOf(c), dictIdx++);
    	}
    	
    	String w;
    	String c;
    	while(!queue.isEmpty()) {
    		w = queue.poll();
    		c = queue.peek();
    		while(c != null
    				&&(dictionary.containsKey(w+c))) {
    			w = w + queue.poll();
    			c = queue.peek();
    		}
    		answer.add(dictionary.get(w));
    		dictionary.put(w+c,dictIdx++);
    	}
    	return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}


public class Problem7 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] arr = s.solution("ABABABABABABABAB");
		for(int i:arr) {
			System.out.println(i);
		}
	}
}
