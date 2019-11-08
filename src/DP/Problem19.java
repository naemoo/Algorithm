package DP;

/*
 * FloydWarshall 알고리즘
 * 가중치가 있는 그래프 일 때, 최단 경로 구하는 알고리즘
*/

public class Problem19 {
	static int INF = 1000000;// 갈 수 없는 곳은 무한대
	static int[][] P;// path함수에서 사용하기 위하여
	
	public static void printArray(int[][] D) {//행렬 출력
		for(int i = 1 ; i <D.length;i++) {
			for(int j=1;j<D.length;j++) {
				System.out.print(D[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] arrayCopy(int[][] src){//행렬 복사(깊은 복사)
		int n = src.length;
		int[][] arr= new int[n][n];
		
		for(int i = 0 ; i <n;i++)
			arr[i] = src[i].clone();
		return arr;
	}
	
	public static void floyd2(int n,int[][] D) {//Floyd 알고리즘 구현 부분
		int i,j,k;
		for(k = 1;k<=n;k++) 
			for(i=1;i<=n;i++)
				for(j=1;j<=n;j++) 
					if(D[i][k] + D[k][j] < D[i][j]) {
						P[i][j] = k;
						D[i][j] = D[i][k] + D[k][j];
					}
	}
	
	public static void path(int q, int r) {// v(q) -> v(r) 지나간 경로 출력
		if(P[q][r]!=0) {
			path(q,P[q][r]);
			System.out.print("v : " + P[q][r]+" ");
			path(P[q][r],r);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("1번 교재 3.4 문제 + 3.5path문제");
		int[][] W = new int[][] {{0,0,0,0,0,0},{0,0,1,INF,1,5},{0,9,0,3,2,INF},{0,INF,INF,0,4,INF},{0,INF,INF,2,0,3},{0,3,INF,INF,INF,0}};
		//W -> 인접행렬, 0인덱스 생략
		int[][] D = arrayCopy(W);
		//D -> W복제본 슈도코드는 floyd함수 안에서 복제하지만 main에서 복제하였습니다.  
		int n = W.length-1;
		P = new int[n+1][n+1];//경로 저장하는 배열 path함수를 위해 전역으로 선언
		
		floyd2(n, D);
		
		printArray(D);
		
		System.out.println();
		System.out.println("경로 나타내기");
		System.out.print("v : 5 ");
		path(5,3);
		System.out.print("v : 3");
		System.out.println("을 지나감");
		System.out.println();
		
		System.out.println("4번 임의의 자작 데이터 생성");
		W = new int[][] {{0,0,0,0,0,0},{0,0,2,3,1,10},{0,INF,0,INF,2,INF},{0,8,INF,0,1,1},{0,INF,INF,INF,0,3},{0,7,4,INF,INF,0}};
		//W -> 인접행렬, 0인덱스 생략
		D = arrayCopy(W);
		//D -> W복제본 슈도코드는 floyd함수 안에서 복제하지만 main에서 복제하였습니다.
		P = new int[n+1][n+1];//경로 저장하는 배열
		
		floyd2(n,D);
		
		printArray(D);
		
		System.out.println();
		System.out.println("경로 나타내기 v2 -> v3");
		System.out.print("v : 2 ");
		path(2,3);
		System.out.print("v : 3");
		System.out.println("을 지나감");
		System.out.println();
		
	}

}
