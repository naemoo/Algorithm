package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class Problem4 {
	static int n;
	static int[] dx = {0,1,0,-1};//������ �Ʒ� �� ����
	static int[] dy = {1,0,-1,0};
	static int[][] map;
	static int[][] visit;
	static Queue<Point> q = new LinkedList<>();
	static ArrayList al = new ArrayList();
	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
	        int call_num = 0;
	        n = scan.nextInt();    
	        
	        map = new int[n][n];
	        visit = new int[n][n];
	        String temp;
	        
	        for(int i=0;i<n;i++) {
	            temp = scan.next();
	            for(int j=0;j<n;j++) 
	                map[i][j] = temp.charAt(j)-'0'; 
	        }
	        for(int i=0;i<n;i++)
	            for(int j=0;j<n;j++) 
	                if(map[i][j] == 1 && visit[i][j] == 0) {
	                    BFS(i,j);
	                    call_num++;
	                }
	        
	        System.out.println(call_num);
	        
	        Collections.sort(al);
	        for(int i=0;i<al.size();i++)
	            System.out.println(al.get(i));
	}
	static void BFS(int i, int j) 
    {
        int nx,ny;
        int local_cnt = 1;
        q.offer(new Point(i,j));
        visit[i][j] = 1;
        
        while(q.isEmpty()==false) 
        {
            Point now;
            now = q.poll();
            
            for(int h=0;h<4;h++) {
            nx = now.x+dx[h];
            ny = now.y+dy[h];
            
            if(nx>=0&&ny>=0&&nx<n&&ny<n) {
            if(map[nx][ny] == 1 && visit[nx][ny]==0) {
                q.offer(new Point(nx,ny));
                visit[nx][ny] = 1;
                local_cnt++;
                    }
                }
            }
        }
        al.add(local_cnt);
    }
}

/*
for(int i = 0;i<n;i++) {
	for(int j = 0 ; j<n;j++) {
		if(apartment[i][j] == 1)
			list.add(BFS(i, j));
	}
}
Collections.sort(list);
System.out.println(adr-1);
for(int i = 0 ; i < list.size();i++) {
	System.out.println(list.get(i));
}

for(int i = 0;i<n;i++) { 
	for(int j = 0 ; j<n;j++) 
		System.out.print(apartment[i][j]+ " ");
	System.out.println();
}*/
/*
static int BFS(int x, int y) {
	adr++;//����
	int count = 0;
	Queue<Dot> q = new LinkedList<>();
	int nx,ny;
	q.add(new Dot(x,y));
	while(!q.isEmpty()) {
		x = q.peek().x;
		y = q.poll().y;
		
		for(int i = 0 ; i < 4;i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx<0|| nx >=n|| ny<0 || ny>=n)
				continue;
			if(apartment[nx][ny] != 1)
				continue;
			q.add(new Dot(nx,ny));
			apartment[nx][ny] = adr;
			count++;
		}
	}
	return count;
}*/


