package BinarySearch;

import java.io.*;
import java.util.*;

public class Problem02 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	br.readLine();
	int[] cards = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	br.readLine();
	int[] mS = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	Arrays.sort(cards);

	StringBuilder answer = new StringBuilder();
	for (int m : mS) {
	    answer.append(getCount(cards, m) + " ");
	}
	System.out.println(answer);
    }

    private static int getCount(int[] cards, int target) {
	int upper = upperBound(cards, target);
	int lower = lowerBound(cards, target);
	return upper - lower;
    }

    private static int upperBound(int[] cards, int target) {
	int left = 0;
	int right = cards.length;

	while (left < right) {
	    int mid = (left + right) / 2;
	    if (target >= cards[mid]) {
		left = mid + 1;
	    } else {
		right = mid;
	    }
	}
	return right;
    }

    private static int lowerBound(int[] cards, int target) {
	int left = 0;
	int right = cards.length;

	while (left < right) {
	    int mid = (left + right) / 2;
	    if (target > cards[mid]) {
		left = mid + 1;
	    } else {
		right = mid;
	    }
	}
	return right;
    }
}
