package Utils;

import java.util.*;
import java.util.function.Predicate;

public class BinarySearch {
    public static <E extends Comparable<E>> int bound(List<E> list, E target, boolean isUpper) {
	int left = 0;
	int right = list.size();
	Predicate<Integer> isProper = isUpper ? idx -> target.compareTo(list.get(idx)) >= 0
		: idx -> target.compareTo(list.get(idx)) > 0;

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

    public static <E extends Comparable<E>> int getNum(List<E> list, E num) {
	return bound(list, num, true) - bound(list, num, false);
    }

}
