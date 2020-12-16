package Utils;

import java.util.*;
import java.io.*;

public class BIT {
    private long[] tree;

    public BIT(int size) {
	this.tree = new long[size + 1];
    }

    public void add(int idx, long num) {
	idx++;

	while (idx < tree.length) {
	    tree[idx] += num;
	    idx += (idx & -idx);
	}
    }

    public long sum(int idx) {
	idx++;
	long ret = 0;
	while (idx > 0) {
	    ret += tree[idx];
	    idx &= idx - 1;
	}
	return ret;
    }

    public long rangeSum(int st, int to) {
	return sum(to) - sum(st - 1);
    }
}
