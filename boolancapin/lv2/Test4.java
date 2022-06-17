package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 기능개발
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/42586
 * @author boolancpain
 */
public class Test4 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] progresses = new int[] {93, 30, 55};
		int[] speeds = new int[] {1, 30, 5};
		int[] expect = new int[] {2, 1};
		int[] result = solution.solution(progresses, speeds);
		System.out.println(String.format("[테스트 케이스 1] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		progresses = new int[] {95, 90, 99, 99, 80, 99};
		speeds = new int[] {1, 1, 1, 1, 1, 1};
		expect = new int[] {1, 3, 2};
		result = solution.solution(progresses, speeds);
		System.out.println(String.format("[테스트 케이스 2] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			// 완료된 기능
			Stack<Integer> completed = new Stack<>();
			
			// 개발해야 할 기능 목록
			ArrayList<Integer> progressList = new ArrayList<>();
			ArrayList<Integer> answer = new ArrayList<>();
			
			for(int i = 0;i < progresses.length;i++) {
				progressList.add(i);
			}
			
			// 완료되어야 할 기능 인덱스
			int todo = 0;
			
			while(!progressList.isEmpty()) {
				for(int i = 0;i < progressList.size();i++) {
					int index = progressList.get(i);
					progresses[index] += speeds[index];
					
					if(progresses[index] >= 100) {
						// 완료 목록에 인덱스 추가
						completed.push(i);
					}
				}
				
				// 완료된 기능은 개발해야 할 목록에서 제거
				while(!completed.isEmpty()) {
					int completedIndex = completed.pop();
					// 미완료 목록에서 삭제
					progressList.remove(completedIndex);
				}
				
				// 배포
				int deploy = 0;
				for(int i = todo;i < progresses.length;i++) {
					if(progresses[i] >= 100) {
						// 배포 기능 개수 증가
						deploy++;
					} else {
						todo = i;
						break;
					}
				}
				
				if(deploy > 0) {
					answer.add(deploy);
				}
			}
			
			return answer.stream().mapToInt(i -> i).toArray();
		}
	}
}
