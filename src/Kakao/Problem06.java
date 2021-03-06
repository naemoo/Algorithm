/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17680
 * 캐시 문제 
*/
package Kakao;

import java.util.*;

public class Problem06 {
	public static int solution(int cacheSize,String[] cities) {
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

	private static int findHitIndex(List<String> cache, String city) {
		for (int i = 0; i < cache.size(); i++) {
			if(cache.get(i).compareToIgnoreCase(city)==0) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		solution(3,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
		solution(3,new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
		solution(2,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
		solution(5,new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
		solution(2,new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
		solution(0,new String[]{"j", "s", "p", "j", "s","p","j","j","j","s","p"});
	}
}
