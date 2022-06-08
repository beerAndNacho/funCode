package programmers.lv1;

/**
 * lv1. 신규 아이디 추천
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/72410
 * @author boolancpain
 */
public class Test2 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		// test case 1
		String id = "...!@BaT#*..y.abcdefghijklm";
		String expect = "bat.y.abcdefghi";
		String result = solution.solution(id);
		System.out.println(String.format("테스트 케이스 1 expect : %s, result : %s : %s", expect, result, (expect.equals(result) ? "통과" : "실패")));
		
		// test case 2
		id = "z-+.^.";
		expect = "z--";
		result = solution.solution(id);
		System.out.println(String.format("테스트 케이스 2 expect : %s, result : %s : %s", expect, result, (expect.equals(result) ? "통과" : "실패")));
		
		// test case 3
		id = "=.=";
		expect = "aaa";
		result = solution.solution(id);
		System.out.println(String.format("테스트 케이스 3 expect : %s, result : %s : %s", expect, result, (expect.equals(result) ? "통과" : "실패")));
		
		// test case 4
		id = "123_.def";
		expect = "123_.def";
		result = solution.solution(id);
		System.out.println(String.format("테스트 케이스 4 expect : %s, result : %s : %s", expect, result, (expect.equals(result) ? "통과" : "실패")));
		
		// test case 5
		id = "abcdefghijklmn.p";
		expect = "abcdefghijklmn";
		result = solution.solution(id);
		System.out.println(String.format("테스트 케이스 5 expect : %s, result : %s : %s", expect, result, (expect.equals(result) ? "통과" : "실패")));
	}
	
	static class Solution {
		public String solution(String new_id) {
			String answer = "";
			// 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
			answer = new_id.toLowerCase();
			
			// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
			answer = answer.replaceAll("[~!@#$%\\^&*\\(\\)=+\\{\\}:?,<>/\\[\\]]", "");
			
			// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
			StringBuilder sb = new StringBuilder();
			char prevCharacter = 0;
			for(char character : answer.toCharArray()) {
				if(prevCharacter == '.' && prevCharacter == character) continue;
				
				// add
				sb.append(character);
				// set prev character
				prevCharacter = character;
			}
			answer = sb.toString();
			
			// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
			answer = answer.startsWith(".") ? answer.substring(1) : answer;
			answer = answer.endsWith(".") ? answer.substring(0, answer.length() - 1) : answer;
			
			// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
			if(answer == null || answer.isEmpty()) {
				answer = "a";
			}
			
			// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
			//      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
			answer = answer.length() > 15 ? answer.substring(0, 15) : answer;
			answer = answer.endsWith(".") ? answer.substring(0, answer.length() - 1) : answer;
			
			// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
			if(answer.length() <= 2) {
				sb = new StringBuilder(answer);
				
				while(sb.length() < 3) {
					sb.append(answer.charAt(answer.length() - 1));
				}
				
				answer = sb.toString();
			}
			
			return answer;
		}
	}
}