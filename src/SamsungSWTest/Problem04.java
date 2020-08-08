/*
 * https://www.acmicpc.net/problem/14890
 * 경사로
*/
package SamsungSWTest;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Problem04 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] nl = br.readLine().split(" ");
	int n = Integer.parseInt(nl[0]);
	int[][] map = new int[n][n];
	for (int i = 0; i < n; i++) {
	    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	System.out.println(findPath(map, Integer.parseInt(nl[1])));
	;
    }

    private static int findPath(int[][] map, int l) {
	int answer = 0;
	List<int[]> mapList = new ArrayList<>(Arrays.asList(map));
	for (int i = 0; i < map.length; i++) {
	    if (isValidPath(l, mapList.get(i))) {
		answer++;
	    }
	    if (isValidPath(l, getRowPath(i, mapList))) {
		answer++;
	    }
	}
	return answer;
    }

    private static int[] getRowPath(int i, List<int[]> mapList) {
	return mapList.stream().mapToInt(arr -> arr[i]).toArray();
    }

    private static boolean isValidPath(int L, int[] path) {
	int[] visit = new int[path.length];
	for (int a = 0; a < path.length - 1; a++) {
	    int step = path[a + 1] - path[a];
	    if (Math.abs(step) > 1) {
		return false;
	    } else if (step == 0) {
		continue;
	    } else if (Math.abs(step) == 1) {
		try {
		    if (step > 0) {
			int comp = path[a];
			if (IntStream.range(a - L + 1, a + 1).map(i -> path[i]).allMatch(i -> i == comp)
				&& !IntStream.range(a - L + 1, a + 1).map(i -> visit[i]).anyMatch(i -> i == 1)) {
			    Arrays.fill(visit, a - L + 1, a + 1, 1);
			} else {
			    return false;
			}
		    } else {
			int comp = path[a + 1];
			if (IntStream.range(a + 1, a + 1 + L).map(i -> path[i]).allMatch(i -> i == comp)
				&& !IntStream.range(a + 1, a + 1 + L).map(i -> visit[i]).anyMatch(i -> i == 1)) {
			    Arrays.fill(visit, a + 1, a + 1 + L, 1);
			} else {
			    return false;
			}
		    }
		} catch (ArrayIndexOutOfBoundsException e) {
		    return false;
		}
	    }
	}
	return true;
    }
}
