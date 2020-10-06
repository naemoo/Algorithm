package BackTracking;

import java.io.*;

public class Problem11 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	System.out.println(getQueenNum(n));
    }

    private static int getQueenNum(int n) {
	return dfs(0, new int[n + 1]);
    }

    private static int dfs(int d, int[] cols) {
	if (d == cols.length - 1) {
	    return 1;
	}
	int cnt = 0;
	for (int i = 1; i < cols.length; i++) {
	    if (canPutQueen(cols, i, d + 1)) {
		cols[i] = d + 1;
		cnt += dfs(d + 1, cols);
		cols[i] = 0;
	    }
	}
	return cnt;
    }

    private static boolean canPutQueen(int[] cols, int c, int r) {
	if (cols[c] != 0) {
	    return false;
	}
	for (int i = 1; i < cols.length; i++) {
	    if (cols[i] != 0 && Math.abs(cols[i] - r) == Math.abs(i - c))
		return false;
	}
	return true;
    }
}
