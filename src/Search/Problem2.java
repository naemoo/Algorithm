package Search;

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
�׽�Ʈ 1> ��� (132.63ms, 65.6MB)	�׽�Ʈ 1 ��	��� (146.50ms, 65.5MB)
�׽�Ʈ 2 ��	��� (99.40ms, 59.8MB)	�׽�Ʈ 2 ��	��� (109.21ms, 59.6MB)
�׽�Ʈ 3 ��	��� (174.31ms, 73MB)		�׽�Ʈ 3 ��	��� (152.86ms, 71.7MB)
�׽�Ʈ 4 ��	��� (14.30ms, 48.3MB)	�׽�Ʈ 4 ��	��� (14.78ms, 47.9MB)
�׽�Ʈ 5 ��	��� (148.61ms, 63.7MB)	�׽�Ʈ 5 ��	��� (141.90ms, 63.1MB)
�׽�Ʈ 6 ��	��� (110.45ms, 61.5MB)	�׽�Ʈ 6 ��	��� (133.52ms, 62MB)
�׽�Ʈ 7 ��	��� (4.33ms, 48.4MB)		�׽�Ʈ 7 ��	��� (3.82ms, 48.9MB)
�׽�Ʈ 8 ��	��� (4.68ms, 47.9MB)		�׽�Ʈ 8 ��	��� (4.74ms, 48.4MB)
�׽�Ʈ 9 ��	��� (4.66ms, 47.7MB)		�׽�Ʈ 9 ��	��� (3.74ms, 47.9MB)
�׽�Ʈ 10 ����� (5.17ms, 48.5MB)		�׽�Ʈ 10 ����� (3.83ms, 48MB)
�׽�Ʈ 11 ����� (0.87ms, 47.6MB)		�׽�Ʈ 11 ����� (0.95ms, 47.5MB)
*/










