/*
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 * 디스크 컨트롤러 
*/
package DataStructure;

import java.util.*;
import java.util.stream.*;

public class Problem04 {

    static class Process implements Comparable<Process> {
	int request;
	int consume;

	public Process(int request, int consume) {
	    super();
	    this.request = request;
	    this.consume = consume;
	}

	@Override
	public int compareTo(Process p) {
	    return Integer.compare(consume, p.consume);
	}

	public int getWorkingTime(int end) {
	    return end - request + consume;
	}
    }

    public static int solution(int[][] jobs) {
	PriorityQueue<Process> waitQ = new PriorityQueue<>();
	Queue<Process> processes = Arrays.stream(jobs)
		.sorted((a,b)->Arrays.compare(a, b))
		.flatMap(job -> Stream.of(new Process(job[0], job[1])))
		.collect(Collectors.toCollection(LinkedList::new));
	int answer = 0;
	int end = 0;

	while (!processes.isEmpty() || !waitQ.isEmpty()) {

	    while (!processes.isEmpty()) {
		Process tmp = processes.peek();
		if (tmp.request <= end) {
		    waitQ.add(processes.poll());
		} else {
		    break;
		}
	    }

	    if (waitQ.isEmpty()) {
		Process tmp = processes.poll();
		answer += tmp.consume;
		end = tmp.request;
		end += tmp.consume;
	    } else {
		Process tmp = waitQ.poll();
		answer += tmp.getWorkingTime(end);
		end += tmp.consume;
	    }
	}
	return answer / jobs.length;
    }

    public static void main(String[] args) {
	solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } });
    }

}
