package Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Combination {
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	int r = s.nextInt();

	List<String> str = "abcd".chars().mapToObj(Character::toString).collect(Collectors.toList());
	System.out.println(combination(r, str));
    }

    public static <E> List<List<E>> combination(int r, List<E> list) {
	int n = list.size();
	List<List<E>> answer = new LinkedList<>();
	boolean[] visit = new boolean[n];

	dfs(0, 0, r, visit, answer, new LinkedList<>(), list);
	return answer;
    }

    private static <E> void dfs(int d, int start, int r, boolean[] visit, List<List<E>> answer, LinkedList<E> list,
	    List<E> eList) {
	if (d == r) {
	    answer.add(new LinkedList<>(list));
	    return;
	}

	for (int i = start; i < visit.length; i++) {
	    if (!visit[i]) {
		visit[i] = true;
		list.add(eList.get(i));
		dfs(d + 1, i + 1, r, visit, answer, list, eList);
		list.remove(list.size() - 1);
		visit[i] = false;
	    }
	}
    }
}
