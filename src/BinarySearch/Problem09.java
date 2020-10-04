package BinarySearch;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import Utils.BinarySearch;

public class Problem09 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	List<Integer> list = Arrays.stream(br.readLine().split("\\s")).map(Integer::valueOf)
		.collect(Collectors.toList());

	List<Integer> lis = new LinkedList<>();

	for (Integer i : list) {
	    int idx = BinarySearch.bound(lis, i, false);
	    if (lis.isEmpty() || idx == lis.size()) {
		lis.add(i);
	    } else {
		lis.set(idx, i);
	    }
	}
	System.out.println(lis.size());
    }
}
