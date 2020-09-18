/*
 * https://www.acmicpc.net/problem/3190
 * 뱀
*/
package SamsungSWTest;

import java.io.*;
import java.util.*;

public class Problem12 {
    static int[][] d = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int[][] map;
    static HashMap<Integer, String> cmd = new HashMap<>();

    static class Snake {
	List<List<Integer>> body = new LinkedList<>();
	List<Integer> head;

	public Snake() {
	    addHead(Arrays.asList(0, 0));
	    head = body.get(0);
	}

	void addHead(List<Integer> arr) {
	    body.add(0, arr);
	    head = arr;
	}

	boolean moveHead(int direction) {
	    int nx = head.get(0) + d[direction][0];
	    int ny = head.get(1) + d[direction][1];

	    if (body.contains(Arrays.asList(nx, ny))) {
		return true;
	    } else if (map[nx][ny] == 1) {
		addHead(Arrays.asList(nx, ny));
		map[nx][ny] = 0;
	    } else {
		addHead(Arrays.asList(nx, ny));
		body.remove(body.size() - 1);
	    }
	    return false;
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());

	map = new int[n][n];

	int cnt = Integer.parseInt(br.readLine());
	for (int i = 0; i < cnt; i++) {
	    String[] info = br.readLine().split(" ");
	    map[Integer.parseInt(info[0]) - 1][Integer.parseInt(info[1]) - 1] = 1;
	}

	cnt = Integer.parseInt(br.readLine());
	for (int i = 0; i < cnt; i++) {
	    String[] info = br.readLine().split(" ");
	    cmd.put(Integer.parseInt(info[0]), info[1]);
	}
	System.out.println(startGame());
    }

    private static int startGame() {
	boolean isEnd = false;
	int curD = 0;
	int stage = 0;
	Snake snake = new Snake();

	while (!isEnd) {
	    try {
		if (cmd.containsKey(stage)) {
		    curD = cmd.get(stage).equals("D") ? (curD + 1) % 4 : (curD + 3) % 4;
		}
		stage++;
		isEnd = snake.moveHead(curD);
	    } catch (ArrayIndexOutOfBoundsException e) {
		break;
	    }
	}

	return stage;
    }

}
