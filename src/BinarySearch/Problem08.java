package BinarySearch;

import java.util.*;

public class Problem08 {
    public static long solution(int n, int[] times) {
	long max = Arrays.stream(times).max().getAsInt() * (long) n;
	long min = 0;
	while (min < max) {
	    long mid = (min + max) / 2;
	    long num = 0;
	    for (int time : times) {
		num += mid / time;
	    }
	    if (num < n) {
		min = mid + 1;
	    } else {
		max = mid;
	    }
	}
	return max;
    }

    public static void main(String[] args) {
	solution(5, new int[] { 1, 2 });
    }
}
