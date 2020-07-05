package Kakao;

/*
 * 2019 KAKAO BLIND RECRUITMENT
 * 후보키 (부분 집합 문제)
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 * 
*/


import java.util.*;

public class Problem3 {
    public static int solution(String[][] relation) {
    	int answer =0;
    	int row = relation.length;
    	int col = relation[0].length;
    	List<Integer> list = new LinkedList<>();
    	for(int i=1; i < 1<<col ;i++) {
    		if(isSuperKey(relation,row,col,i)) {
    			list.add(i);
    		}
    	}
    	Comparator<Integer> comp  = new Comparator<Integer>() {
			int bitCount(int n) {
				int cnt = 0;
				while(n!=0) {
					if((n&1) != 0) cnt++;
					n = n>>1;
				}
				return cnt;
			}
			@Override
			public int compare(Integer o1, Integer o2) {
				int x = bitCount(o1),y = bitCount(o2);
				return Integer.compare(x, y);
			}
		};
		Collections.sort(list,comp);
		
		
		while(list.size()!=0) {
			int n = list.remove(0);
			++answer;
			for(Iterator<Integer> itr = list.iterator();itr.hasNext();) {
				int c = itr.next();
				if((n&c)==n)
					itr.remove();
			}
		}
    	
    	return answer;
    }
	

	private static boolean isSuperKey(String[][] relation, int row, int col, int subset) {
		for (int a = 0; a < row -1 ; a++) {
			for (int b = a+1; b < row; b++) {
				boolean isTrue = true;
				for (int k = 0; k < col; k++) {
					if((subset & 1<<k)==0)
						continue;
					if(!(relation[a][k].equals(relation[b][k]))) {
						isTrue = false;
						break;
					}
				}
				if(isTrue) 
					return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {
		int ans = solution(new String[][]{{"100","ryan","music","2"}
				,{"200","apeach","math","2"}
				,{"300","tube","computer","3"}
				,{"400","con","computer","4"}
				,{"500","muzi","music","3"}
				,{"600","apeach","music","2"}});
		System.out.println(ans);
	}
}
