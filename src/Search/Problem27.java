/*
 * https://www.acmicpc.net/problem/2573 
*/
package Search;

import java.io.*;
import java.util.*;

public class Problem27 {
    static int[][] map;
    static boolean[][] visit;
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] line = br.readLine().split(" ");
	int iceBerg = 0;
	int year = 0;

	map = new int[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
	visit = new boolean[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
	for (int i = 0; i < map.length; i++) {
	    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	
	int[][] tmp = new int[map.length][map[0].length];
	while ((iceBerg = getIceBerg()) < 2) {
	    if(iceBerg == 0) {
		year = 0;
		break;
	    }
	    
	    visit = new boolean[map.length][map[1].length];

	    for (int i = 0; i < tmp.length; i++) {
		for (int j = 0; j < tmp[0].length; j++) {
		    if (map[i][j] > 0) {
			tmp[i][j] = flowOneYear(i, j);
		    }
		}
	    }

	    for (int i = 0; i < tmp.length; i++) {
		for (int j = 0; j < tmp[0].length; j++) {
		    map[i][j] -= tmp[i][j];
		}
	    }
	    year++;
	}
	System.out.println(year);
    }

    private static int flowOneYear(int x, int y) {
	int cnt = 0;
	for (int i = 0; i < 4; i++) {
	    try {
		if (map[x + d[i][0]][y + d[i][1]] <= 0) {
		    cnt++;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
		
	    }
	}
	return cnt;
    }

    private static int getIceBerg() {
	int cnt = 0;
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[i].length; j++) {
		if (map[i][j] > 0 && !visit[i][j]) {
		    dfs(i, j);
		    cnt++;
		}
	    }
	}
	return cnt;
    }

    private static void dfs(int x, int y) {
	visit[x][y] = true;

	for (int i = 0; i < 4; i++) {
	    int nx = x + d[i][0];
	    int ny = y + d[i][1];
	    try {
		if (!visit[nx][ny] && map[nx][ny] > 0) {
		    dfs(nx, ny);
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	}
    }
}
