package programmers.lv1;

import java.util.Arrays;

/**
 * lv.1 스킬 체크 테스트 문제 2
 * 
 * @see https://programmers.co.kr/skill_checks/398726?challenge_id=2550
 * @author boolancpain
 */
public class Test5 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String[] participant = new String[] {"leo", "kiki", "eden"};
		String[] completion = new String[] {"eden", "kiki"};
		String expect = "leo";
		String result = solution.solution(participant, completion);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect.equals(result) ? "통과" : "실패"));
		
		participant = new String[] {"marina", "josipa", "nikola", "vinko", "filipa"};
		completion = new String[] {"josipa", "filipa", "marina", "nikola"};
		expect = "vinko";
		result = solution.solution(participant, completion);
		System.out.println(String.format("[테스트 케이스 2] : %s", expect.equals(result) ? "통과" : "실패"));
		
		participant = new String[] {"mislav", "stanko", "mislav", "ana"};
		completion = new String[] {"stanko", "ana", "mislav"};
		expect = "mislav";
		result = solution.solution(participant, completion);
		System.out.println(String.format("[테스트 케이스 3] : %s", expect.equals(result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public String solution(String[] participant, String[] completion) {
			Arrays.sort(participant);
			Arrays.sort(completion);
			
			String answer = "";
			for(int i = 0;i < completion.length;i++) {
				if(!participant[i].equals(completion[i])) {
					answer = participant[i];
					break;
				}
			}
			
			if(answer.equals("")) {
				answer = participant[participant.length - 1];
			}
			
			return answer;
		}
	}
}