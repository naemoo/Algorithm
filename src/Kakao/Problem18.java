/*
 * Summer/Winter Coding(2018)
 * https://programmers.co.kr/learn/courses/30/lessons/49993
 * 스킬트리
*/
package Kakao;

import java.util.stream.*;

public class Problem18 {
	public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String skillTree:skill_trees) {
        	String comp = skillTree.chars().mapToObj(Character::toString).filter(skill::contains)
        			.collect(Collectors.joining());
        	if(skill.indexOf(comp)==0)
        		answer++;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"});//2
	}
}
