package BinarySearch;

import java.util.*;
import java.io.*;

public class Problem01 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	int[] A = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).sorted().toArray();
	br.readLine();
	int[] B = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	for (int b : B) {
	    System.out.println(binarySearch(A, b) >= 0 ? 1 : 0);
	}
    }

    private static int binarySearch(int[] a, int b) {
	int left = 0;
	int right = a.length - 1;
	int idx = -1;
	while (left <= right) {
	    int mid = (left + right) >>> 1; // divide by 2
	    if (b > a[mid]) {
		left = mid + 1;
	    } else if (b < a[mid]) {
		right = mid - 1;
	    } else {
		idx = mid;
		break;
	    }
	}
	return idx;
    }

}
