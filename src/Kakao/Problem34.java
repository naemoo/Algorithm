/*
 * 2019 카카오 개발자 겨울 인턴십
 * https://programmers.co.kr/learn/courses/30/lessons/64063
 * 호텔 방 배정
*/
package Kakao;

import java.util.*;

public class Problem34 {
    static class Hotel {
	HashMap<Long, Long> rooms = new HashMap<>();

	public Hotel() {
	    rooms = new HashMap<>();
	}

	long find(long room) {
	    if (!rooms.containsKey(room)) {
		rooms.put(room, room + 1);
		return room;
	    }

	    long next = rooms.get(room);
	    long empty = find(next);
	    rooms.put(room, empty);
	    return empty;
	}
    }

    public static long[] solution(long k, long[] room_number) {
	Hotel hotel = new Hotel();
	List<Long> answer = new LinkedList<>();
	for (long room : room_number) {
	    answer.add(hotel.find(room));
	}
	return answer.stream().mapToLong(Long::valueOf).toArray();
    }

    public static void main(String[] args) {
	solution(10, new long[] { 1, 3, 4, 1, 3, 1 });
    }
}
