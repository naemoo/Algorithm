/*
 * https://www.acmicpc.net/problem/5373
 * 큐빙
*/
package SamsungSWTest;

import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Problem09 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	HashMap<Character, char[][]> cube = new HashMap<>();
	HashMap<Character, char[]> affectedFace = new HashMap<>();

	initFace(affectedFace);

	int n = Integer.parseInt(br.readLine());
	for (int i = 0; i < n; i++) {
	    br.readLine();
	    String[] logs = br.readLine().split(" ");
	    initializeCube(cube);
	    turnCube(cube, logs, affectedFace);
	}

    }

    private static void initFace(HashMap<Character, char[]> affectedFace) {
	char[] faces = new char[] { 'U', 'D', 'F', 'B', 'L', 'R' };
	char[][] affects = { { 'F', 'L', 'B', 'R' }, { 'F', 'R', 'B', 'L' }, { 'U', 'R', 'D', 'L' },
		{ 'U', 'L', 'D', 'R' }, { 'U', 'F', 'D', 'B' }, { 'U', 'B', 'D', 'F' } };
	for (int i = 0; i < faces.length; i++) {
	    affectedFace.put(faces[i], affects[i]);
	}
    }

    private static void initializeCube(HashMap<Character, char[][]> cube) {
	char[] colors = new char[] { 'w', 'y', 'r', 'o', 'g', 'b' };
	char[] faces = new char[] { 'U', 'D', 'F', 'B', 'L', 'R' };

	for (int k = 0; k < colors.length; k++) {
	    char[][] initialFace = new char[3][3];
	    for (int i = 0; i < 3; i++) {
		Arrays.fill(initialFace[i], colors[k]);
	    }
	    cube.put(faces[k], initialFace);
	}
    }

    private static void turnCube(HashMap<Character, char[][]> cube, String[] logs,
	    HashMap<Character, char[]> affectedFace) {

	for (String log : logs) {
	    char[] moveLocation = affectedFace.get(log.charAt(0));
	    int i = log.charAt(1) == '+' ? 1 : 3;
	    for (int j = 0; j < i; j++) {
		trun(cube, moveLocation, log.charAt(0));
	    }
	}

	for (char[] upside : cube.get('U')) {
	    for (char element : upside) {
		System.out.print(element); 
	    }
	    System.out.println();
	}
	cube.clear();
    }

    private static void trun(HashMap<Character, char[][]> cube, char[] moveLocation, char key) {
	HashMap<Character, char[][]> copy = new HashMap<>();
	for (Map.Entry<Character, char[][]> element : cube.entrySet()) {
	    copy.put(element.getKey(), Arrays.stream(element.getValue()).map(char[]::clone).toArray(char[][]::new));
	}

	rotate(cube.get(key));
	switch (key) {
	case 'U':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		switch (i) {
		case 0:
		    IntStream.range(0, 3).forEach(idx -> cur[0][idx] = tmp[2 - idx][0]);
		    break;
		case 1:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][2] = tmp[0][idx]);
		    break;
		case 2:
		    IntStream.range(0, 3).forEach(idx -> cur[2][idx] = tmp[2 - idx][2]);
		    break;
		case 3:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][0] = tmp[2][idx]);
		    break;
		}
	    }
	    break;
	case 'D':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		switch (i) {
		case 0:
		    IntStream.range(0, 3).forEach(idx -> cur[2][idx] = tmp[idx][0]);
		    break;
		case 1:
		    IntStream.range(0, 3).forEach(idx -> cur[2 - idx][2] = tmp[2][idx]);
		    break;
		case 2:
		    IntStream.range(0, 3).forEach(idx -> cur[0][idx] = tmp[idx][2]);
		    break;
		case 3:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][0] = tmp[0][2 - idx]);
		    break;
		}
	    }
	    break;
	case 'F':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		IntStream.range(0, 3).forEach(idx -> cur[2][idx] = tmp[2][idx]);
	    }
	    break;
	case 'B':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		IntStream.range(0, 3).forEach(idx -> cur[0][idx] = tmp[0][idx]);
	    }
	    break;
	case 'L':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		switch (i) {
		case 2:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][2] = tmp[2 - idx][0]);
		    break;
		case 3:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][0] = tmp[2 - idx][2]);
		    break;
		default:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][0] = tmp[idx][0]);
		    break;
		}
	    }
	    break;
	case 'R':
	    for (int i = 0; i < moveLocation.length; i++) {
		char[][] tmp = copy.get(moveLocation[(i + 3) % 4]);
		char[][] cur = cube.get(moveLocation[i]);
		switch (i) {
		case 2:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][0] = tmp[2 - idx][2]);
		    break;
		case 3:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][2] = tmp[2 - idx][0]);
		    break;
		default:
		    IntStream.range(0, 3).forEach(idx -> cur[idx][2] = tmp[idx][2]);
		    break;
		}
	    }
	    break;
	}
	copy.clear();
    }

    private static void rotate(char[][] face) {
	char[][] copy = Arrays.stream(face).map(char[]::clone).toArray(char[][]::new);
	for (int i = 0; i < copy.length; i++) {
	    for (int j = 0; j < copy.length; j++) {
		face[i][j] = copy[2 - j][i];
	    }
	}
    }
}
