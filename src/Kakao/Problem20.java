/*
 * https://programmers.co.kr/learn/courses/30/lessons/12980
 * Summer/Winter Coding(2018)
 * 점프와 순간이동 
*/
package Kakao;

import java.util.*;

public class Problem20 {
    static private HashMap<Integer,Integer> dp = new HashMap<>();
    public static int solution(int n) {
	dp.put(1,1);
	return getBatteryConsumtion(n);
    }
    

    private static int getBatteryConsumtion(int n) {
	if(dp.containsKey(n))
	    return dp.get(n);
	if(n%2 == 0) 
	    dp.put(n, getBatteryConsumtion(n/2));
	else
	    dp.put(n, getBatteryConsumtion(n-1)+1);
	return dp.get(n);
    }


    public static void main(String[] args) {
	int ans = solution(5);
	System.out.println(ans);//2
	ans = solution(6);
	System.out.println(ans);//2
	ans = solution(5000);
	System.out.println(ans);//5
	ans = solution(1000000000);
	System.out.println(ans);//13
    }
}
