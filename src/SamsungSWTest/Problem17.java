package SamsungSWTest;

import java.util.*;
import java.io.*;

public class Problem17 {
    static int[][] map;
    static boolean[][] visit;
    static int n;
    static int m;
    static int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    static class Point {
	int x;
	int y;
	int d;

	public Point(int x, int y, int d) {
	    this.x = x;
	    this.y = y;
	    this.d = d;
	}

	@Override
	public String toString() {
	    return "Point [x=" + x + ", y=" + y + "]";
	}

	public Point move(int i) {
	    Point np = null;
	    int nx = x + dir[i][0];
	    int ny = y + dir[i][1];
	    try {
		if (!visit[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == 2)) {
		    np = new Point(nx, ny, d + 1);
		}
	    } catch (ArrayIndexOutOfBoundsException e) {

	    }
	    return np;
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	List<Point> viruses = new LinkedList<>();
	String[] nm = br.readLine().split("\\s");
	n = Integer.parseInt(nm[0]);
	m = Integer.parseInt(nm[1]);
	map = new int[n][n];

	for (int i = 0; i < n; i++) {
	    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	    for (int j = 0; j < n; j++) {
		map[i][j] = Integer.parseInt(st.nextToken());
		if (map[i][j] == 2)
		    viruses.add(new Point(i, j, 0));
	    }
	}

	List<List<Point>> combination = Combination.combination(m, viruses);

	int answer = combination.size() == 0 ? 0 : Integer.MAX_VALUE;

	for (List<Point> candidate : combination) {
	    Queue<Point> q = new LinkedList<>(candidate);
	    visit = new boolean[n + 1][n + 1];
	    for (Point virus : candidate) {
		visit[virus.x][virus.y] = true;
	    }

	    if (isRight()) {
		answer = Integer.min(spreadVirus(q), answer);
	    }
	}

	System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static boolean isRight() {
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (map[i][j] == 0) {
		    if (!visit[i][j]) {
			return false;
		    }
		}
	    }
	}
	return true;
    }

    private static int spreadVirus(Queue<Point> q) {
	int tic = 0;

	while (!q.isEmpty()) {
	    Point cur = q.poll();

	    for (int i = 0; i < 4; i++) {
		Point np = cur.move(i);
		if (np != null) {
		    q.add(np);
		    visit[np.x][np.y] = true;
		    if (map[np.x][np.y] == 0) {
			tic = Integer.max(tic, np.d);
		    }
		}
	    }

	}
	return tic;
    }

    static class Combination {
	public static <E> List<List<E>> combination(int r, List<E> list) {
	    int n = list.size();
	    List<List<E>> answer = new LinkedList<>();
	    boolean[] visit = new boolean[n];

	    if (r != 0)
		dfs(0, 0, r, visit, answer, new LinkedList<>(), list);
	    return answer;
	}

	private static <E> void dfs(int d, int start, int r, boolean[] visit, List<List<E>> answer, LinkedList<E> list,
		List<E> eList) {
	    if (d == r) {
		answer.add(new LinkedList<>(list));
		return;
	    }

	    for (int i = start; i < visit.length; i++) {
		if (!visit[i]) {
		    visit[i] = true;
		    list.add(eList.get(i));
		    dfs(d + 1, i + 1, r, visit, answer, list, eList);
		    list.remove(list.size() - 1);
		    visit[i] = false;
		}
	    }
	}
    }
}
