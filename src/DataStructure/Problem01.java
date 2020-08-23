package DataStructure;

/*
 * https://programmers.co.kr/learn/courses/30/lessons/12973
 * Prgrammers 짝지어 제거하기 
 */

import java.util.*;
import java.util.stream.Stream;

public class Problem01 {
	
    public static int solution(String s)
    {	
    	Stack<Character> stack = new Stack<>();
    	for(char c:s.toCharArray()) {
    		if(stack.isEmpty()) {
    			stack.push(c);
    		}
    		else {
    			char tmp = stack.pop();
    			if(tmp != c) {
    				stack.push(tmp);
    				stack.push(c);
    			}
    		}
    	}
    	return (int) Stream.of(stack).filter(st->st.isEmpty()).count();
    }
	
	public static void main(String[] args) {
		System.out.println(solution("baabaa"));
		System.out.println(solution("cdcd"));
	}
}
