package Sort;

/*
 * 프로그래머스 : 가장큰수(정렬)
 * https://programmers.co.kr/learn/courses/30/lessons/42746 
*/
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










