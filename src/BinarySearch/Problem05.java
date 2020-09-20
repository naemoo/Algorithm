package BinarySearch;

import java.util.*;
import java.io.*;

public class Problem05 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	br.readLine();
	int[] budgets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

	System.out.println(getMaxBudGet(budgets, Integer.parseInt(br.readLine())));
    }

    private static int getMaxBudGet(int[] budgets, int total) {
	int min = 0;
	int max = Arrays.stream(budgets).max().getAsInt();

	while (min < max) {
	    int mid = (min + max + 1) / 2;

	    if (isProper(mid, budgets, total)) {
		min = mid;
	    } else {
		max = mid - 1;
	    }
	}
	return max;
    }

    private static boolean isProper(int target, int[] budgets, int total) {
	for (int budget : budgets) {
	    int price = target >= budget ? budget : target;
	    total -= price;
	}
	return total >= 0 ? true : false;
    }
}
