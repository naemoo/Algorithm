/*
 * https://www.acmicpc.net/problem/1992
*/
package DivideAndConquer;

import java.io.*;

public class Problem6 {
    static int[][] map;
    static int n;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	n = Integer.parseInt(br.readLine());
	map = new int[n][n];

	for (int i = 0; i < map.length; i++) {
	    map[i] = br.readLine().chars().map(e -> e - '0').toArray();
	}
	System.out.println(getQuadTree(0, 0, n, n));
    }

    private static String getQuadTree(int startX, int startY, int endX, int endY) {
	StringBuilder answer = new StringBuilder();
	if (isAllSame(startX, startY, endX, endY)) {
	    return String.valueOf(map[startX][startY]);
	} else {
	    answer.append("(");
	    answer.append(getQuadTree(startX, startY, (startX + endX) / 2, (startY + endY) / 2));
	    answer.append(getQuadTree(startX, (startY + endY) / 2, (startX + endX) / 2, endY));
	    answer.append(getQuadTree((startX + endX) / 2, startY, endX, (startY + endY) / 2));
	    answer.append(getQuadTree((startX + endX) / 2, (startY + endY) / 2, endX, endY));
	    answer.append(")");
	}
	return answer.toString();
    }

    private static boolean isAllSame(int startX, int startY, int endX, int endY) {
	int element = map[startX][startY];
	for (int i = startX; i < endX; i++) {
	    for (int j = startY; j < endY; j++) {
		if (element != map[i][j])
		    return false;
		element = map[i][j];
	    }
	}
	return true;
    }
}
