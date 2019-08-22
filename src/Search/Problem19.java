package Search;

/*
 * 프로그래머스 : 카펫(완전탐색)
 * https://programmers.co.kr/learn/courses/30/lessons/42842
*/
//에라토스테네스의 체 사용
class Solution19{
	public int[] solution(int brown,int red) {
		int sum = brown + red;
		for(int row = 3;row<=Math.sqrt(sum);row++) {
			int col;
			if(sum % row == 0) {
				col = sum /row;
				if((col-2) * (row-2) == red)
					return new int[] {col,row};
			}
		}
		return null;
	}
}



public class Problem19 {
	public static void main(String[] args) {
		Solution19 s = new Solution19();
		int[] arr = s.solution(24, 24);
		for(int i:arr)
			System.out.print(i+" ");
	}
}
