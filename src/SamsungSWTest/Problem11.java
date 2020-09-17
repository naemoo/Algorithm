package SamsungSWTest;

/*
 * https://www.acmicpc.net/problem/13460
*/

import java.io.*;
import java.util.*;

public class Problem11 {
    static int[][] map;
    static HashSet<Step> visit = new HashSet<>();
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int row;
    static int col;

    static class Step {
	int[] blue;
	int[] red;
	int step;
	boolean isLast;

	public Step(int step) {
	    this.step = step;
	    isLast = false;
	}

	public Step nextStep(int i) {
	    Step next = new Step(this.step + 1);
	    next.blue = this.blue;
	    next.red = this.red;

	    int[] curB = blue, curR = red;
	    do {
		if (Arrays.equals(next.blue, next.red))
		    break;

		curB = next.blue;
		curR = next.red;

		next.blue = nextBlue(i, curB);
		if (next.blue == null)
		    return null;
		next.red = nextRed(i, curR);
		if (map[next.red[0]][next.red[1]] == 'O') {
		    next.isLast = true;
		    while (!Arrays.equals(next.blue, curB)) {
			curB = next.blue;
			next.blue = nextBlue(i, curB);
			if (next.blue == null)
			    return null;
		    }
		    break;
		}
	    } while (!(Arrays.equals(curB, next.blue) && Arrays.equals(curR, next.red)));

	    if (Arrays.equals(next.red, next.blue)) {
		next.red = curR;
		next.blue = curB;
	    }
	    return next;
	}

	private int[] nextBlue(int n, int[] blue) {
	    int x = blue[0] + d[n][0];
	    int y = blue[1] + d[n][1];
	    if (map[x][y] == 'O')
		return null;
	    if (map[x][y] == '.') {
		return new int[] { x, y };
	    } else {
		return blue;
	    }
	}

	private int[] nextRed(int n, int[] red) {
	    int x = red[0] + d[n][0];
	    int y = red[1] + d[n][1];
	    if (map[x][y] == '.' || map[x][y] == 'O') {
		return new int[] { x, y };
	    } else {
		return red;
	    }
	}

	@Override
	public int hashCode() {
	    return Arrays.hashCode(blue) + Arrays.hashCode(red) * 31;
	}

	@Override
	public boolean equals(Object obj) {
	    Step other = (Step) obj;
	    if (!Arrays.equals(blue, other.blue))
		return false;
	    if (!Arrays.equals(red, other.red))
		return false;
	    return true;
	}

    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] info = br.readLine().split(" ");
	row = Integer.parseInt(info[0]);
	col = Integer.parseInt(info[1]);
	map = new int[row][];

	Queue<Step> q = new LinkedList<>();
	Step init = new Step(0);

	q.add(init);
	for (int i = 0; i < row; i++) {
	    map[i] = br.readLine().chars().toArray();
	    for (int j = 0; j < map[i].length; j++) {
		if (map[i][j] == 'R') {
		    q.peek().red = new int[] { i, j };
		    map[i][j] = '.';
		} else if (map[i][j] == 'B') {
		    q.peek().blue = new int[] { i, j };
		    map[i][j] = '.';
		}
	    }
	}
	visit.add(init);

	System.out.println(removeRedMarble(q));
    }

    private static int removeRedMarble(Queue<Step> q) {
	while (!q.isEmpty()) {
	    Step cur = q.poll();
	    for (int i = 0; i < 4; i++) {
		Step next = cur.nextStep(i);
		if (next != null && !visit.contains(next)) {
		    if (next.isLast) {
			return next.step <= 10 ? next.step : -1;
		    }
		    q.add(next);
		    visit.add(next);
		}
	    }
	}
	return -1;
    }
}
