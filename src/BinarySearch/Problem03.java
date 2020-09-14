/*
 * https://www.acmicpc.net/problem/1654
 * 랜선 자르기
*/
package BinarySearch;

import java.io.*;

public class Problem03 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] info = br.readLine().split("\\s");
	int k = Integer.parseInt(info[0]);
	int n = Integer.parseInt(info[1]);
	long[] arr = new long[k];
	long min = 0;
	long max = 0;
	for (int i = 0; i < k; i++) {
	    arr[i] = Integer.parseInt(br.readLine());
	    max = Long.max(max, arr[i]);
	}

	System.out.println(getLength(min, max, arr, n));
    }

    private static long getLength(long min, long max, long[] arr, int n) {
	while (min < max) {
	    long mid = (min + max + 1) / 2;
	    if (isProper(mid, arr, n)) {
		min = mid;
	    } else {
		max = mid - 1;
	    }
	}
	return max;
    }

    private static boolean isProper(long mid, long[] arr, int n) {
	int cnt = 0;
	for (int i = 0; i < arr.length; i++) {
	    int pCnt = 0;
	    while (arr[i] - (pCnt + 1) * mid >= 0) {
		pCnt++;
	    }
	    cnt += pCnt;
	}
	return cnt >= n ? true : false;
    }
}
