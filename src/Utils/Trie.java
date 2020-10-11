package Utils;

import java.util.*;

public class Trie {
    class Node {
	HashMap<Character, Node> child;
	boolean isEnd;

	Node() {
	    child = new HashMap<>();
	    isEnd = false;
	}
    }

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
