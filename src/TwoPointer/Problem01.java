package TwoPointer;

import java.io.*;
import java.util.*;

public class Problem01 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] ns = br.readLine().split("\\s");
	int n = Integer.parseInt(ns[0]);
	int s = Integer.parseInt(ns[1]);
	int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

	long sum = 0;
	int end = 0;
	int answer = Integer.MAX_VALUE;

	for (int start = 0; start < arr.length; start++) {
	    while (sum < s && end < arr.length) {
		sum += arr[end];
		end++;
	    }

	    if (sum >= s) {
		answer = Math.min(answer, end - start);
	    }

	    sum -= arr[start];
	}
	System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
