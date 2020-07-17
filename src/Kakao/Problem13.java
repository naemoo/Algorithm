/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 * 문자열 압축
*/
package Kakao;

public class Problem13 {
	public static int solution(String s) {
		int min = s.length();
		for (int k = 1; k <= s.length()/2; k++) {
			String shortStr = compressString(s,k);
			min = Math.min(shortStr.length(), min);
		}
		return min;
    }
	private static String compressString(String s, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length()-k +1 ;){
			String std = s.substring(i,i+k);
			int cnt = 1;
			for (int j = i+k; j <s.length()-k+1 ; j=j+k) {
				if(std.equals(s.substring(j,j+k))) {
					cnt++;
				}
				else
					break;
			}
			if(cnt > 1)
				sb.append(cnt);
				sb.append(std);
			i += cnt*k;
			if(s.length()-k+1<=i && i <s.length()) {
				sb.append(s.substring(i,s.length()));
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		solution("aabbaccc");
	}
}
