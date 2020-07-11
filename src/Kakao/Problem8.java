/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17686
 * 파일명 정렬(정렬 문제)
*/
package Kakao;

import java.util.*;

class Solution {
    class File implements Comparable<File>{
    	String orgName;
        String head ="";
        String number ="";
        String tail = "";

        public File(String orgName) {
        	this.orgName = orgName;
        	boolean isNumberStart= false;
        	boolean isTailStart = false;
        	for(char c:orgName.toCharArray()) {
        		if(!isNumberStart && !isTailStart) {
        			if(Character.isDigit(c)) {
        				isNumberStart = true;
        			}
        			else {
        				head = head + c;
        			}
        		}
        		if(isNumberStart && !isTailStart) {
        			if(Character.isDigit(c)) {
        				number = number + c;
        			}
        			else {
        				isTailStart = true;
        			}
        		}
        	}
        }

		@Override
		public int compareTo(File f) {
			if(head.compareToIgnoreCase(f.head) != 0) {
				return head.compareToIgnoreCase(f.head);
			}
			if(Integer.parseInt(number) != Integer.parseInt(f.number)) {
				return Integer.compare(Integer.parseInt(number), Integer.parseInt(f.number));
			}
			return 0;
		}
    }

    public String[] solution(String[] files) {
    	List<File> list = new LinkedList<>();
    	for(String file:files) {
    		list.add(new File(file));
    	}
    	Collections.sort(list);
    	return list.stream().map(str->str.orgName).toArray(String[]::new);
    }
}

public class Problem8 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] answers =
                solution.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        for(String answer:answers){
            System.out.println(answer);
        }
    	
    }
}
