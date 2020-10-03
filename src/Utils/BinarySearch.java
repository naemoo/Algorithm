package Utils;

import java.util.*;
import java.util.function.Predicate;

public class BinarySearch {
    public static <E extends Comparable<E>> int bound(List<E> cards, E target, boolean isUpper) {
	int left = 0;
	int right = cards.size();
	Predicate<Integer> isProper = isUpper ? idx -> target.compareTo(cards.get(idx)) >= 0
		: idx -> target.compareTo(cards.get(idx)) > 0;

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
