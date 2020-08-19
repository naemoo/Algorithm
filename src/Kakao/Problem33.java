/*
 * 2019 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/42894
 * 블록 게임 
*/
package Kakao;

public class Problem33 {
    public static int solution(int[][] board) {
	int answer = 0;
	int partAnswer = 0;
	int n = board.length;
	do {
	    partAnswer = 0;
	    for (int i = 0; i < board.length; i++) {
		for (int j = 0; j < board.length; j++) {
		    if (isSquare(i, j, 2, 3, board)) {
			partAnswer++;
		    } else if (isSquare(i, j, 3, 2, board)) {
			partAnswer++;
		    }
		}
	    }
	    answer += partAnswer;
	} while (partAnswer != 0);
	System.out.println(answer);
	return answer;
    }

    private static boolean isSquare(int row, int col, int h, int w, int[][] board) {
	int zeroCnt = 0;
	int prev = -1;
	for (int i = row; i < row + h; i++) {
	    for (int j = col; j < col + w; j++) {
		try {
		    if (board[i][j] == 0) {
			    if (!isProperLocation(i, j, board))
				return false;
			    if ((++zeroCnt) == 3)
				return false;
			} else {
			    if (prev > 0 && prev != board[i][j])
				return false;
			    prev = board[i][j];
			}
		}
		catch (IndexOutOfBoundsException e) {
		    return false;
		}
	    }
	}

	for (int i = row; i < row + h; i++) {
	    for (int j = col; j < col + w; j++) {
		board[i][j] = 0;
	    }
	}

	return true;
    }

    private static boolean isProperLocation(int row, int col, int[][] board) {
	for (int i = 0; i < row; i++) {
	    if (board[i][col] != 0)
		return false;
	}
	return true;
    }

    public static void main(String[] args) {
	solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 },
		{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } });
    }
}
