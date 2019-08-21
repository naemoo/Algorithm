package Search;

/*
 * 프로그래머스(완전탐색) : 숫자야구
 * https://programmers.co.kr/learn/courses/30/lessons/42841
*/
import java.util.*;

class Solution {
	List<String> list = new LinkedList<>();//가능한 숫자 저장
	StringBuilder sb = new StringBuilder();
	int[] visit = new int[10];
    public int solution(int[][] baseball) {
    	visit[0] = 1;
    	makeNum(0);
    	for(int i =0;i<baseball.length;i++)
    		baseballGame(baseball[i]);
        return list.size();
    }
    //숫자 야구에서 존재할 수 있는 모든 숫자 만들기
    public void makeNum(int d) {
    	if(d == 3) {
    		list.add(sb.toString());
    		return;
    	}
    	for(int i = 1;i<10;i++) {
    		if(visit[i] == 0) {
    			visit[i] = 1;
    			sb.append(i);
    			makeNum(d+1);
    			sb.deleteCharAt(sb.length()-1);
    			visit[i] = 0;
    		}
    	}
    }
    //조건에 맞지 않은 숫자 지우기
    //주어진 스트라이크와 볼이 맞지않으면 숫자 제거
    public void baseballGame(int[] requirement) {
    	String str = String.valueOf(requirement[0]);
    	for(int i = 0 ; i < list.size();i++) {
    		String list_str = list.get(i);
    		int strike = 0;
        	int ball = 0;
    		for(int j = 0 ; j<3;j++) {
    			if(str.charAt(j) == list_str.charAt(j))
    				strike++;
    			else if(str.contains(list_str.substring(j, j+1)))
    				ball++;
    		}
    		if(strike!=requirement[1] || ball != requirement[2]) {
    			list.remove(i--);
    		}
    	}
    }
}

public class problem18{
	public static void main(String[] args) {
		Solution s = new Solution();
		int answer = s.solution(new int[][] {{123,1,1},{356,1,0},{327,2,0},{489,0,1}});
		System.out.println(answer);
	}
}
