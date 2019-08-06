package DP;
/*
 *백준:1912번(연속합) 스트림이용 
*/
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Problem12 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];
		int[] arr = Stream.of(br.readLine()).flatMap(s->Arrays.stream(s.split(" "))).mapToInt(Integer::parseInt).toArray();
		dp[0] = arr[0];
		int max = dp[0];
		for(int i = 1 ; i <n;i++) {
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
