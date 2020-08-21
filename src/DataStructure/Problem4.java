/*
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 * 디스크 컨트롤러 
*/
package DataStructure;

import java.util.*;

public class Problem4 {

    static class Process implements Comparable<Process> {
	static int end = 0;
	int request;
	int consume;

	public Process(int request, int consume) {
	    super();
	    this.request = request;
	    this.consume = consume;
	}

	@Override
	public int compareTo(Process p) {
	    if (request <= end && p.request <= end) {
	    } else {
		return request <= end ? -1 : 0;
	    }
	    return 0;
	}

	@Override
	public String toString() {
	    return "Process [request=" + request + ", consume=" + consume + "]";
	}

    }

    public static int solution(int[][] jobs) {
	PriorityQueue<Process> q = new PriorityQueue<>();

	for (int[] job : jobs) {
	    
	}

	return 0;
    }

    public static void main(String[] args) {
	solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 1 } });
    }

}
