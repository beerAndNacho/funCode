package programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * lv1. 신고 결과 받기
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/92334
 * @author boolancpain
 */
public class Test1 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		// test case 1
		// parameter
		String[] id_list = new String[] {"muzi", "frodo", "apeach", "neo"};
		String[] report = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k = 2;
		int[] result = solution.solution(id_list, report, k);
		
		int[] answer = new int[] {2, 1, 1, 0};
		System.out.println(String.format("테스트 케이스1 %s", Arrays.equals(answer, result) ? "통과" : "실패"));
		
		// test case 2
		// parameter
		id_list = new String[] {"con", "ryan"};
		report = new String[] {"ryan con", "ryan con", "ryan con", "ryan con"};
		k = 3;
		result = solution.solution(id_list, report, k);
		
		answer = new int[] {0, 0};
		System.out.println(String.format("테스트 케이스2 %s", Arrays.equals(answer, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public int[] solution(String[] id_list, String[] report, int k) {
			int answer[] = new int[id_list.length];
			
			Map<String, Report> results = new HashMap<>();
			
			// 초기화
			for(String id : id_list) {
				results.put(id, new Report(new HashSet<>(), 0));
			}
			
			// 신고 결과 처리
			for(String reportElement : report) {
				String reportFrom = reportElement.split(" ")[0];
				String reportTo = reportElement.split(" ")[1];
				
				results.get(reportTo).getReporters().add(reportFrom);
			}
			
			// 메일 발송 처리
			for(String id : id_list) {
				HashSet<String> reporters = results.get(id).getReporters();
				// k보다 많이 신고 된 유저에게 메일 발송
				if(!reporters.isEmpty() && reporters.size() >= k) {
					for(String reporter : reporters) {
						results.get(reporter).addMailCount();
					}
				}
			}
			
			for(int i = 0;i < id_list.length;i++) {
				answer[i] = results.get(id_list[i]).getMailCount();
			}
			
			return answer;
		}
	}
	
	static class Report {
		private HashSet<String> reporters;
		private int mailCount;
		
		public Report(HashSet<String> reporters, int mailCount) {
			this.reporters = reporters;
			this.mailCount = mailCount;
		}
		public HashSet<String> getReporters() {
			return reporters;
		}
		public void setReporters(HashSet<String> reporters) {
			this.reporters = reporters;
		}
		public int getMailCount() {
			return mailCount;
		}
		public void setMailCount(int mailCount) {
			this.mailCount = mailCount;
		}
		public void addMailCount() {
			this.mailCount++;
		}
	}
}