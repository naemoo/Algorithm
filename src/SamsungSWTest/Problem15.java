package SamsungSWTest;

import java.io.*;
import java.util.stream.*;
import java.util.*;

public class Problem15 {
    static HashMap<Integer, HashSet<Integer>> rowInfo = new HashMap<>();
    static int cols;
    static int rows;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] infos = br.readLine().split(" ");
	cols = Integer.parseInt(infos[0]);
	rows = Integer.parseInt(infos[2]);
	int n = Integer.parseInt(infos[1]);

	for (int i = 0; i < n; i++) {
	    infos = br.readLine().split(" ");
	    rowInfo.computeIfAbsent(Integer.parseInt(infos[0]), k -> new HashSet<>()).add(Integer.parseInt(infos[1]));
	}

	int[] dest = new int[cols + 1];
	for (int i = 1; i < dest.length; i++) {
	    dest[i] = i;
	}

	dfs(0, 0, dest);

    }

    private static void dfs(int d, int cnt, int[] dest) {
	if (d == rows && cnt > 3) {
	    return;
	}
	int[] newDest = dest.clone();

	if (rowInfo.containsKey(d + 1)) {
	    for (int row : rowInfo.get(d + 1)) {
		swap(newDest, row, row + 1);
	    }
	}

	HashSet<Integer> org = rowInfo.computeIfAbsent(d + 1, k -> new HashSet<>());

	for (int i = 0; i <= 3; i++) {
	    for (HashSet<Integer> newRows : makeNew(i, org)) {
		rowInfo.put(d + 1, newRows);
		dfs(d + 1, cnt + 1, newDest);
		rowInfo.put(d + 1, org);
	    }
	}
    }

    private static List<HashSet<Integer>> makeNew(int n, HashSet<Integer> org) {
	List<HashSet<Integer>> list = new LinkedList<>();
	if (n == 0) {
	    list.add(org);

	} else {
	    boolean[] visit = new boolean[cols + 1];
	    IntStream.range(1, cols + 1).filter(e -> org.contains(e) || org.contains(e - 1))
		    .forEach(e -> visit[e] = true);
	    makeCombination(0, 1, n, list, new LinkedList<>(), visit);
	}
	return list;
    }

    private static void makeCombination(int d, int start, int n, List<HashSet<Integer>> list,
	    LinkedList<Integer> answer, boolean[] visit) {
	if (d == n) {
	    list.add(new HashSet<>(answer));
	    return;
	}

	for (int i = start; i < cols; i++) {
	    if (!visit[i]) {
		visit[i] = true;
		answer.add(i);
		makeCombination(d + 1, start + 1, n, list, answer, visit);
		answer.remove(answer.size() - 1);
		visit[i] = false;
	    }
	}

    }

    private static void swap(int[] dest, int i, int j) {
	int tmp = dest[i];
	dest[i] = dest[j];
	dest[j] = tmp;
    }

}
