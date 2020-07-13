/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17687
 * n진수 게임
*/

package Kakao;

import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
    	List<String> list = new LinkedList<>();
    	list.add("0");
    	int num = 1;
    	while(list.size() < t *m) {
    		String radixConverted = convertToRadix(num,n);
    		for(String str:radixConverted.split("")) {
    			if(list.size() < t*m)
    				list.add(str);
    		}
    		num++;
    	}
    	
    	String ans = 
    			IntStream.range(0, t*m).filter(i->i%m==(p-1))
    			.mapToObj(i->list.get(i)).reduce((s1,s2)->s1.concat(s2)).orElse(null);
    	return ans;
    }

	private String convertToRadix(int num, int n) {
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			if(num%n >=10) {
				sb.append(Integer.toHexString(num%n).toUpperCase());
			}
			else
				sb.append(num%n);
			num = num / n;
		}
		return sb.reverse().toString();
	}
}

public class Problem09 {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(2, 4, 2, 1);
		s.solution(16, 16, 2, 1);
	}
}
