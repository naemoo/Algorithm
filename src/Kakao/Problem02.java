package Kakao;

/*
 * 2019 KAKAO BLIND RECRUITMENT
 * 실패율
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 */

import java.util.*;

public class Problem02 {
	static class Failure{
		int stage;
		int num;
		int noClear;
		double failRate;
		public Failure(int stage,int num,double failRate) {
			this.stage = stage;
			this.num = num;
			this.failRate = failRate;
		}
		
		public void makeFailure() {
			if(num==0) {
				failRate =0;
				return;
			}
			failRate = noClear/(double)num;
		}
		
		@Override
		public String toString() {
			return stage+","+noClear+","+num;
		}
	}
	
	public static int[] solution(int N, int[] stages) {
		Failure[] failures = new Failure[N];
		for(int i=0;i<N;i++) {
			failures[i] = new Failure(i+1, 0, 0.0);
		}
		for(int stage:stages) {
			if(stage == N+1) {
				for(int i = 0 ; i <N;i++)
					failures[i].num +=1;
				continue;
			}
			for(int i = 0 ; i <stage;i++) {
				failures[i].num +=1;
			}
			failures[stage-1].noClear += 1;
		}
		for(Failure f:failures) {
			f.makeFailure();
		}
		Arrays.sort(failures,(f1,f2)->Double.compare(f2.failRate, f1.failRate));
		return Arrays.stream(failures).mapToInt(f->f.stage).toArray();
    }
	
	public static void main(String[] args) {
		int[] answer;
		answer =solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
		for(int i:answer) {
			System.out.print(i+" ");
		}//3,4,2,1,5
		System.out.println();
		answer = solution(4, new int[]{4,4,4,4});
		for(int i:answer) {
			System.out.print(i+" ");
		}//4,1,2,3
		System.out.println();
		answer = solution(2, new int[]{1,1,1,1});
		for(int i:answer) {
			System.out.print(i+" ");
		}//1,2
		
	} 
}
