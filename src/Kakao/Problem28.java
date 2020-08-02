/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60063
 * 블록 이동하기
*/
package Kakao;

import java.util.*;

public class Problem28 {

    static int[] dx = new int[] { 1, 0, -1, 0 };
    static int[] dy = new int[] { 0, 1, -0, -1 };
    static HashMap<Robot, Integer> visit;
    static int[][] map;

    static class Robot {
	int x1;
	int x2;
	int y1;
	int y2;
	int level;

	public Robot(int x1, int x2, int y1, int y2, int level) {
	    this.x1 = x1;
	    this.x2 = x2;
	    this.y1 = y1;
	    this.y2 = y2;
	    this.level = level;
	}

	@Override
	public String toString() {
	    return "Robot [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";
	}

	@Override
	public int hashCode() {
	    final int prime = 3;
	    int result = 0;
	    result = prime * (x1 + x2) + (y1 + y2);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Robot other = (Robot) obj;
	    if (x1 == other.x1 && y1 == other.y1 && x2 == other.x2 && y2 == other.y2) {
		return true;
	    }
	    if (x2 == other.x1 && y2 == other.y1 && x1 == other.x2 && y1 == other.y2) {
		return true;
	    }
	    return false;
	}

	Robot move(int i) {
	    try {
		if (i < 4) {
		    return new Robot(x1 + dx[i], x2 + dx[i], y1 + dy[i], y2 + dy[i], level + 1);
		} else if (i == 4) {
		    return rotateClockPivotR1();
		} else if (i == 5)
		    return rotateClockPivotR2();
		else if (i == 6) {
		    return rotateReversePivotR1();
		} else
		    return rotateReversePivotR2();
	    } catch (ArrayIndexOutOfBoundsException e) {
		return this;
	    }
	}

	Robot rotateClockPivotR1() {
	    if (x1 == x2) {
		return y1 < y2 ? (map[x2 + 1][y2] == 0 ? new Robot(x1, x1 + 1, y1, y1, level + 1) : this)
			: (map[x2 - 1][y2] == 0) ? new Robot(x1, x1 - 1, y1, y1, level + 1) : this;
	    } else {
		return x1 < x2 ? (map[x2][y2 - 1] == 0 ? new Robot(x1, x1, y1, y1 - 1, level + 1) : this)
			: ((map[x2][y2 + 1] == 0) ? new Robot(x1, x1, y1, y1 + 1, level + 1) : this);
	    }
	}

	Robot rotateClockPivotR2() {
	    if (x1 == x2) {
		return y1 < y2 ? (map[x1 - 1][y1] == 0 ? new Robot(x2 - 1, x2, y2, y2, level + 1) : this)
			: (map[x1 + 1][y1] == 0 ? new Robot(x2 + 1, x2, y2, y2, level + 1) : this);
	    } else {
		return x1 < x2 ? (map[x1][y1 + 1] == 0 ? new Robot(x2, x2, y2 + 1, y2, level + 1) : this)
			: (map[x1][y1 - 1] == 0 ? new Robot(x2, x2, y2 - 1, y2, level + 1) : this);
	    }
	}

	Robot rotateReversePivotR1() {
	    if (x1 == x2) {
		return y1 < y2 ? (map[x2 - 1][y2] == 0 ? new Robot(x1, x1 - 1, y1, y1, level + 1) : this)
			: (map[x2 + 1][y2] == 0 ? new Robot(x1, x1 + 1, y1, y1, level + 1) : this);
	    } else {
		return x1 < x2 ? (map[x2][y2 + 1] == 0 ? new Robot(x1, x1, y1, y1 + 1, level + 1) : this)
			: (map[x2][y2 - 1] == 0 ? new Robot(x1, x1, y1, y1 - 1, level + 1) : this);
	    }
	}

	Robot rotateReversePivotR2() {
	    if (x1 == x2) {
		return y1 < y2 ? (map[x1 + 1][y1] == 0 ? new Robot(x2 + 1, x2, y2, y2, level + 1) : this)
			: (map[x1 - 1][y1] == 0 ? new Robot(x2 - 1, x2, y2, y2, level + 1) : this);
	    } else {
		return x1 < x2 ? (map[x1][y1 - 1] == 0 ? new Robot(x2, x2, y2 - 1, y2, level + 1) : this)
			: (map[x1][y1 + 1] == 0 ? new Robot(x2, x2, y2 + 1, y2, level + 1) : this);
	    }
	}

	public boolean canGo(int d) {
	    if ((0 <= x1 && x1 < map.length) && (0 <= y1 && y1 < map.length) && (0 <= x2 && x2 < map.length)
		    && (0 <= y2 && y2 < map.length)) {
		if (map[x1][y1] == 0 && map[x2][y2] == 0) {
		    if (!visit.containsKey(this))
			return true;
		}
	    }
	    return false;
	}
    }

    public static int solution(int[][] board) {
	map = board;
	visit = new HashMap<>();
	Robot startRobot = new Robot(0, 0, 0, 1, 0);
	Queue<Robot> q = new LinkedList<>();
	visit.put(startRobot, 0);
	q.add(startRobot);
	return BFS(q);
    }

    private static int BFS(Queue<Robot> q) {
	int depth = 0;
	while (!q.isEmpty()) {
	    Robot robot = q.poll();
	    for (int i = 0; i < 8; i++) {
		Robot newRobot = robot.move(i);
		depth = newRobot.level;
		if (newRobot.canGo(depth) && newRobot != robot) {
		    visit.put(newRobot, depth);
		    q.add(newRobot);
		}

		if (robot.x1 == map.length - 1 && robot.y1 == map.length - 1
			|| (robot.x2 == map.length - 1 && robot.y2 == map.length - 1))
		    return robot.level;
	    }
	}
	return 0;
    }

    public static void main(String[] args) {
	int ans = solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
		{ 0, 0, 0, 0, 0 } });
	System.out.println(ans);
	ans = solution(new int[][] { { 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1 }, { 0, 0, 1, 0, 0, 0, 0 } });
	System.out.println(ans);

	ans = solution(new int[][] { { 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0, 0, 0 } });
	System.out.println(ans);

	ans = solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 0, 0 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1, 1, 1, 0, 0 },
		{ 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 0 } });
	System.out.println(ans);
    }
}
