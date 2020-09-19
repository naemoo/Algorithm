package SamsungSWTest;

import java.util.*;
import java.io.*;

public class Problem13 {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	long answer = 0;
	int classNum = Integer.parseInt(br.readLine());
	int[] classRoom = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	int[] supervisors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

	classRoom = Arrays.stream(classRoom).map(room -> room - supervisors[0]).toArray();
	int max = Arrays.stream(classRoom).max().getAsInt();
	answer = classNum;

	long[] dp = new long[Math.max(max, supervisors[1]) + 1];
	makeDP(dp, supervisors[1]);

	for (int i = 0; i < classNum; i++) {
	    if (classRoom[i] > 0) {
		answer += dp[classRoom[i]];
	    }
	}
	System.out.println(answer);
    }

    private static void makeDP(long[] dp, int supervisors) {
	Arrays.fill(dp, 1, supervisors + 1, 1);

	for (int i = supervisors + 1; i < dp.length; i++) {
	    dp[i] = dp[i - supervisors] + 1;
	}
    }

}
