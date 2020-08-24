/*
 * https://www.acmicpc.net/problem/2003
 * 백준(수들의 합 2) - 투포인터
*/
package Search;

import java.io.*;
import java.util.*;

public class Problem21 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] command = br.readLine().split(" ");
	int n = Integer.parseInt(command[0]);
	int m = Integer.parseInt(command[1]);
	int answer = 0;
	StringTokenizer st = new StringTokenizer(br.readLine()," ");
	int[] sequence = new int[n];
	for (int i = 0; i < sequence.length; i++) {
	    sequence[i] = Integer.parseInt(st.nextToken());
	}
	int end = 0;
	int sum = 0;

	for (int start = 0; start < sequence.length; start++) {
	    while (sum < m && end < sequence.length) {
		sum += sequence[end];
		end++;
	    }
	    if (sum == m)
		answer++;

	    sum -= sequence[start];
	}
	System.out.println(answer);
    }
}
