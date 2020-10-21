package Utils;

import java.util.*;

public class HashMapList<T, E> {
    HashMap<T, HashSet<E>> dict;

    public HashMapList() {
	dict = new HashMap<>();
    }

    public void put(T key, E value) {
	dict.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    public HashSet<E> get(T key) {
	return dict.get(key);
    }

    public boolean containsKey(T key) {
	return dict.containsKey(key);
    }

    public boolean containsKeyValues(T key, E value) {
	return dict.get(key).contains(value);
    }

    @Override
    public String toString() {
	return dict.toString();
    }
}
