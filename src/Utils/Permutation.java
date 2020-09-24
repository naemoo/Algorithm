package Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Permutation {
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	int n = s.nextInt();
	int r = s.nextInt();

	System.out.println(permutation(n, r, Arrays.asList(1, 2, 3, 4)));
    }

    private static <E> List<List<E>> permutation(int n, int r, List<E> list) {
	boolean[] visit = new boolean[n];
	List<List<E>> answer = new LinkedList<>();
	dfs(r, 0, visit, answer, new LinkedList<>(), list);
	return answer;
    }

    private static <E> void dfs(int r, int d, boolean[] visit, List<List<E>> answer, LinkedList<E> list,
	    List<E> eList) {
	if (d == r) {
	    answer.add(new LinkedList<>(list));
	    return;
	}
	for (int i = 0; i < visit.length; i++) {
	    if (!visit[i]) {
		visit[i] = true;
		list.add(eList.get(i));
		dfs(r, d + 1, visit, answer, list, eList);
		list.remove(list.size() - 1);
		visit[i] = false;
	    }
	}
    }

}
