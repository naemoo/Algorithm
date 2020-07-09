/*
 * 2018 KAKAO BLIND RECRUITMENT
 * 프렌즈4블록
 * https://programmers.co.kr/learn/courses/30/lessons/17679
*/

package Kakao;

import java.util.*;

public class Problem5 {
	static int[] dx = {1,0,1};
	static int[] dy = {0,1,1};
	
    public static int solution(int m, int n, String[] board) {
        char[][] gameBoard = new char[m][n];
        int answer = 0;
        
        for(int i = 0 ; i <board.length;i++){
        	char[] elements = board[i].toCharArray();
            for(int j = 0 ; j<n;j++){
                gameBoard[i][j] = elements[j];
            }
        }
        
         char[][] eraseBlocks;
         
         while ((eraseBlocks= findEraseBlocks(gameBoard, m, n)) != null) {
        	 for (int i = 0; i < m; i++) {
        		 for (int j = 0; j < n; j++) {
					if(eraseBlocks[i][j] == 1) {
						rearrangeBlocks(gameBoard,i,j);
					}
				}
			}
		}
         for(char[] blocks:gameBoard) {
        	 for(char c:blocks) {
        		 if(c==0) {
        			 answer++;
        		 }
        	 }
         }
         
        return answer;
    }

    private static void rearrangeBlocks(char[][] gameBoard, int x, int y) {
    	for (int i = x-1; i >= 0 ; i--){
    		gameBoard[i+1][y] = gameBoard[i][y];
		}
    	gameBoard[0][y] = 0;
	}

	private static char[][] findEraseBlocks(char[][] gameBoard, int m, int n) {
    	boolean isExist = false;
    	char[][] eraseBlocks = new char[m][n];
    	
    	for(int i =0;i<m;i++) {
    		for (int j = 0; j < n; j++) {
    			if(gameBoard[i][j] == 0) {
    				continue;
    			}
    			else if(isSquare(gameBoard, i, j)) {
    				eraseBlocks[i][j] = 1;
    				eraseBlocks[i+1][j] = 1;
    				eraseBlocks[i][j+1] = 1;
    				eraseBlocks[i+1][j+1] = 1;
    				isExist = true;
    			}
			}
    	}
		return isExist?eraseBlocks:null;
	}

	private static boolean isSquare(char[][] gameBoard, int lx, int ly) {

		for(int i = 0;i<3;i++) {
			int nx = lx + dx[i];
			int ny = ly + dy[i];
			if(nx<gameBoard.length&&ny<gameBoard[0].length) {
				if(gameBoard[lx][ly] != gameBoard[nx][ny]) {
					return false;
				}
			}
			else {
				return false;
			}
			
		}
		return true;
	}

	public static void main(String[] args) {
		int answer = solution(4, 5, new String[]{"CCBDE","AAADE","AAABF","CCBBF"});
		System.out.println(answer);
		answer = solution(6, 6, new String[]{"TTTANT","RRFACC","RRRFCC","TRRRAA","TTMMMF","TMMTTJ"});
		System.out.println(answer);
	}
}
