package Search;
/*
 * 프로그래머스 : 여행경로(DFS/BFS) 문제
*/
class Solution17 {
	String[][] tickets;
	int[] visit;
	String[] tmp;
	String[] answer;
	int n;
    public String[] solution(String[][] tickets) {
    	this.tickets = tickets;
    	n = tickets.length;
    	visit = new int[n];
    	tmp = new String[n+1];
    	DFS("ICN",0);
        return answer;
    }
    public void DFS(String nextDest,int d) {
    	if(d == n) {
    		tmp[n] = nextDest;
    		if(answer == null) {
    			answer =tmp.clone();
    		}
    		else {
    			for(int i = 0 ; i <=n;i++)
    				if(answer[i].compareTo(tmp[i]) != 0) {
    					if(answer[i].compareTo(tmp[i])>0)
    						answer = tmp.clone();
    					break;
    				}
    		}
    		return;
    	}
    	for(int i = 0 ; i < n;i++) {
    		if(tickets[i][0].equals(nextDest)&& visit[i] == 0) {
    			visit[i] = 1;
    			tmp[d] = nextDest;
    			DFS(tickets[i][1],d+1);
    			tmp[d] = null;
    			visit[i] = 0;
    		}
    	}
    }
}

public class Problem17 {
	public static void main(String[] args) {
		Solution17 s = new Solution17();
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		System.out.print("answer : ");
		for(String str : s.solution(tickets))
			System.out.print(str+" ");
	}
}
