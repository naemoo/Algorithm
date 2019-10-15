package DivideAndConquer;

//스트라센 알고리즘 
/*
 *  단순한 행렬의 곱셈의 시간 복잡도 : O(n^3)
  		8번의 곱셈과 4번의 덧셈 필요
 *  스트라센 : 7번의 곱셈과 18번의 덧셈,뺄셈
  		7번의 곱셈 -> 재귀 7번!(분할 정복)
	조건 : 행렬의 크기 2n(짝수)
 
 *	임계점 보다 n이 작을 경우 단순한 행렬보다 좋은 성능을 보이지 않는다.
 */

public class Problem5 {
	static int threshold = 1; //임계점
	//행렬 뺄셈
	public static void subMatrix(int n,int[][] A,int[][] B,int[][] C) {//행렬 뺄셈
		int i,j;
		for(i=1;i<=n;i++) {
			for(j=1;j<=n;j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
	}
	//행렬 덧셈
	public static void addMatrix(int n,int[][] A,int[][] B,int[][] C) {//행렬 더하기
		int i,j;
		
		for(i=1;i<=n;i++) {
			for(j=1;j<=n;j++) {
				C[i][j] = A[i][j]+ B[i][j];
			}
		}
	}
	//행렬 곱셈
	public static void mulMatrix(int n,int[][] A,int[][] B,int[][] C) {//행렬 단순 곱하기
		int i,j,k;
		for(i = 1;i<=n;i++) {
			for(j=1;j<=n;j++) {
				for(k=1;k<=n;k++)
					C[i][j] += A[i][k]*B[k][j]; 
			}
		}
	}
	//행렬 4개로 분할		
	public static void partitionMatrix(int[][] OriginalMatrix,int[][] matrix11,int[][]matrix12,int[][] matrix21,int[][] matrix22) {
		int newN = matrix11.length-1;
		
		for(int i = 1;i<=newN;i++) {
			for(int j = 1;j<=newN;j++) {
				matrix11[i][j] = OriginalMatrix[i][j];
				matrix12[i][j] = OriginalMatrix[i][newN+j];
				matrix21[i][j] = OriginalMatrix[newN+i][j];
				matrix22[i][j] = OriginalMatrix[newN+i][newN+j];
			}
		}
	}
	//행렬 합치기
	public static void mergeMatrix(int[][] originalMatrix,int[][] c11,int[][] c12,int[][] c21,int[][] c22) {
		int orgSize = c11.length-1;
		
		for(int i = 1 ; i <= orgSize;i++) {
			for(int j = 1;j<= orgSize;j++) {
				originalMatrix[i][j] = c11[i][j];
				originalMatrix[i][j+orgSize] = c12[i][j];
				originalMatrix[i+orgSize][j] = c21[i][j];
				originalMatrix[i+orgSize][j+orgSize] = c22[i][j];
			}
		}
	}
	//스트라센 알고리즘
	public static void strassan(int n,int[][] A,int[][] B,int[][] C) {
		if(n<=threshold) {
			mulMatrix(n, A, B, C);
			return;
		}
		else {
			int newRow = n/2;
			int newCol = n/2;
			
			//A,B를 4개의 행렬로 분할
			int[][] A11 = new int[newRow+1][newCol+1];	int[][] A12 = new int[newRow+1][newCol+1];
			int[][] A21 = new int[newRow+1][newCol+1];	int[][] A22 = new int[newRow+1][newCol+1];
			
			int[][] B11 = new int[newRow+1][newCol+1];	int[][] B12 = new int[newRow+1][newCol+1];
			int[][] B21 = new int[newRow+1][newCol+1];	int[][] B22 = new int[newRow+1][newCol+1];
			
			partitionMatrix(A, A11, A12, A21, A22);
			partitionMatrix(B, B11, B12, B21, B22);
			
			//m1~m7 만들기
			int[][] m1 = new int[newRow+1][newCol+1];	int[][] m2 = new int[newRow+1][newCol+1];
			int[][] m3 = new int[newRow+1][newCol+1];	int[][] m4 = new int[newRow+1][newCol+1];
			int[][] m5 = new int[newRow+1][newCol+1];	int[][] m6 = new int[newRow+1][newCol+1];
			int[][] m7 = new int[newRow+1][newCol+1];
			
			//A1+A2 저장할 임시 행렬
			int[][] tempA = new int[newRow+1][newCol+1];	int[][] tempB = new int[newRow+1][newCol+1];
			
			addMatrix(newRow, A11, A22, tempA);	  
			addMatrix(newRow, B11, B22, tempB);	
			strassan(newRow, tempA, tempB, m1);//m1 = (a11+a22)(b11+b22)		
			
			addMatrix(newRow,A21,A22,tempA);
			strassan(newCol, tempA, B11, m2);//m2 = (a21+a22)b11
			
			subMatrix(newCol, B12, B22, tempA);
			strassan(newCol, A11, tempA, m3);//m3 = a11(b12-b22)
			
			subMatrix(newCol, B21, B11, tempA);
			strassan(newCol, A22, tempA, m4);//m4 = a22(b21-b11)
			
			addMatrix(newCol, A11, A12, tempA);
			strassan(newCol, tempA, B22, m5);//m5 = (a11+a12)b22
			
			subMatrix(newCol, A21, A11, tempA);
			addMatrix(newCol, B11, B12, tempB);
			strassan(newCol, tempA, tempB, m6);//m6 = (a21-a11)(b11+b12)
			
			subMatrix(newCol, A12, A22, tempA);
			addMatrix(newCol, B21, B22, tempB);
			strassan(newCol, tempA, tempB, m7);//m7 = (a11-a22)(b21+b22)
			
			//c11 c12 c21 c22 만들기
			int[][] c11 = new int[newRow+1][newCol+1];	int[][] c12 = new int[newRow+1][newCol+1];
			int[][] c21 = new int[newRow+1][newCol+1];	int[][] c22 = new int[newRow+1][newCol+1];
			
			addMatrix(newCol, m1, m4, tempA);
			subMatrix(newCol, tempA, m5, tempB);
			addMatrix(newCol, tempB, m7, c11);//c11 = m1+m4 - m5 + m7
			
			addMatrix(newCol, m3, m5, c12);//c12 = m3 + m5
			
			addMatrix(newCol, m2, m4, c21);//c21 = m2 + m4
			
			subMatrix(newCol, m1, m2, tempA);
			addMatrix(newCol, tempA, m3, tempB);
			addMatrix(newCol, tempB, m6, c22);//c22 = m1 - m2 + m3 + m6
			
			mergeMatrix(C, c11, c12, c21, c22);
		}
	}

	public static void main(String[] args) {
		int[][] A = new int[][]{{0,0,0,0,0},{0,1,2,3,4},{0,5,6,7,8},{0,9,1,2,3},{0,4,5,6,7}};//4x4 0 번 인덱스 더미
		int[][] B = new int[][]{{0,0,0,0,0},{0,8,9,1,2},{0,3,4,5,6},{0,7,8,9,1},{0,2,3,4,5}};//4x4 0 번 인덱스  더미
		int n = A.length-1;
		int[][] C = new int[n+1][n+1];
		
		strassan(n, A, B, C);
		
		for (int i = 1;i<=n;i++) {
			for(int j =1 ;j<=n;j++){
				System.out.print(C[i][j]+" ");
			}
			System.out.println();
		}
	}

}
