package DivideAndConquer;

/*
 * 백준 : 2630번(색종이 만들기)
*/
import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
	static int[][] arr;
	static int scnt = 0;
	static int zcnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for(int i = 0 ; i < n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <n;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		cut(0,n,0,n);
		System.out.println(zcnt);
		System.out.println(scnt);
	}
	static boolean isProper(int width_start,int width, int height_start, int height) {
		int tmp = arr[width_start][height_start];
		for(int i = width_start;i<width;i++) {
			for(int j = height_start; j<height;j++) {
				if(arr[i][j] != tmp)
					return false;
			}
		}
		if(tmp == 0)
			zcnt++;
		else
			scnt++;
		return true;
	}
	static void cut(int width_start,int width, int height_start, int height) {
		if(!isProper(width_start, width, height_start, height)) {
			cut(width_start,width_start+(width-width_start)/2,height_start,height_start+(height-height_start)/2); 
			cut(width_start,width_start+(width-width_start)/2,height_start+(height-height_start)/2,height);
			cut((width-width_start)/2+width_start,width,height_start,height_start+(height-height_start)/2);
			cut((width-width_start)/2+ width_start,width,(height-height_start)/2+height_start,height);
		}
	}
}
