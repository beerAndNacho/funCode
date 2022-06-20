package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 타겟 넘버
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/43165
 * @author boolancpain
 */
public class Test5 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] numbers = new int[] {1, 1, 1, 1, 1};
		int target = 3;
		int expect = 5;
		int result = solution.solution(numbers, target);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect == result ? "통과" : "실패"));
		
		numbers = new int[] {4, 1, 2, 1};
		target = 4;
		expect = 2;
		result = solution.solution(numbers, target);
		System.out.println(String.format("[테스트 케이스 2] : %s", expect == result ? "통과" : "실패"));
	}
	
	static class Solution {
		Queue<Integer> q = new LinkedList<>();
		
		public int solution(int[] numbers, int target) {
			int[] elements = new int[numbers.length];
			visit(numbers, 0, elements);
			
			int answer = 0;
			while(!q.isEmpty()) {
				if(q.poll() == target)	answer++;
			}
			
			return answer;
		}
		
		public void visit(int[] numbers, int depth, int[] operators) {
			// 더하기
			operators[depth] = 1;
			if(numbers.length - 1 > depth) {
				visit(numbers, depth + 1, operators);
			} else {
				int sum = 0;
				for(int i = 0;i < operators.length;i++) {
					sum += numbers[i] * operators[i];
				}
				
				// 연산 결과 큐에 저장
				q.offer(sum);
			}
			
			// 빼기
			operators[depth] = -1;
			if(numbers.length - 1 > depth) {
				visit(numbers, depth + 1, operators);
			} else {
				int sum = 0;
				for(int i = 0;i < operators.length;i++) {
					sum += numbers[i] * operators[i];
				}
				
				// 연산 결과 큐에 저장
				q.offer(sum);
			}
		}
		
		public int goodSolution(int[] numbers, int target) {
			return visit(numbers, 0, 0, target);
		}
		
		// dfs
		public int visit(int[] numbers, int depth, int sum, int target) {
			// 탐색이 끝났을 때 target과 연산값 비교
			if(numbers.length == depth) {
				if(target == sum) {
					return 1;
				} else {
					return 0;
				}
			}
			
			return visit(numbers, depth + 1, sum + numbers[depth], target) + visit(numbers, depth + 1, sum - numbers[depth], target);
		}
	}
}
