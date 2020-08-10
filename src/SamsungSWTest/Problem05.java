/*
 * https://www.acmicpc.net/problem/14891 
 * 톱니바퀴
*/
package SamsungSWTest;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Problem05 {
    static class Gear {
	List<Integer> gear;
	Gear left;
	Gear right;

	public Gear(String str) {
	    gear = str.chars().mapToObj(c -> c - '0').collect(Collectors.toCollection(LinkedList::new));
	    left = null;
	    right = null;
	}

	void rotate(boolean l, boolean r) {
	    if (right != null && r)
		right.rotatedByLeft(this, -1);
	    if (left != null && l)
		left.rotatedByRight(this, -1);
	    int tmp = gear.remove(gear.size() - 1);
	    gear.add(0, tmp);
	}

	void rotateReverse(boolean l, boolean r) {
	    if (right != null && r)
		right.rotatedByLeft(this, 1);
	    if (left != null && l)
		left.rotatedByRight(this, 1);
	    int tmp = gear.remove(0);
	    gear.add(tmp);
	}

	void rotatedByRight(Gear gear, int direction) {
	    if (this.get(2) != gear.get(6)) {
		if (direction > 0)
		    rotate(true, false);
		else
		    rotateReverse(true, false);
	    }
	}

	void rotatedByLeft(Gear gear, int direction) {
	    if (this.get(6) != gear.get(2)) {
		if (direction > 0)
		    rotate(false, true);
		else
		    rotateReverse(false, true);
	    }
	}

	private int get(int i) {
	    return gear.get(i);
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Gear[] gears = new Gear[4];
	int k = 0;
	Gear pre = null;
	for (int i = 0; i < 4; i++) {
	    gears[i] = new Gear(br.readLine());
	    gears[i].left = pre;
	    pre = gears[i];
	}
	for (int i = 0; i < 3; i++) {
	    gears[i].right = gears[i + 1];
	}

	k = Integer.parseInt(br.readLine());
	int[][] commands = new int[k][];
	for (int i = 0; i < k; i++) {
	    commands[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	for (int[] command : commands) {
	    if (command[1] > 0) {
		gears[command[0] - 1].rotate(true, true);
	    } else {
		gears[command[0] - 1].rotateReverse(true, true);
	    }
	}
	System.out.println(getAnswer(gears));
    }

    private static int getAnswer(Gear[] gears) {
	int answer = 0;
	for (int i = 0; i < gears.length; i++) {
	    answer += gears[i].get(0) == 1 ? 1 << i : 0;
	}
	return answer;
    }
}
