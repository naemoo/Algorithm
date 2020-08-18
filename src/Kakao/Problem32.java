/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17685
 * 자동완성
*/
package Kakao;

import java.util.*;

public class Problem32 {

    static class Node {
	HashMap<Character, Node> child;
	int size;
	boolean isEnd;

	public Node() {
	    this.child = new HashMap<>();
	    this.size = 0;
	    this.isEnd = false;
	}
    }

    static class Tries {
	Node head;

	public Tries() {
	    this.head = new Node();
	}

	public void add(String word) {
	    Node cur = this.head;
	    for (int i = 0; i < word.length(); i++) {
		cur.size += 1;
		cur = cur.child.computeIfAbsent(word.charAt(i), ch -> new Node());
	    }
	    cur.isEnd = true;
	}

	public int getMinimalNumber(String word) {
	    Node cur = this.head;
	    for (int i = 0; i < word.length(); i++) {
		if (cur.size == 1)
		    return cur.isEnd ? i + 1 : i;

		cur = cur.child.get(word.charAt(i));
		if (cur == null)
		    return 0;
	    }
	    return cur.isEnd ? word.length() : -1;
	}
    }

    public static int solution(String[] words) {
	Tries tries = new Tries();
	int[] answers = new int[words.length];
	int answer = 0;
	for (String word : words) {
	    tries.add(word);
	}
	int idx = 0;
	for (String word : words) {
	    answer += tries.getMinimalNumber(word);
	    answers[idx++] = tries.getMinimalNumber(word);
	}
	System.out.println(answer);
	System.out.println(Arrays.toString(answers));
	return answer;
    }

    public static void main(String[] args) {
	solution(new String[] { "go", "gone", "guild" });
    }

}
