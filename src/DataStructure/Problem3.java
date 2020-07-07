/*
 * https://www.acmicpc.net/problem/13020
 * 부분집합 문제
*/
package DataStructure;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Problem3 {
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int setSize = Integer.parseInt(br.readLine());
        int subSetSize = 1<<setSize;
        int answer = 0;
        String[] set = IntStream.rangeClosed(1, setSize).mapToObj(String::valueOf).toArray(String[]::new);
        
        for(int i = 1 ; i < subSetSize ;i++){
            if(isGoodSet(set,setSize,i)){
                answer++;
            }
        }
        
        System.out.println(answer);
    }
    
    static boolean isGoodSet(String[] set,int setSize,int subSet){
    	int[] visit = new int[setSize+1];
        List<String> list = new LinkedList<>();
        
        for(int i = 0;i<setSize;i++){
            if((subSet&1<<i)!=0){
                list.add(set[i]);
            }
        }
        
        for(String str:list) {
        	for(char c:str.toCharArray()) {
        		visit[Character.digit(c,10)] += 1;
        	}
        }
        return Arrays.stream(visit).filter(i->i>1).sum()==0?true:false;
    }
}
