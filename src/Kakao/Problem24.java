/*
 * https://programmers.co.kr/learn/courses/30/lessons/17678
 * 2018 KAKAO BLIND RECRUITMENT
 * 셔틀버스 
*/
package Kakao;

import java.util.*;

public class Problem24 {

    public static String solution(int n, int t, int m, String[] timetable) {
	PriorityQueue<String> crews = new PriorityQueue<String>(Arrays.asList(timetable));
	String curTime = "09:00";
	String nextTime = "09:00";
	String crew = "";
	String answer = null;

	for (int i = 0; i < n; i++) {
	    int cnt = 0;
	    curTime = nextTime;
	    nextTime = getNextTime(curTime, t);
	    while (!crews.isEmpty()) {
		crew = crews.peek();
		if (curTime.compareTo(crew) >= 0) {
		    cnt++;
		    crews.poll();
		    if (cnt >= m)
			break;
		} else {
		    break;
		}
	    }
	    if (crews.isEmpty()) {
		if(i == n-1)
		    answer = cnt < m ? curTime : getNextTime(crew, -1);
	    }
	}
	if (answer == null)
	    answer = curTime;

	return answer;
    }

    private static String getNextTime(String curTime, int t) {
	int time = Integer.parseInt(curTime.split(":")[0]);
	int min = Integer.parseInt(curTime.split(":")[1]);
	min += t;
	if (min >= 60) {
	    time += min / 60;
	    min = min % 60;
	}

	if (min < 0) {
	    time -= 1;
	    min += 60;
	}

	return String.format("%02d", time) + ":" + String.format("%02d", min);
    }

    public static void main(String[] args) {
	String ans;
	ans = solution(1, 1, 5, new String[] { "08:00", "08:01", "08:02", "08:03" });
	System.out.println(ans);
	ans = solution(2, 10, 2, new String[] { "09:10", "09:10", "08:00" });
	System.out.println(ans);
	ans = solution(2, 1, 2, new String[] { "09:00", "09:00", "09:00", "09:00" });
	System.out.println(ans);
	ans = solution(1, 1, 5, new String[] { "00:01", "00:01", "00:01", "00:01", "00:01" });
	System.out.println(ans);
	ans = solution(1, 1, 1, new String[] { "23:59" });
	System.out.println(ans);
	ans = solution(10, 60, 45, new String[] { "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
		"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59" });
	System.out.println(ans);
	ans = solution(2, 2, 3, new String[] { "09:00","09:11","02:11","02:12","02:13","02:14" });
	System.out.println(ans);
    }

}
