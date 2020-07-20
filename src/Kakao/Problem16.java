/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17677
 * 뉴스 클러스터링 
*/
package Kakao;

import java.util.*;

public class Problem16 {
	public static int solution(String str1, String str2) {
		List<String> subset1 = getSubset(str1); 
		List<String> subset2 = getSubset(str2);
		double similarity = getSimilarity(subset1,subset2);
        return (int)(similarity * 65536);
    }

	private static List<String> getSubset(String str) {
		List<String> subset = new LinkedList<>();
		for(int i = 0 ; i < str.length()-1;i++) {
			if(str.substring(i,i+2).chars().allMatch(Character::isAlphabetic))
				subset.add(str.substring(i,i+2).toLowerCase());
		}
		return subset;
	}
	
	private static double getSimilarity(List<String> subset1, List<String> subset2) {
		List<String> intersection = new LinkedList<>(subset1);
		List<String> union= new LinkedList<>(subset1);
		int interCnt = 0;
		union.addAll(subset2);
		for(String element:subset2) {
			if(intersection.remove(element)) {
				interCnt++;
			}
		}
		return union.size()-interCnt == 0 ? 1 :
					(double)interCnt/(union.size()-interCnt);
	}

	public static void main(String[] args) {
		int ans = solution("FRANCE", "french");//16384
	}
}
