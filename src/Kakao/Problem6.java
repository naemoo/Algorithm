/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17680
 * 캐시 문제 
*/
package Kakao;

import java.util.*;

class Solution{
	public int solution(int cacheSize,String[] cities) {
		List<String> cache = new LinkedList<>();
        int idx = 0;
        int exeTime = 0;

    	for(String city:cities){
    		int hitIdx = findHitIndex(cache,city);
    		if(hitIdx >= 0) {
    			exeTime += 1;
    			String tmp = cache.remove(hitIdx);
    			cache.add(tmp);
    		}
    		else {
    			exeTime += 5;
    			if((cache.size() != 0) 
    					&& (cache.size() == cacheSize))
    				cache.remove(0);
    			if(cacheSize != 0)
    				cache.add(city);
    		}
        }
    	
        System.out.println(exeTime);
        return exeTime;
	}

	private int findHitIndex(List<String> cache, String city) {
		for (int i = 0; i < cache.size(); i++) {
			if(cache.get(i).compareToIgnoreCase(city)==0) {
				return i;
			}
		}
		return -1;
	}
}

public class Problem6 {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(3,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
		s.solution(3,new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
		s.solution(2,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
		s.solution(5,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
		s.solution(2,new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
		s.solution(0,new String[]{"j", "s", "p", "j", "s","p","j","j","j","s","p"});
	}
}
