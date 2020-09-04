package Greedy;

import java.io.*;
import java.util.*;

public class Problem08 {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
	int testCase = Integer.parseInt(br.readLine());

	for (int i = 0; i < testCase; i++) {
	    String[] info = br.readLine().split(" ");
	    for (int j = 0; j < Integer.parseInt(info[1]); j++) {
		String[] dependency = br.readLine().split(" ");
		map.computeIfAbsent(Integer.parseInt(dependency[1]),k -> new HashMap<>())
			.put(Integer.parseInt(dependency[0]), Integer.parseInt(dependency[2]));
	    }
	    int[] answer = spreadVirus(Integer.parseInt(info[0]), Integer.parseInt(info[3]), map);
	    System.out.println(answer[0] + " " + answer[1]);
	}
    }

    private static int[] spreadVirus(int computerNumber, int start, HashMap<Integer, HashMap<Integer, Integer>> map) {
	int[] touch = new int[computerNumber + 1];
	Arrays.fill(touch, Integer.MAX_VALUE);
	touch[start] = -1;
	int near = start;

	for (int t = 1; t < computerNumber - 1; t++) {
	    
	    // touch 초기화
	    for(Map.Entry<Integer,Integer> entry : map.get(near).entrySet()) {
		
	    }
	    
	    
	    // near 뽑기

	}

	return new int[] {};
    }
}
