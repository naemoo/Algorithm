/*
 * https://www.acmicpc.net/problem/10816
 * 숫자카드2
*/
package BinarySearch;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

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
	return bound(cards, target, true) - bound(cards, target, false);
    }

    private static int bound(int[] cards, int target, boolean isUpper) {
	int left = 0;
	int right = cards.length;
	Predicate<Integer> isProper = isUpper ? idx -> target >= cards[idx] : idx -> target > cards[idx];

	while (left < right) {
	    int mid = (left + right) / 2;
	    if (isProper.test(mid)) {
		left = mid + 1;
	    } else {
		right = mid;
	    }
	}
	return right;
    }
}
