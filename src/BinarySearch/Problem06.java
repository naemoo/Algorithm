package BinarySearch;

import java.util.*;
import java.io.*;

public class Problem06 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] info = br.readLine().split("\\s");
	int n = Integer.parseInt(info[0]);
	int c = Integer.parseInt(info[1]);

	int[] homes = new int[n];

	for (int i = 0; i < n; i++) {
	    homes[i] = Integer.parseInt(br.readLine());
	}
	Arrays.sort(homes);

	int answer = findLongestPath(homes, c, 1, homes[n - 1]);
	System.out.println(answer);
    }

    private static int findLongestPath(int[] homes, int c, int left, int right) {
	while (left < right) {
	    int mid = (left + right + 1) / 2;

	    if (isProper(c, mid, homes)) {
		left = mid;
	    } else {
		right = mid - 1;
	    }
	}

	return right;
    }

    private static boolean isProper(int c, int mid, int[] homes) {
	int start = homes[0];
	int cnt = 1;

	for (int i = 1; i < homes.length; i++) {
	    int d = homes[i] - start;
	    if (mid <= d) {
		cnt++;
		start = homes[i];
	    }
	}
	return cnt >= c ? true : false;
    }

}
