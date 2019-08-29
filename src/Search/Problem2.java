package Search;

/* 프로그래머스 : 소수찾기(완전탐색)
 * https://programmers.co.kr/learn/courses/30/lessons/42839
*/
import java.util.*;

class Solution20 {
	List<Integer> list = new LinkedList<>();
	StringBuilder sb = new StringBuilder();
	int[]visit;
	char[] arr;
	int[][] dp;
	int n;
	int max = 0;
    public int solution(String numbers) {
    	n = numbers.length();
    	visit = new int[n];
    	arr = numbers.toCharArray();
    	makeNum(0);
    	dp = new int[max+1][2];
    	dp[0][0] = dp[1][0] = 1;
    	isPrime();
        return list.size();
    }
    void makeNum(int d) {
    	if(d==n) {
    		return;
    	}
    	for(int i = 0;i<n;i++) {
    		if(visit[i] ==0) {
    			visit[i] = 1;
    			sb.append(arr[i]);
    			max = Math.max(max, Integer.parseInt(sb.toString()));
    			if(!sb.toString().equals("0")) 
    				list.add(Integer.parseInt(sb.toString()));
    			makeNum(d+1);
    			sb.deleteCharAt(sb.length()-1);
    			visit[i] = 0;
    		}
    	}
    }
    void isPrime() {
    	for(int i = 2;i<=max;i++) {
    		for(int j = 2;j<= Math.sqrt(i);j++) {
    			if(i%j == 0) {
    				dp[i][0] = 1;//소수 아님
    				break;
    			}
    		}
    	}
    	for(int i = 0 ; i < list.size();i++) {
    		int num = list.get(i);
    		if(dp[num][0] == 1) 
    			list.remove(i--);
    		else if(dp[num][1] == 1)
    			list.remove(i--);
    		else
    			dp[num][1] = 1;//중복체크
    	}
    }
}

public class Problem2 {
	public static void main(String[] args) {
		Solution20 s = new Solution20();
		int n = s.solution("17");
		System.out.println(n);
	}
}
