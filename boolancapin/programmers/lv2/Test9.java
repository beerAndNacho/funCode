package programmers.lv2;

import java.util.Arrays;

/**
 * 광물 캐기
 * 
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/172927
 * @author boolancpain
 */
public class Test9 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] picks = new int[] {1, 3, 2};
		String[] minerals = new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
		int expect = 12;
		int result = solution.solution(picks, minerals);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect == result ? "통과" : "실패"));
		
		picks = new int[] {0, 1, 1};
		minerals = new String[] {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
		expect = 50;
		result = solution.solution(picks, minerals);
		System.out.println(String.format("[테스트 케이스 2] : %s", expect == result ? "통과" : "실패"));
	}
	
	static class Solution {
		public int solution(int[] picks, String[] minerals) {
			int answer = 0;
			
			// 전체 곡괭이 개수
			int gok = picks[0] + picks[1] + picks[2];
			// 5개씩 나눈 미네랄들의 배열 사이즈
			int size = minerals.length / 5 + (minerals.length % 5 == 0 ? 0 : 1);
			// 미네랄 그룹
			int[][] mineralGroups = new int[size][5];
			for(int i = 0;i < size;i++) {
				// 곡괭이 개수보다 미네랄이 많으면 나머지 미네랄은 제외함
				if(gok == 0) break;
				gok--;
				for(int j = 0;j < 5;j++) {
					int idx = i * 5 + j;
					if(idx >= minerals.length) break;
					String mineral = minerals[i * 5 + j];
					int cost = 0;
					// 피로도는 돌 곡괭이를 기준으로 계산
					switch(mineral) {
					case "diamond":	cost = 25; break;
					case "iron" :	cost = 5; break;
					default: cost = 1;
					}
					mineralGroups[i][j] = cost;
				}
			}
			
			// 피로도의 합이 큰 것부터 내림차순 정렬
			Arrays.sort(mineralGroups, (a1, a2) -> 
				Arrays.stream(a2).sum() - Arrays.stream(a1).sum());
			
			for(int i = 0;i < mineralGroups.length;i++) {
				// 최악의 경우(피로도가 가장 큰)부터 곡괭이 사용
				int pick = 0;
				if(picks[0] > 0) {
					pick = 25;
					picks[0] -= 1;
				} else if(picks[1] > 0) {
					pick = 5;
					picks[1] -= 1;
				} else {
					pick = 1;
					picks[2] -= 1;
				}
				int cost = 0;
				for(int j = 0;j < mineralGroups[i].length;j++) {
					if(mineralGroups[i][j] == 0) {
						break;
					} else if(pick > mineralGroups[i][j]) {
						cost++;
					} else {
						cost += mineralGroups[i][j] / pick;
					}
				}
				
				answer += cost;
			}
			
			return answer;
		}
	}
}
