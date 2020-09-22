/*
 * SW역량 테스트 (2048)
*/
package SamsungSWTest;

import java.util.*;
import java.io.*;

public class Problem14 {
    static int max;

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	int[][] map = new int[n][];

	for (int i = 0; i < map.length; i++) {
	    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}

	max = 0;
	playGame(0, map);
	System.out.println(max);
    }

    private static void playGame(int d, int[][] map) {
	if (d == 5) {
	    max = Integer.max(max, getMaxValue(map));
	    return;
	}

	for (int i = 0; i < 4; i++) {
	    int[][] newMap = moveMap(map, i);
	    if (newMap != null)
		playGame(d + 1, newMap);
	    else
		max = Integer.max(max, getMaxValue(map));
	}

    }

    private static int[][] moveMap(int[][] map, int d) {
	int[][] newMap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
	int cnt = 0;

	if (d == 0) {
	    for (int i = 0; i < newMap.length; i++) {
		for (int j = newMap.length - 1; j >= 1;) {
		    if (newMap[j][i] == 0 && cnt < map.length) {
			for (int k = j; k >= 1; k--) {
			    newMap[k][i] = newMap[k - 1][i];
			    if (k == 1)
				newMap[0][i] = 0;
			}
			j++;
			cnt++;
		    } else if (newMap[j][i] != 0 && newMap[j][i] == newMap[j - 1][i]) {
			newMap[j][i] *= 2;
			for (int k = j - 1; k >= 1; k--) {
			    newMap[k][i] = newMap[k - 1][i];
			}
			newMap[0][i] = 0;
		    }
		    j--;
		}
		cnt = 0;
	    }
	} else if (d == 1) {
	    for (int i = 0; i < newMap.length; i++) {
		for (int j = newMap.length - 1; j >= 1;) {
		    if (newMap[i][j] == 0 && cnt < map.length) {
			for (int k = j; k >= 1; k--) {
			    newMap[i][k] = newMap[i][k - 1];
			    if (k == 1)
				newMap[i][0] = 0;
			}
			j++;
			cnt++;
		    } else if (newMap[i][j] != 0 && newMap[i][j] == newMap[i][j - 1]) {
			newMap[i][j] *= 2;
			for (int k = j - 1; k >= 1; k--) {
			    newMap[i][k] = newMap[i][k - 1];
			}
			newMap[i][0] = 0;
		    }
		    j--;
		}
		cnt = 0;
	    }

	} else if (d == 2) {
	    for (int i = 0; i < newMap.length; i++) {
		for (int j = 0; j < newMap.length - 1;) {
		    if (newMap[j][i] == 0 && cnt < map.length) {
			for (int k = j; k < newMap.length - 1; k++) {
			    newMap[k][i] = newMap[k + 1][i];
			    if (k == newMap.length - 2)
				newMap[newMap.length - 1][i] = 0;
			}
			j--;
			cnt++;
		    } else if (newMap[j][i] != 0 && newMap[j][i] == newMap[j + 1][i]) {
			newMap[j][i] *= 2;
			for (int k = j + 1; k < newMap.length - 1; k++) {
			    newMap[k][i] = newMap[k + 1][i];
			}
			newMap[newMap.length - 1][i] = 0;
		    }
		    j++;
		}
		cnt = 0;
	    }
	} else if (d == 3) {
	    for (int i = 0; i < newMap.length; i++) {
		for (int j = 0; j < newMap.length - 1;) {
		    if (newMap[i][j] == 0 && cnt < map.length) {
			for (int k = j; k < newMap.length - 1; k++) {
			    newMap[i][k] = newMap[i][k + 1];
			    if (k == newMap.length - 2)
				newMap[i][newMap.length - 1] = 0;
			}
			j--;
			cnt++;
		    } else if (newMap[i][j] != 0 && newMap[i][j] == newMap[i][j + 1]) {
			newMap[i][j] *= 2;
			for (int k = j + 1; k < newMap.length - 1; k++) {
			    newMap[i][k] = newMap[i][k + 1];
			}
			newMap[i][newMap.length - 1] = 0;
		    }
		    j++;
		}
		cnt = 0;
	    }

	}
	return newMap;
    }

    private static int getMaxValue(int[][] map) {
	int maxElement = -1;
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[0].length; j++) {
		maxElement = Integer.max(maxElement, map[i][j]);
	    }
	}
	return maxElement;
    }

}
