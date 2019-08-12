package DP;
/*
 * 프로그래머스 : 정수 삼각형(DP) 
*/
class Solution {
    public int solution(int[][] triangle) {
    	int[][] dp = new int[triangle.length][triangle.length];
    	int level = 2;
    	dp[0][0] = triangle[0][0];
    	int max = 0;
    	for(int i = 1 ; i <triangle.length;i++) {
    		for(int j = 0 ; j<level;j++) {
    			if(j == 0)
    				dp[i][j] = dp[i-1][j] + triangle[i][j];
    			else if(j == level -1) {
    				dp[i][j] = dp[i-1][j-1] + triangle[i][j];
    			}
    			else
    				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])
    				+triangle[i][j];
    			max = Math.max(max,dp[i][j]);
    		}
    		level++;
    	}
        return max;
    }
}

public class Problem16 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] tri = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		System.out.println(s.solution(tri));
	}
}
