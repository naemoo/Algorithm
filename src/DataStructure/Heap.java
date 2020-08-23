package DataStructure;

import java.util.*;

public class Heap<E extends Comparable> {
    Object[] list;
    private static final int HEAD = 1;
    private Comparator<E> comp = null;
    private int end;

    public Heap(Comparator<E> comp) {
	this();
	this.comp = comp;
    }

    public Heap() {
	this.list = new Object[101];
	list[0] = Integer.MIN_VALUE;
	this.end = 0;
    }

    public void add(E e) {
	list[++end] = e;
	int idx = end;

	while (idx > 1) {
	    E parent = getParent(idx);
	    E newNode = getElement(idx);
	    if (compare(newNode, parent) < 0) {
		swap(idx, idx / 2);
		idx = idx / 2;
	    } else {
		break;
	    }
	}
    }

    private void swap(int idx, int parent) {
	E tmp = getElement(idx);
	list[idx] = list[parent];
	list[parent] = tmp;
    }

    private int compare(E newNode, E parent) {
	return comp == null ? newNode.compareTo(parent) : comp.compare(newNode, parent);
    }

    public E pop() {
	E remove = (E) list[HEAD];
	swap(HEAD, end);
	end--;
	arrangeList(HEAD);
	return remove;
    }

    private void arrangeList(int idx) {
	if (idx * 2 > end) {
	    return;
	} else if (idx * 2 <= end && end < idx * 2 + 1) {
	    if (compare(getElement(idx), getLeftChild(idx)) > 0) {
		swap(idx * 2, idx);
	    }
	} else {
	    int pChildIdx = getPriorityChild(idx);
	    if (compare(getElement(idx), getElement(pChildIdx)) <= 0) {
		return;
	    } else {
		swap(idx, pChildIdx);
		arrangeList(pChildIdx);
	    }
	}
    }

    private E getElement(int idx) {
	return (E) list[idx];
    }

    private int getPriorityChild(int idx) {
	return compare(getLeftChild(idx), getRightChild(idx)) >= 0 ? idx * 2 + 1 : idx * 2;
    }

    private E getParent(int idx) {
	return (E) list[idx / 2];
    }

    private E getLeftChild(int idx) {
	return (E) list[idx * 2];
    }

    private E getRightChild(int idx) {
	return (E) list[idx * 2 + 1];
    }

    public boolean hasNext() {
	return end >= 1 ? true : false;
    }

    public static void main(String[] args) {
	Heap<Integer> minHeap = new Heap<>();
	Heap<Integer> maxHeap = new Heap<>(Comparator.reverseOrder());
	minHeap.add(8);
	minHeap.add(2);
	minHeap.add(3);
	minHeap.add(1);
	while (minHeap.hasNext()) {
	    System.out.println(minHeap.pop());
	}
    }

}
