package DivideAndConquer;

import java.io.*;
import java.util.StringTokenizer;

public class Problem2 {
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for(int i = 0 ; i < n;i++) {
			arr[i] = br.readLine().toCharArray();
		}
		DFS(0, n, 0, n);
		System.out.println(sb);
	}
	public static boolean isProper(int hst,int h,int wst,int w) {
		int tmp = arr[wst][hst];
		for(int i = wst;i<w;i++) {
			for(int j = hst;j<h;j++)
				if(arr[i][j] != tmp)
					return false;
		}
		if(tmp == '0')
			sb.append(0);
		else
			sb.append(1);
		return true;
	}
	
	
	public static void DFS(int hst,int h,int wst,int w) {
		if(!isProper(wst, w, hst, h)) {
			sb.append("(");
			DFS(hst, (h-hst)/2+hst, wst, (w-wst)/2+wst);//1
			DFS(hst, (h-hst)/2+hst, (w-wst)/2+wst, w);//2
			DFS(hst+(h-hst)/2, h, wst, (w-wst)/2+wst);//3
			DFS((h-hst)/2+hst, h,(w-wst)/2+wst, w);//4
			sb.append(")");
		}
	}
}
