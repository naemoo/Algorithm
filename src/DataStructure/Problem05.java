/*
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 * 이중우선순위 큐 (Heap)
*/
package DataStructure;

import java.util.*;

public class Problem05 {
    public static int[] solution(String[] operations) {
	int cnt = 0;
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

	for (String operation : operations) {
	    String[] oper = operation.split(" ");
	    if (oper[0].equals("I")) {
		minHeap.add(Integer.parseInt(oper[1]));
		maxHeap.add(Integer.parseInt(oper[1]));
		cnt++;
	    } else if (oper[0].equals("D")) {
		if (cnt == 0) 
		    continue;
		
		if (oper[1].charAt(0) == '-') {
		    minHeap.poll();
		} else {
		    maxHeap.poll();
		}
		cnt--;
		
		if(cnt == 0) {
		    maxHeap.clear();
		    minHeap.clear();
		}
	    }
	}

	return cnt == 0 ? new int[] { 0, 0 } : new int[] { maxHeap.poll(), minHeap.poll() };
    }

    public static void main(String[] args) {
	int[] answer = solution(new String[] { "I 16", "D 1" });
	System.out.println(Arrays.toString(answer));
	answer = solution(new String[] { "I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6" });
	System.out.println(Arrays.toString(answer));
    }
}
