/*
 * Summer/Winter Coding(2019)
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 * 멀쩡한 사각형
 * 유클리드 호제법 사용
*/
package Kakao;

public class Problem17 {

	public static long solution(int w, int h) {
        return (long)w *h - (w+h-gcd(Math.max(w, h),Math.min(w, h)));
	}
	
	private static int gcd(int a,int b) {
		return a%b ==0 ? b: gcd(b,a%b);
	}

	public static void main(String[] args) {
		long ans = solution(8,12);
		System.out.println(ans);
	}
}
