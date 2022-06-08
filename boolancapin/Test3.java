package programmers.lv1;

import java.util.ArrayList;
import java.util.List;

/**
 * lv1. 크레인 인형뽑기 게임
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/64061
 * @author boolancpain
 */
public class Test3 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[][] board = new int[][] {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = new int[] {1, 5, 3, 5, 1, 2, 1, 4};
		int expect = 4;
		int result = solution.solution(board, moves);
		System.out.println(String.format("테스트 케이스 1 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
	}
	
	static class Solution {
		public int solution(int[][] board, int[] moves) {
			int answer = 0;
			
			List<Integer> basket = new ArrayList<>();
			
			// moves 순회
			for(int move : moves) {
				// 꺼낸 인형
				int pop = 0;
				
				// move - 1 열에서 순서대로 인형을 탐색
				for(int i = 0;i < board.length;i++) {
					if(board[i][move - 1] != 0) {
						// 인형 꺼내기
						pop = board[i][move - 1];
						// 인형 비우기
						board[i][move - 1] = 0;
						break;
					}
				}
				
				// 인형이 있는 경우
				if(pop != 0) {
					// 가장 마지막 인형과 뽑은 인형이 같으면 바구니에서 제거
					if(basket.size() > 0 && basket.get(basket.size() - 1) == pop) {
						basket.remove(basket.size() - 1);
						answer += 2;
					} else {
						// 바구니에 추가
						basket.add(pop);
					}
				}
			}
			
			return answer;
		}
	}
}