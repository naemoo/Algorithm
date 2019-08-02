package BFS_Search;

import java.util.Arrays;
import java.util.Comparator;

class Com implements Comparator<String>{
	@Override
	public int compare(String str1, String str2) {
		for(int i = 0 ; i < Math.min(str1.length(), str2.length());i++) {
			if(str2.charAt(i)==str1.charAt(i))
				continue;
			return str2.charAt(i) - str1.charAt(i);
		}
		if(str1.length() > str2.length())
			return compare(str1.substring(str2.length()), str2);
		else if(str1.length() < str2.length())
			return compare(str1,str2.substring(str1.length()));
		return 0;
	}
}

class Solution3 {
    public static String solution(int[] numbers) {
    	for(int i = 0;numbers[i] == 0 ;i++)
    		if(i == numbers.length -1)
    			return "0";
    	String[] arr = Arrays.stream(numbers).mapToObj(i->String.valueOf(i)).toArray(String[]::new);
    	Com comp = new Com();
    	Arrays.parallelSort(arr,comp);
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < arr.length;i++)
    		sb.append(arr[i]);
    	return sb.toString();
    }
}

public class Problem2 {
	public static void main(String[] args) {
		System.out.println(Solution3.solution(new int[] {0,0,1}));
	}

}
/*
 	paralleSrot						sort
테스트 1> 통과 (132.63ms, 65.6MB)	테스트 1 〉	통과 (146.50ms, 65.5MB)
테스트 2 〉	통과 (99.40ms, 59.8MB)	테스트 2 〉	통과 (109.21ms, 59.6MB)
테스트 3 〉	통과 (174.31ms, 73MB)		테스트 3 〉	통과 (152.86ms, 71.7MB)
테스트 4 〉	통과 (14.30ms, 48.3MB)	테스트 4 〉	통과 (14.78ms, 47.9MB)
테스트 5 〉	통과 (148.61ms, 63.7MB)	테스트 5 〉	통과 (141.90ms, 63.1MB)
테스트 6 〉	통과 (110.45ms, 61.5MB)	테스트 6 〉	통과 (133.52ms, 62MB)
테스트 7 〉	통과 (4.33ms, 48.4MB)		테스트 7 〉	통과 (3.82ms, 48.9MB)
테스트 8 〉	통과 (4.68ms, 47.9MB)		테스트 8 〉	통과 (4.74ms, 48.4MB)
테스트 9 〉	통과 (4.66ms, 47.7MB)		테스트 9 〉	통과 (3.74ms, 47.9MB)
테스트 10 〉통과 (5.17ms, 48.5MB)		테스트 10 〉통과 (3.83ms, 48MB)
테스트 11 〉통과 (0.87ms, 47.6MB)		테스트 11 〉통과 (0.95ms, 47.5MB)
*/










