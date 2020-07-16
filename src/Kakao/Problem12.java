/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60062
 * 외벽점검
*/
package Kakao;

import java.util.*;
import java.util.stream.*;

public class Problem12 {
	static int[][] checkRange;
	static int[] visit;
	static int min = Integer.MAX_VALUE;
	static int maxDepth;
	
	
    public static int solution(int n, int[] weak, int[] dist) {
    	checkRange = new int[dist.length][n];
    	visit = new int[n];
    	maxDepth = dist.length;
    	
    	dist = Arrays.stream(dist).mapToObj(Integer::new).sorted(Comparator.reverseOrder())
    		.mapToInt(Integer::valueOf).toArray();
    	
    	for(int i = 0 ; i < checkRange.length;i++) {
    		for (int j = 0; j < checkRange[0].length; j++) {
    			for(int w:weak) {
    				if(w == j)
    					checkRange[i][j] = (dist[i] + j +1);
    				else if((dist[i] + j)%n == w) {
    					checkRange[i][j] = (dist[i] + j +1);
    				}
    			}
			}
    	}
    	DFS(0,n,weak);
        return min != Integer.MAX_VALUE? min :-1;
    }
    
	private static void DFS(int d,int n,int[] weak) {
		if(Arrays.stream(weak).allMatch(idx->visit[idx]==1)) {
			min = Math.min(min, d);
			return;
		}
		
		if(d == maxDepth || min < d) {
			return;
		}
		
		for(int i = 0 ; i<checkRange[0].length;i++) {
			if(checkRange[d][i] == 0) {
				continue;
			}
			if(IntStream.range(i, checkRange[d][i]).map(idx->idx%n).anyMatch(idx->visit[idx] == 1)) {
				continue;
			}
			IntStream.range(i, checkRange[d][i]).map(idx->idx%n).forEach(idx->visit[idx] =1);
			DFS(d+1,n,weak);
			IntStream.range(i, checkRange[d][i]).map(idx->idx%n).forEach(idx->visit[idx] =0);
		}
	}
	public static void main(String[] args) {
		solution(12, new int[] {1,3,4,9,10}, new int[] {1,2,3,4});
		System.out.println(min);
//		solution(50, new int[] {1, 5, 10, 12, 22, 25}, new int[] {4, 3, 2, 1});
//		System.out.println(min);
	}
}
