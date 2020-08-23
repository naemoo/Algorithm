package DataStructure;

import java.util.*;

public class Heap<E extends Comparable> {
    List<E> list;
    private static final int HEAD = 1;
    private Comparator<E> comp = null;

    public Heap(Comparator<E> comp) {
	this();
	this.comp = comp;
    }

    public Heap() {
	list = new LinkedList<>();
	list.add(null);
    }

    public void add(E e) {
	list.add(e);
	int idx = list.size() - 1;

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
	E tmp = list.set(idx, list.get(parent));
	list.set(parent, tmp);
    }

    private int compare(E newNode, E parent) {
	return comp == null ? newNode.compareTo(parent) : comp.compare(newNode, parent);
    }

    public E poll() {
	E remove = list.remove(1);
	if (list.size() -1 > 1) {
	    list.add(1, list.remove(list.size() - 1));
	    arrangeList(HEAD);
	}
	return remove;
    }

    private void arrangeList(int idx) {
	int end = list.size() - 1;

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
	return list.get(idx);
    }

    private int getPriorityChild(int idx) {
	return compare(getLeftChild(idx), getRightChild(idx)) >= 0 ? idx * 2 + 1 : idx * 2;
    }

    private E getParent(int idx) {
	return list.get(idx / 2);
    }

    private E getLeftChild(int idx) {
	return list.get(idx * 2);
    }

    private E getRightChild(int idx) {
	return list.get(idx * 2 + 1);
    }

    public boolean hasNext() {
	return list.size() - 1 >= 1 ? true : false;
    }

    public static void main(String[] args) {
	Heap<Integer> minHeap = new Heap<>();
	Heap<Integer> maxHeap = new Heap<>(Comparator.reverseOrder());
	maxHeap.add(8);
	maxHeap.add(2);
	maxHeap.add(3);
	maxHeap.add(1);
	while (maxHeap.hasNext()) {
	    System.out.println(maxHeap.poll());
	}
    }

}
