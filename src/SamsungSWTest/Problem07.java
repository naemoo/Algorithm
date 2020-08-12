/*
 * https://www.acmicpc.net/problem/15683 
 * 감시
*/
package SamsungSWTest;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Problem07 {
    static int[][] d = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static List<CCTV> cctvs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    static class CCTV {
	int type;
	int x;
	int y;

	public CCTV(int type, int x, int y) {
	    this.type = type;
	    this.x = x;
	    this.y = y;
	}

	public int[][][] rotate(int[][] map) {
	    int[][][] newMaps = null;
	    if (type == 1) {
		newMaps = new int[4][][];
		for (int i = 0; i < 4; i++) {
		    newMaps[i] = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		    beam(newMaps[i], 1 << i);
		}
	    } else if (type == 2) {
		newMaps = new int[2][][];
		for (int i = 0; i < 2; i++) {
		    newMaps[i] = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		    beam(newMaps[i], 5 << i);
		}
	    } else if (type == 3) {
		newMaps = new int[4][][];
		for (int i = 0; i < 4; i++) {
		    newMaps[i] = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		    beam(newMaps[i], 3 << i);
		}
	    } else if (type == 4) {
		newMaps = new int[4][][];
		for (int i = 0; i < 4; i++) {
		    newMaps[i] = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		    beam(newMaps[i], 11 << i);
		}
	    } else if (type == 5) {
		newMaps = new int[1][][];
		newMaps[0] = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
		beam(newMaps[0], 15);
	    }
	    return newMaps;
	}

	private void beam(int[][] newMap, int direction) {
	    for (int i = 0; i < 4; i++) {
		if (((direction >> i) & 1) == 1 || ((direction >> (i + 4)) & 1) == 1) {
		    int next = -1;
		    int nx = x + d[i][0];
		    int ny = y + d[i][1];
		    try {
			while ((next = newMap[nx][ny]) == 0 || next != 6) {
			    newMap[nx][ny] = 9;
			    nx = nx + d[i][0];
			    ny = ny + d[i][1];
			}
		    } catch (ArrayIndexOutOfBoundsException e) {
		    }
		}
	    }
	}

    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine().split(" ")[0]);
	int[][] map = new int[n][];

	for (int i = 0; i < n; i++) {
	    String line = br.readLine();
	    map[i] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
	    getCctv(map, i);
	}
	DFS(0, map);
	System.out.println(min);
    }

    private static void DFS(int d, int[][] map) {
	if (cctvs.size() == d) {
	    min = Math.min(min, getBlackArea(map));
	    return;
	}

	for (int[][] newMap : cctvs.get(d).rotate(map)) {
	    DFS(d + 1, newMap);
	}
    }

    private static int getBlackArea(int[][] map) {
	int answer = 0;
	for (int[] rows : map) {
	    for (int ele : rows) {
		if (ele == 0)
		    answer++;
	    }
	}
	return answer;
    }

    private static void getCctv(int[][] map, int i) {
	IntStream.range(0, map[i].length).filter(idx -> 1 <= map[i][idx] && map[i][idx] <= 5)
		.forEach(ele -> cctvs.add(new CCTV(map[i][ele], i, ele)));
    }
}
