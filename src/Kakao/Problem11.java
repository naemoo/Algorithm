/*
 * 2020 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 * 기둥과 보
*/
package Kakao;

import java.util.*;


public class Problem11 {
	static int[][] pilarLocation;
	static int[][] beamLocation;
	public static int[][] solution(int n, int[][] build_frame) {
		pilarLocation = new int[n+1][n+1];
		beamLocation = new int[n+1][n+1];
		List<Frame> frames= new LinkedList<>();
				
		for(int[] command:build_frame) {
			Frame frame = new Frame(command[0], command[1],command[2], n);
			if(command[2] == 0 && command[3] == 1) {
				if(isValidPillar(frame.x, frame.y)) {
					pilarLocation[frame.x][frame.y] = 1;
					frames.add(frame);
				}
			}
			else if(command[2] == 1 && command[3] == 1){
				if(isValidBeam(frame.x, frame.y)) {
					beamLocation[frame.x][frame.y] = 1;
					frames.add(frame);
				}
			}
			else if(command[3] == 0) {
				if(isValidToDelete(frame.x, frame.y,frame.type)) {
					frames.remove(frame);
				}
			}
		}
		Collections.sort(frames);
		int[][] answer = new int[frames.size()][3];
		for (int i = 0; i < answer.length; i++) {
			answer[i][0] = frames.get(i).orgX;
			answer[i][1] = frames.get(i).orgY;
			answer[i][2] = frames.get(i).type;
		}
		return answer;
    } 

	private static boolean isValidToDelete(int x, int y,int type) {
		int[] dx = new int[]{-1,0,1,-1,0,1,-1,0,1};
		int[] dy = new int[]{-1,-1,-1,0,0,0,1,1,1};
		int[][] deleteArea = (type == 0)?pilarLocation:beamLocation;
		deleteArea[x][y] = 0;
		
		for(int i = 0 ; i < 9;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if((0<= nx && nx <deleteArea.length)
					&&(0<= ny && ny <deleteArea.length)) {
				if(pilarLocation[nx][ny] == 1) {
					if(!isValidPillar(nx, ny)) {
						deleteArea[x][y] = 1;
						return false;
					}
				}
				if(beamLocation[nx][ny] == 1) {
					if(!isValidBeam(nx, ny)) {
						deleteArea[x][y] = 1;
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean isValidBeam(int x, int y) {
		if(x+1 < pilarLocation.length && pilarLocation[x+1][y] == 1) {
			return true;
		}
		else if((x+1 < pilarLocation.length && y+1 < pilarLocation.length)
				&&(pilarLocation[x+1][y+1] == 1 )){
			return true;
		}
		else if((y+1 < beamLocation.length && 0 <= y-1)
				&& (beamLocation[x][y-1] == 1&& beamLocation[x][y+1] == 1)) {
			return true;
		}
		return false;
	}

	private static boolean isValidPillar(int x, int y) {
		if(x == pilarLocation.length-1) {
			return true;
		}
		else if(beamLocation[x][y] == 1) {
			return true;
		}
		else if((y-1>=0)&&(beamLocation[x][y-1] == 1)){
			return true;
		}
		else if(x+1 < pilarLocation.length && pilarLocation[x+1][y] == 1) {
			return true;
		}
		return false;
	}
	
	static class Frame implements Comparable<Frame>{
		int x;
		int y;
		int orgX;
		int orgY;
		int type;
		public Frame(int x, int y,int type,int n) {
			this.x = n-y;
			this.y = x;
			this.type = type;
			this.orgX = x;
			this.orgY = y;
		}
		@Override
		public int compareTo(Frame f) {
			if(Integer.compare(orgX, f.orgX)!=0) {
				return Integer.compare(orgX, f.orgX);
			}
			else if(Integer.compare(orgY, f.orgY)!=0) {
				return Integer.compare(orgY, f.orgY);
			}
			return type - f.type;
		}
		@Override
		public boolean equals(Object o) {
			Frame f= (Frame)o;
			return x==f.x?(y==f.y?(type==f.type):false):false;
		}
		@Override
		public String toString() {
			return "x : "+orgX +", y : " +orgY + ", type : " + type; 
		}
	}
	
	public static void main(String[] args) {
//		solution(5, new int[][]{{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}});
		int[][] arr = solution(5, new int[][]{{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}});
//		int[][] arr = solution(5, new int[][] {{5,0,1,1},{5,0,0,1},{5,1,1,1}});
		for(int[] a:arr) {
			for(int i:a) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
}
