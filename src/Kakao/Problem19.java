/*
 * Summer/Winter Coding(~2018)
 * https://programmers.co.kr/learn/courses/30/lessons/12981
 * 영어 끝말잇기 
*/
package Kakao;

import java.util.*;

public class Problem19 {
	
	public static int[] solution(int n, String[] words) {
		int[] answer = null;
		List<String> wordLog = new LinkedList<>();
		String prevWord = words[0].substring(words[0].length()-1,words[0].length());
		wordLog.add(words[0]);
		
		for (int i = 1; i < words.length; i++) {
			if(isFail(prevWord,words[i],wordLog)) {
				answer = new int[] {(i%n)+1,(i/n)+1};
				break;
			}
			else {
				prevWord = words[i];
				wordLog.add(prevWord);
			}
		}
        return answer == null?new int[] {0,0}:answer;
    }
	

	private static boolean isFail(String prevWord, String curWord, List<String> wordLog) {
		return (prevWord.charAt(prevWord.length()-1)!= curWord.charAt(0)
				|| wordLog.contains(curWord)) ? true :false;
	}


	public static void main(String[] args) {
		int[] arr = solution(3, new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
		System.out.println(Arrays.toString(arr));
	}
}
