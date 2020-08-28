/*
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 * 전화번호 목록
*/
package DataStructure;

import java.util.*;

public class Problem06 {
    static class Node {
	HashMap<Character, Node> child;
	boolean isEnd;

	Node() {
	    child = new HashMap<>();
	    isEnd = false;
	}
    }

    static class Trie {
	Node head;

	Trie() {
	    head = new Node();
	}

	boolean add(String word) {
	    Node cur = head;
	    for (int i = 0; i < word.length(); i++) {
		cur = cur.child.computeIfAbsent(word.charAt(i), ch -> new Node());

		if (cur.isEnd)
		    return false;
	    }
	    return (cur.isEnd = true);
	}
    }

    public static boolean solution(String[] phoneBook) {
	Trie trie = new Trie();
	Arrays.sort(phoneBook, (s1, s2) -> s1.length() - s2.length());

	for (String phoneNumber : phoneBook) {
	    if (!trie.add(phoneNumber))
		return false;
	}
	return true;
    }

    public static void main(String[] args) {
	solution(new String[] {"123","456","789"});
		
    }
}
