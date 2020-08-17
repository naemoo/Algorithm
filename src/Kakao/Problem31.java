package Kakao;

import java.util.*;

public class Problem31 {
    static class Node {
	HashMap<Character, Node> childNode;
	HashMap<Character, Node> reverseChild;
	int size;
	int reverseSize;

	public Node() {
	    childNode = new HashMap<>();
	    reverseChild = new HashMap<>();
	    size = 0;
	    reverseSize = 0;
	}
    }

    static class Tries {
	Node head;

	public Tries() {
	    head = new Node();
	}

	void add(String word) {
	    Node cur = this.head;
	    for (int i = 0; i < word.length(); i++) {
		cur.size += 1;
		cur = cur.childNode.computeIfAbsent(word.charAt(i), ch -> new Node());
	    }
	    cur = this.head;
	    for (int i = word.length() - 1; i >= 0; i--) {
		cur.reverseSize += 1;
		cur = cur.reverseChild.computeIfAbsent(word.charAt(i), ch -> new Node());
	    }
	}

	int getCountNumber(String word, Node head) {
	    Node cur = head;

	    if (word.charAt(0) != '?') {
		for (int i = 0; i < word.length(); i++) {
		    if (word.charAt(i) == '?') {
			long qcnt = word.chars().filter(ch -> ch == '?').count();
			return cur.size;
		    }
		    cur = cur.childNode.get(word.charAt(i));
		    if (cur == null)
			return 0;
		}
	    } else {
		for (int i = word.length()-1; i >= 0; i--) {
		    if (word.charAt(i) == '?') {
			long qcnt = word.chars().filter(ch -> ch == '?').count();
			if (qcnt == word.length())
			    return cur.size;
			return cur.reverseSize;
		    }
		    cur = cur.reverseChild.get(word.charAt(i));
		    if(cur == null)
			return 0;
		}
	    }
	    return 0;
	}
    }

    public static int[] solution(String[] words, String[] queries) {
	HashMap<Integer, Tries> tries = new HashMap<>();
	int[] answer = new int[queries.length];

	for (String word : words) {
	    tries.computeIfAbsent(word.length(), t -> new Tries());
	    tries.get(word.length()).add(word);
	}

	for (int i = 0; i < queries.length; i++) {
	    Tries cur = tries.get(queries[i].length());
	    if (cur == null)
		continue;
	    answer[i] = cur.getCountNumber(queries[i], cur.head);
	}
	System.out.println(Arrays.toString(answer));
	return answer;
    }

    public static void main(String[] args) {
	solution(new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" },
		new String[] { "fro??", "????o", "fr???", "fro???", "?????" });
    }

}
