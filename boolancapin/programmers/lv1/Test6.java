package programmers.lv1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * lv.1 개인정보 수집 유효기간
 * 
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * @author boolancpain
 */
public class Test6 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String today = "2022.05.19";
		String[] terms = new String[]{"A 6", "B 12", "C 3"};
		String[] privacies = new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
		int[] expect = new int[]{1, 3};
		int[] result = solution.solution(today, terms, privacies);
		System.out.println(String.format("테스트 케이스1 %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		today = "2020.01.01";
		terms = new String[]{"Z 3", "D 5"};
		privacies = new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
		expect = new int[]{1, 4, 5};
		result = solution.solution(today, terms, privacies);
		System.out.println(String.format("테스트 케이스2 %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	public static class Solution {
		public int[] solution(String today, String[] terms, String[] privacies) {
			ArrayList<Integer> answer = new ArrayList<>();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate todayDate = LocalDate.parse(today, formatter);
			
			// 약관 종류와 유효기간을 hash map
			HashMap<String, Integer> termMap = new HashMap<>();
			for(String term : terms) {
				String[] t = term.split(" ");
				termMap.put(t[0], Integer.parseInt(t[1]));
			}
			
			for(int i = 0;i < privacies.length;i++) {
				// ex: {"2021.05.02", "A"}
				String[] privacy = privacies[i].split(" ");
				// 약관 종류로 유효기간 조회
				Integer exp = termMap.get(privacy[1]);
				// 개인정보 수집일
				LocalDate expireDate = LocalDate.parse(privacy[0], formatter);
				// 개인정보 만료일(개인정보 수집일 + 유효기간)이 today보다 작거나 같은 경우 파기
				if(!expireDate.plusMonths(exp).isAfter(todayDate)) {
					// index + 1 번째 약관
					answer.add(i + 1);
				}
			}
			
			// 오름차순 정렬 후 배열로 변환
			return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
		}
	}
}