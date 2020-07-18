/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60058
 * 괄호 변환
*/

package Kakao;

import java.util.*;

public class Problem14 {
	public static String solution(String p) {
		String[] uv = null;
		if(!isProperBracket(p)) {
			uv = seperateBalance(p);
			if(isProperBracket(uv[0])) {
				uv[1] = solution(uv[1]);
			}
			else {
				uv[0] = uv[0].substring(1,uv[0].length()-1);
				StringBuilder newU = new StringBuilder();
				StringBuilder newV = new StringBuilder();
				
				newU.append('(');
				newU.append(solution(uv[1]));
				newU.append(')');
				
				for(char c: uv[0].toCharArray()) {
					if(c== '(')
						newV.append(')');
					else
						newV.append('(');
				}
				
				uv[0] = newU.toString();
				uv[1] = newV.toString();
			}
		}
		else {
			return p;
		}
		return uv[0] + uv[1];
	}

	private static String[] seperateBalance(String p) {
		String[] uv = new String[2];
		int leftCnt = 0;
		int rigthCnt = 0;
		for (int i = 0; i < p.length(); i++) {
			if(p.charAt(i) == '(') {
				leftCnt++;
			}
			else {
				rigthCnt++;
			}
			if(leftCnt == rigthCnt) {
				uv[0] = p.substring(0,i+1);
				uv[1] = p.substring(i+1);
				break;
			}
		}
		return uv;
	}

	private static boolean isProperBracket(String p) {
		StringBuilder sb = new StringBuilder(p);
		for (int i = 0; i < p.length()/2; i++) {
			int deleteIdx = sb.indexOf("()");
			if(deleteIdx != -1) {
				sb.delete(deleteIdx, deleteIdx+2);
			}
		}
		return sb.length() == 0 ? true : false;
	}

	public static void main(String[] args) {
		String answer = solution("(()())()");
		System.out.println(answer);
		answer = solution(")(");
		System.out.println(answer);
		answer = solution(")()()()(");
		System.out.println(answer);
	}
}
