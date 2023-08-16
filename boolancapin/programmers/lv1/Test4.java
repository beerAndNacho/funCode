package programmers.lv1;

/**
 * lv.1 스킬 체크 테스트 문제 1
 * 
 * @see https://programmers.co.kr/skill_checks/398726?challenge_id=908
 * @author boolancpain
 */
public class Test4 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String[] input = new String[] {"Jane", "Kim"};
		String expect = "김서방은 1에 있다";
		String result = solution.solution(input);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect.equals(result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public String solution(String[] seoul) {
			String kim = "Kim";
			String answer = "";
			for(int i = 0;i < seoul.length;i++) {
				if(seoul[i].equals(kim)) {
					answer = String.format("김서방은 %s에 있다", String.valueOf(i));
					break;
				}
			}
			
			return answer;
		}
	}
}
