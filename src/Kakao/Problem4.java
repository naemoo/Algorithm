/*
 * 2018 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/17683
 * 방금그곡(문자열 + 파싱 문제)
*/
package Kakao;

import java.util.*;

public class Problem4 {
	static class BroadCast{
		int broadCastTime;
		String title;
		StringBuilder totalMelody;
		public BroadCast(String musicInfo) {
			StringTokenizer st = new StringTokenizer(musicInfo,",");
			this.broadCastTime = getBroadCasetTime(st.nextToken(), st.nextToken());
			this.title = st.nextToken();
			totalMelody = new StringBuilder();
			getTotalMelody(st.nextToken());
		}
		
		private void getTotalMelody(String music) {
			int musicLen = 0;
			
			for(char c:music.toCharArray()) {
				if(c!='#') {
					musicLen++;
				}
			}
			int idx = 0;
			for (int i = 0; i < broadCastTime; i++) {
				totalMelody.append(music.charAt(idx));
				idx = (idx+1) % music.length();
				if((idx < music.length())
						&& (music.charAt(idx)=='#')) {
					totalMelody.append(music.charAt(idx));
					idx = (idx+1) % music.length();
				}
			}
		}
		@Override
		public String toString() {
			return broadCastTime+"\n"
					+ title +"\n"
					+totalMelody+"\n";
		}
	}
	
	
	public static String solution(String m, String[] musicinfos) throws Exception {
		List<BroadCast> broadcastMusicList = new LinkedList<>();
		List<BroadCast> result;
		
		for(String musicIfo:musicinfos) {
			broadcastMusicList.add(new BroadCast(musicIfo));
		}
		result = findTitle(m,broadcastMusicList);
		Collections.sort(result, (b1,b2)->b2.broadCastTime-b1.broadCastTime);
		try {
			return result.get(0).title;
		}
		catch(Exception e) {
			return "None";
		}
        
    }
	

	private static List<BroadCast> findTitle(String m, List<BroadCast> broadcastMusicList) {
		List<BroadCast> result = new LinkedList<>();
		
		for(BroadCast music:broadcastMusicList) {
			int idx;
			idx = music.totalMelody.indexOf(m);
			if(idx == -1)
				continue;
			idx += m.length();
			
			while(idx<music.totalMelody.length()) {
				if(music.totalMelody.charAt(idx) == '#') {
					if(music.totalMelody.indexOf(m, idx) != -1) {
						idx = music.totalMelody.indexOf(m,idx);
						idx += m.length();
						continue;
					}
					else 
						break;
				}
				result.add(music);
				break;
			}
			if(idx == music.totalMelody.length())
				result.add(music);
		}
		return result;
	}


	private static int getBroadCasetTime(String startTime, String endTime) {
		int time = Integer.parseInt(endTime.split(":")[0])
				 	-Integer.parseInt(startTime.split(":")[0]);
		int min = Integer.parseInt(endTime.split(":")[1])
			 	-Integer.parseInt(startTime.split(":")[1]);
		return time*60+min;
	}

	public static void main(String[] args) throws Exception{
		String ans;
		ans = solution("ABCDEFG", new String[] {"12:00,12:14,HELLO,CDEFGAB","13:00,13:05,WORLD,ABCDEF"});
		System.out.println(ans);
		ans = solution("CC#BCC#BCC#BCC#B", new String[] {"03:00,03:30,FOO,CC#B","04:00,04:08,BAR,CC#BCC#BCC#B"});
		System.out.println(ans);
		ans = solution("ABC", new String[] {"12:00,12:14,HELLO,C#DEFGAB","13:00,13:05,WORLD,ABCDEF"});
		System.out.println(ans);
		ans = solution("ABC", new String[] {"13:00,13:06,WORLD,ABC#ABC"});
		System.out.println(ans);
	}
}
