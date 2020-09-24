package SamsungSWTest;

import java.util.*;
import java.io.*;

public class Problem16 {
    static int[][] map;
    static int[][] d = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static HashSet<Passenger> passengers;

    static class Taxi {
	int[] pos;
	int fuel;
	int m;

	public Taxi(int x, int y, int fuel) {
	    this.pos = new int[] { x, y };
	    this.fuel = fuel;
	    m = 0;
	}

	@Override
	public int hashCode() {
	    return Arrays.hashCode(pos);
	}

	@Override
	public boolean equals(Object obj) {
	    Taxi other = (Taxi) obj;
	    return Arrays.equals(pos, other.pos) ? true : false;
	}

	@Override
	public String toString() {
	    return "Taxi [pos=" + Arrays.toString(pos) + ", fuel=" + fuel + ", m=" + m + "]";
	}

	public Taxi move(int i) {
	    Taxi newTaxi = null;
	    try {
		int nx = pos[0] + d[i][0];
		int ny = pos[1] + d[i][1];
		if (map[nx][ny] == 0) {
		    newTaxi = new Taxi(nx, ny, this.fuel - 1);
		    newTaxi.m = this.m + 1;
		}
	    } catch (ArrayIndexOutOfBoundsException e) {
	    }
	    return newTaxi;
	}

    }

    static class Passenger {
	int[] dpt;
	int[] arr;
	int distance;

	public Passenger(String str) {
	    int[] info = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
	    dpt = new int[] { info[0] - 1, info[1] - 1 };
	    arr = new int[] { info[2] - 1, info[3] - 1 };
	    distance = Integer.MAX_VALUE;
	}

	@Override
	public String toString() {
	    return "Passenger [dpt=" + Arrays.toString(dpt) + ", arr=" + Arrays.toString(arr) + ", distance=" + distance
		    + "]";
	}

	@Override
	public int hashCode() {
	    return Arrays.hashCode(dpt);
	}

	@Override
	public boolean equals(Object obj) {
	    Passenger other = (Passenger) obj;
	    if (!Arrays.equals(arr, other.arr))
		return false;
	    if (!Arrays.equals(dpt, other.dpt))
		return false;
	    return true;
	}
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] infos = br.readLine().split("\\s");
	map = new int[Integer.parseInt(infos[0])][Integer.parseInt(infos[0])];
	passengers = new HashSet<>();

	for (int i = 0; i < map.length; i++) {
	    map[i] = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
	}

	String[] taxiStart = br.readLine().split("\\s");
	Taxi taxi = new Taxi(Integer.parseInt(taxiStart[0]) - 1, Integer.parseInt(taxiStart[1]) - 1,
		Integer.parseInt(infos[2]));

	for (int i = 0; i < Integer.parseInt(infos[1]); i++) {
	    passengers.add(new Passenger(br.readLine()));
	}

	for (int i = 0; i < Integer.parseInt(infos[1]); i++) {
	    Passenger passenger = getShortestPassenger(taxi);
	    if (passenger == null)
		break;
	    taxi.fuel -= passenger.distance;
	    taxi.pos = passenger.dpt;
	    if (taxi.fuel < 0) {
		break;
	    }
	    if (!goDestination(taxi, passenger)) {
		break;
	    }
	}
	System.out.println(passengers.size() == 0 ? taxi.fuel : -1);

    }

    private static boolean goDestination(Taxi taxi, Passenger passenger) {
	HashSet<Taxi> visit = new HashSet<>();
	Queue<Taxi> q = new LinkedList<>();
	Taxi newTaxi = new Taxi(taxi.pos[0], taxi.pos[1], 0);
	q.add(newTaxi);
	visit.add(newTaxi);

	while (!q.isEmpty()) {
	    Taxi t = q.poll();
	    for (int i = 0; i < 4; i++) {
		Taxi nt = t.move(i);
		if (nt != null && !visit.contains(nt)) {
		    q.add(nt);
		    visit.add(nt);
		    if (Arrays.equals(nt.pos, passenger.arr)) {
			taxi.fuel -= nt.m;
			if (taxi.fuel < 0) {
			    return false;
			}
			taxi.pos = passenger.arr;
			taxi.fuel += (nt.m * 2);
			return true;
		    }
		}
	    }
	}
	return false;
    }

    private static Passenger getShortestPassenger(Taxi taxi) {
	HashSet<Taxi> visit = new HashSet<>();
	HashSet<Taxi> dest = new HashSet<>();
	Queue<Taxi> q = new LinkedList<>();
	int cnt = 0;
	q.add(taxi);
	visit.add(taxi);
	Passenger answer = null;

	for (Passenger p : passengers) {
	    dest.add(new Taxi(p.dpt[0], p.dpt[1], -1));
	    if (Arrays.equals(taxi.pos, p.dpt)) {
		p.distance = 0;
		passengers.remove(p);
		return p;
	    }
	}

	while (!q.isEmpty()) {
	    Taxi t = q.poll();
	    for (int i = 0; i < 4; i++) {
		Taxi nt = t.move(i);
		if (nt != null && !visit.contains(nt)) {
		    if (dest.contains(nt)) {
			for (Passenger p : passengers) {
			    if (Arrays.equals(p.dpt, nt.pos)) {
				p.distance = Integer.min(nt.m, p.distance);
				cnt++;
				if (answer != null) {
				    if (answer.distance < p.distance) {
					passengers.remove(answer);
					return answer;
				    } else if (!Arrays.equals(answer.dpt, p.dpt)) {
					if (answer.dpt[0] > p.dpt[0])
					    answer = p;

					if (answer.dpt[0] == p.dpt[0] && answer.dpt[1] > p.dpt[1])
					    answer = p;

				    }
				} else {
				    answer = p;
				}
				break;
			    }
			}
		    }
		    if (cnt == dest.size())
			break;

		    q.add(nt);
		    visit.add(nt);
		}
	    }
	}
	passengers.remove(answer);
	return answer;
    }

}
