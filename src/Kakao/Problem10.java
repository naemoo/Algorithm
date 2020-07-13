/*
 * 2020 카카오 인턴쉽	
 * https://programmers.co.kr/learn/courses/30/lessons/67257
 * 수식 최대화
*/
package Kakao;

import java.util.*;

public class Problem10 {
	
	
	public static long solution(String expression) {
		Long max = Long.MIN_VALUE;
		
		for(int i = 0;i<6;i++) {
			HashMap<Character,Integer> oprTable = setOperatorPriority(i);
			
			Stack<Long> numStack = new Stack<>();
			Stack<Character> oprStack = new Stack<>();

			StringBuilder sb = new StringBuilder();
			Long answer = 0l;
			
			for(char c: expression.toCharArray()) {
				if(Character.isDigit(c)) {
					sb.append(c);
				}
				else {
					numStack.add(Long.parseLong(sb.toString()));
					sb = new StringBuilder();
					if(oprStack.isEmpty()) {
						oprStack.add(c);
					}
					else {
						char preOpr = oprStack.peek();
						if(oprTable.get(c) >= oprTable.get(preOpr)) {
							while(oprTable.get(c) >= oprTable.get(preOpr)) {
								if(preOpr == '*') {
									numStack.add(numStack.pop()
											*numStack.pop());
								}
								else if(preOpr == '+') {
									numStack.add(numStack.pop()
											+numStack.pop());
								}
								else {
									numStack.add((-1*numStack.pop())
											+numStack.pop());
								}
								oprStack.pop();
								if(oprStack.size() != 0)
									preOpr = oprStack.peek();
								else
									break;
							}
							oprStack.add(c);
						}
						else {
							oprStack.add(c);
						}
					}
				}
			}
			numStack.add(Long.parseLong(sb.toString()));
			while(!numStack.isEmpty()) {
				char opr = oprStack.pop();
				if(opr == '*') {
					numStack.add(numStack.pop()
							*numStack.pop());
				}
				else if(opr == '+') {
					numStack.add(numStack.pop()
							+numStack.pop());
				}
				else {
					numStack.add((-1*numStack.pop())
							+numStack.pop());
				}
				
				if(numStack.size()==1) {
					answer = numStack.pop();
				}
			}
			
			max = Math.max(max, Math.abs(answer));
		}
		System.out.println(max);
		return max;
    }


	private static HashMap<Character, Integer> setOperatorPriority(int num) {
		HashMap<Character,Integer> oprTable = new HashMap<>();
		if(num < 3) {
			oprTable.put('*', num % 3);
			oprTable.put('+', (num+1) % 3);
			oprTable.put('-', (num+2) % 3);
		}
		else {
			oprTable.put('*', num % 3);
			oprTable.put('+', (num+2) % 3);
			oprTable.put('-', (num+1) % 3);
		}
		return oprTable;
	}

	public static void main(String[] args) {
		solution("100-200*300-500+20");
		solution("50*6-3*2");
	}
}
