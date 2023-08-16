package programmers.lv2;

/**
 * lv2. 문자열 압축
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/60057
 * @author boolancpain
 */
public class Test1 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		// test case 1
		String s = "aabbaccc";
		int expect = 7;
		int result = solution.solution(s);
		System.out.println(String.format("테스트 케이스 1 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
		
		// test case 2
		s = "ababcdcdababcdcd";
		expect = 9;
		result = solution.solution(s);
		System.out.println(String.format("테스트 케이스 2 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
		
		// test case 3
		s = "abcabcdede";
		expect = 8;
		result = solution.solution(s);
		System.out.println(String.format("테스트 케이스 3 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
		
		// test case 4
		s = "abcabcabcabcdededededede";
		expect = 14;
		result = solution.solution(s);
		System.out.println(String.format("테스트 케이스 4 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
		
		// test case 5
		s = "xababcdcdababcdcd";
		expect = 17;
		result = solution.solution(s);
		System.out.println(String.format("테스트 케이스 5 expect : %s, result : %s : %s", expect, result, expect == result ? "통과" : "실패"));
	}
	
	static class Solution {
		public int solution(String s) {
			// 2글자 이하인 경우는 문자열 길이를 그대로 리턴(압축하더라도 문자열 길이보다 작을 수 없음)
			if(s.length() <= 2) {
				return s.length();
			}
			
			// 1. 압축할 때 입력값의 절반을 넘는 크기의 압축은 불가능
			int maxCompress = s.length() / 2;
			
			int answer = s.length();
			
			// 2. 압축 테스트
			for(int i = 1;i <= maxCompress;i++) {
				String sb = compress(s, i);
				
				// 최소 길이 문자열
				// 압축된 최소 문자열 길이는 2
				if(sb.length() == 2) {
					return sb.length();
				}
				
				answer = answer > sb.length() ? sb.length() : answer;
			}
			
			return answer;
		}
		
		public String compress(String s, int compressibility) {
			StringBuilder sb = new StringBuilder();
			
			int start = 0;
			int end = compressibility;
			
			int cnt = 1;
			String prevToken = null;
			
			while(end <= s.length()) {
				String token = s.substring(start, end);
				
				// 이전 토큰 저장
				if(prevToken == null) {
					prevToken = token;
				} else {
					// 토큰이 같은 경우 카운트 증가
					if(token.equals(prevToken)) {
						cnt++;
					} else {
						if(cnt > 1) {
							sb.append(cnt);
							cnt = 1;
						}
						sb.append(prevToken);
						prevToken = token;
					}
				}
				
				// 더 잘라낼 수 있는 토큰이 존재하는 경우 탐색 범위 변경
				if(end + compressibility <= s.length()) {
					start = end;
					end = start + compressibility;
				} else {
					// 더이상 잘라낼 토큰이 없다면 이전 토큰과 나머지 문자열을 추가하고 종료
					if(cnt > 1) {
						sb.append(cnt);
					}
					sb.append(prevToken);
					sb.append(s.substring(end));
					break;
				}
			}
			
			return sb.toString();
		}
	}
}