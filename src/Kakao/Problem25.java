/*
 * 2019 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/42892
 * 길 찾기 게임 
*/
package Kakao;

import java.util.*;

public class Problem25 {
    static class Node {
	int x;
	int y;
	int number;
	Node left;
	Node right;

	public Node(int x, int y, int number) {
	    this.x = x;
	    this.y = y;
	    this.number = number;
	}

	Node addNewNode(Node newNode) {
	    if (x > newNode.x) {
		left = left == null ? newNode : left.addNewNode(newNode);
	    } else {
		right = right == null ? newNode : right.addNewNode(newNode);
	    }
	    return this;
	}
    }

    static class Tree {
	private Node root = null;
	private List<Integer> pre = new LinkedList<>();
	private List<Integer> post = new LinkedList<>();

	void addNode(Node newNode) {
	    root = root == null? newNode:root.addNewNode(newNode);
	}

	public int[][] move() {
	    preMove(root);
	    postMove(root);

	    return new int[][] { pre.stream().mapToInt(i -> i.intValue()).toArray(),
		    post.stream().mapToInt(i -> i.intValue()).toArray() };
	}

	private void postMove(Node node) {
	    if (node == null)
		return;

	    postMove(node.left);
	    postMove(node.right);
	    post.add(node.number);
	}

	private void preMove(Node node) {
	    if (node == null)
		return;

	    pre.add(node.number);
	    preMove(node.left);
	    preMove(node.right);
	}
    }

    public static int[][] solution(int[][] nodeinfo) {
	List<Node> list = new LinkedList<>();
	int num = 0;
	for (int[] node : nodeinfo) {
	    list.add(new Node(node[0], node[1], ++num));
	}
	Collections.sort(list, (n1, n2) -> n2.y - n1.y);

	Tree tree = new Tree();
	for (Node node : list) {
	    tree.addNode(node);
	}

	return tree.move();
    }

    public static void main(String[] args) {
	int[][] ans = solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 },
		{ 7, 2 }, { 2, 2 } });
	System.out.println(Arrays.deepToString(ans));
    }

}
