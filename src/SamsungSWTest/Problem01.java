/*
 * 삼성 SW 역량 테스트 기출 문제
 * https://www.acmicpc.net/problem/14503
 * 로봇 청소기
*/
package SamsungSWTest;

import java.util.*;
import java.io.*;

public class Problem01 {
    static int[][] d = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int[][] map;
    static HashSet<Robot> visit;
    static int testidx = 1;

    static class Robot {
	int x;
	int y;
	int direction;

	public Robot(int x, int y, int direction) {
	    this.x = x;
	    this.y = y;
	    this.direction = direction;
	}

	public Robot move(int num) {
	    int nextD = direction - num >= 0 ? direction - num : 4 + direction - num;
	    Robot nextRobot = new Robot(x + d[nextD][0], y + d[nextD][1], nextD);
	    return nextRobot;
	}

	@Override
	public int hashCode() {
	    return 51 * x + y;
	}

	@Override
	public boolean equals(Object obj) {
	    Robot other = (Robot) obj;
	    if ((x != other.x) || (y != other.y))
		return false;
	    return true;
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	int n = Integer.parseInt(st.nextToken());
	int m = Integer.parseInt(st.nextToken());
	map = new int[n][m];
	visit = new HashSet<>();

	st = new StringTokenizer(br.readLine(), " ");
	Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
		Integer.parseInt(st.nextToken()));
	visit.add(robot);

	for (int i = 0; i < map.length; i++) {
	    st = new StringTokenizer(br.readLine(), " ");
	    for (int j = 0; j < map[0].length; j++) {
		map[i][j] = Integer.parseInt(st.nextToken());
	    }
	}
	System.out.println(clean(1, robot));
    }

    private static int clean(int answer, Robot robot) {
	while (true) {
	    for (int i = 1; i <= 4; i++) {
		Robot nextRobot = robot.move(i);
		if (0 <= nextRobot.x && nextRobot.x < map.length && 0 <= nextRobot.y && nextRobot.y < map[0].length) {
		    if ((!visit.contains(nextRobot)) && map[nextRobot.x][nextRobot.y] == 0) {
			visit.add(nextRobot);
			answer++;
			robot = nextRobot;
			i = 0;
		    }
		}
	    }

	    Robot nextRobot = robot.move(2);
	    if (0 <= nextRobot.x && nextRobot.x < map.length && 0 <= nextRobot.y && nextRobot.y < map[0].length) {
		if (map[nextRobot.x][nextRobot.y] == 0) {
		    nextRobot.direction = robot.direction;
		    robot = nextRobot;
		} else {
		    break;
		}
	    }
	}
	return answer;
    }
}

