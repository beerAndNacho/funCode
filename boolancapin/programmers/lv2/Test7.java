package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * lv2. 양궁대회
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/92342
 * @author boolancpain
 */
public class Test7 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int n = 5;
		int[] info = new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
		int[] expect = new int[] {0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0};
		int[] result = solution.solution(n, info);
		System.out.println(String.format("[테스트 케이스 1] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		n = 1;
		info = new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		expect = new int[] {-1};
		result = solution.solution(n, info);
		System.out.println(String.format("[테스트 케이스 2] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		n = 9;
		info = new int[] {0,0,1,2,0,1,1,1,1,1,1};
		expect = new int[] {1,1,2,0,1,2,2,0,0,0,0};
		result = solution.solution(n, info);
		System.out.println(String.format("[테스트 케이스 3] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		n = 10;
		info = new int[] {0,0,0,0,0,0,0,0,3,4,3};
		expect = new int[] {1,1,1,1,1,1,1,1,0,0,2};
		result = solution.solution(n, info);
		System.out.println(String.format("[테스트 케이스 4] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		ArrayList<Integer> max = new ArrayList<>();
		int maxScore = 0;
		
		public int[] solution(int n, int[] info) {
			int[] ryan = new int[11];
			
			max = new ArrayList<>(Arrays.asList(-1));
			
			// 기록 비교
			compare(info, ryan, n, 0);
			
			int[] answer = new int[max.size()];
			for(int i = 0;i < answer.length;i++) {
				answer[i] = max.get(i);
			}
			
			return answer;
		}
		
		public void compare(int[] apeach, int[] ryan, int n, int index) {
			// 탐색 끝난 경우
			if(index > 10 || n == 0) {
				// 남은 화살 모두 0점에 추가
				ryan[10] += n;
				// 점수 차이
				int scoreDiff = 0;
				
				for(int i = 0;i < apeach.length;i++) {
					if(ryan[i] > apeach[i]) {
						scoreDiff += 10 - i;
					} else if(apeach[i] > 0) {
						scoreDiff -= 10 - i;
					}
				}
				
				if(scoreDiff > 0 && scoreDiff >= maxScore) {
					if(scoreDiff == maxScore && max.size() > 1) {
						// 낮은 점수부터 많이 맞힌 경우 체크
						for(int i = 10;i >= 0;i--) {
							if(max.get(i) < ryan[i]) {
								// 새로운 기록을 입력하고 종료
								max.clear();
								for(int j = 0;j < 11;j++) {
									max.add(ryan[j]);
								}
								break;
							} else if(max.get(i) > ryan[i]) {
								// 기존 기록이 낮은 점수를 많이 맞췄다면 종료
								break;
							}
						}
					} else {
						max.clear();
						for(int i = 0;i < 11;i++) {
							max.add(ryan[i]);
						}
					}
					
					maxScore = scoreDiff;
				}
				
				// 추가했던 남은 화살 모두 제거
				ryan[10] -= n;
				
				return;
			}
			
			// 어피치가 이기는 경우
			ryan[index] = 0;
			compare(apeach, ryan, n, index + 1);
			
			// 라이언이 이길 수 있을때 라이언이 이기는 경우
			if(apeach[index] + 1 <= n) {
				ryan[index] = apeach[index] + 1;
				compare(apeach, ryan, n - (apeach[index] + 1), index + 1);
				// 기록을 비교한 뒤 다시 처음 상태로 되돌림
				ryan[index] = 0;
			}
		}
	}
}