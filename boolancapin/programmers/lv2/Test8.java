package programmers.lv2;

import java.util.Arrays;

/**
 * lv.2 행렬 테두리 회전하기
 * 
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/77485
 * @author boolancpain
 */
public class Test8 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int rows = 6;
		int colums = 6;
		int[][] queries = new int[][] {{2,2,5,4}, {3,3,6,6}, {5,1,6,3}};
		int[] expect = new int[] {8, 10, 25};
		int[] result = solution.solution(rows, colums, queries);
		System.out.println(String.format("테스트 케이스 1 : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		rows = 3;
		colums = 3;
		queries = new int[][] {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
		expect = new int[] {1,1,5,3};
		result = solution.solution(rows, colums, queries);
		System.out.println(String.format("테스트 케이스 2 : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		rows = 100;
		colums = 97;
		queries = new int[][] {{1,1,100,97}};
		expect = new int[] {1};
		result = solution.solution(rows, colums, queries);
		System.out.println(String.format("테스트 케이스 3 : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public int[] solution(int rows, int columns, int[][] queries) {
			// init
			int[][] data = new int[rows][columns];
			for(int i = 0;i < rows;i++) {
				for(int j = 0;j < columns;j++) {
					data[i][j] = (i * columns) + j + 1;
				}
			}
			
			int[] answer = new int[queries.length];
			
			for(int i = 0;i < queries.length;i++) {
				int min = 0;
				int prev = 0;
				
				int x1 = queries[i][0] - 1;
				int y1 = queries[i][1] - 1;
				int x2 = queries[i][2] - 1;
				int y2 = queries[i][3] - 1;
				
				int x = x1; // 현재 x
				int y = y1; // 현재 y
				
				boolean isEnd = false;
				while(!isEnd) {
					if(prev == 0) {
						// 처음인 경우 현재 행렬의 숫자를 저장하고 오른쪽 이동
						prev = data[x][y++];
						min = prev;
					} else if(x == x1 && y == y1) {
						// 처음으로 돌아온 경우 저장한 숫자로 변경하고 종료
						if(min > prev) {
							min = prev;
						}
						data[x][y] = prev;
						isEnd = true;
					} else {
						if(min > prev) {
							min = prev;
						}
						int t = data[x][y];
						data[x][y] = prev;
						prev = t;
						if(x == x1) {
							// 위 테두리
							if(y == y2) {
								// 위 오른쪽 테두리 : 아래로
								x++;
							} else {
								// 위 테두리 : 오른쪽으로
								y++;
							}
						} else if(y == y2) {
							// 오른쪽 테두리
							if(x == x2) {
								// 오른쪽 아래 테두리 : 왼쪽으로
								y--;
							} else {
								// 오른쪽 테두리 : 아래로
								x++;
							}
						} else if(x == x2) {
							// 아래 테두리
							if(y == y1) {
								// 왼쪽 아래 테두리 : 위로
								x--;
							} else {
								// 아래 테두리 : 왼쪽으로
								y--;
							}
						} else if(y == y1) {
							// 왼쪽 테두리 : 위로
							x--;
						}
					}
				}
				
				answer[i] = min;
			}
			
			return answer;
		}
	}
}