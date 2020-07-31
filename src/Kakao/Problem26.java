/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17676
 * 추석 트래픽
*/
package Kakao;

import java.util.*;

public class Problem26 {
    final static int ONE_SECOND = 999;

    static class Log {
	private int endTime;
	private int precessTime;
	private int startTime;

	public Log(String line) {
	    String[] info = line.split(" ");
	    endTime = getMilliSec(info[1]);
	    precessTime = (int) (Double.parseDouble(info[2].split("s")[0]) * 1000);
	    startTime = endTime - precessTime + 1;
	}

	public boolean isCocurrentLog(int pivot) {
	    if ((pivot <= startTime && startTime <= pivot + ONE_SECOND)
		    || (pivot <= endTime && endTime <= pivot + ONE_SECOND)
		    || (startTime <= pivot && pivot + ONE_SECOND <= endTime)) {
		return true;
	    }
	    return false;
	}

	public int getCocurrent(List<Log> logs, int pivot) {
	    int cnt = 0;
	    for (Log log : logs) {
		if (log.isCocurrentLog(pivot)) {
		    cnt++;
		}
	    }
	    return cnt;
	}
    }

    public static int solution(String[] lines) {
	int max = 0;
	List<Log> logs = new LinkedList<>();
	for (String line : lines) {
	    logs.add(new Log(line));
	}

	for (Log log : logs) {
	    int pivot = log.startTime - ONE_SECOND;
	    int cnt = log.getCocurrent(logs, pivot);
	    max = Math.max(max, cnt);

	    pivot = log.endTime;
	    cnt = log.getCocurrent(logs, pivot);
	    max = Math.max(max, cnt);
	}

	return max;
    }

    private static int getMilliSec(String string) {
	String[] times = string.split("[:\\.]");

	return Integer.parseInt(times[0]) * 1000 * 60 * 60 + Integer.parseInt(times[1]) * 1000 * 60
		+ Integer.parseInt(times[2]) * 1000 + Integer.parseInt(times[3]);
    }

    public static void main(String[] args) {
	int ans = 0;
	ans = solution(new String[] { "2016-09-15 00:00:00.000 2.0s", "2016-09-15 00:00:01.001 1.00s" });
	System.out.println(ans);
    }
}
