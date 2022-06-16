package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * lv2. 메뉴 리뉴얼
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/72411
 * @author boolancpain
 */
public class Test3 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String[] orders = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = new int[] {2, 3, 4};
		String[] expext = new String[] {"AC", "ACDE", "BCFG", "CDE"};
		String[] result = solution.solution(orders, course);
		System.out.println(String.format("[테스트 케이스 1] : %s", Arrays.equals(expext, result) ? "통과" : "실패"));
		
		orders = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		course = new int[] {2, 3, 5};
		expext = new String[] {"ACD", "AD", "ADE", "CD", "XYZ"};
		result = solution.solution(orders, course);
		System.out.println(String.format("[테스트 케이스 2] : %s", Arrays.equals(expext, result) ? "통과" : "실패"));
		
		orders = new String[] {"XYZ", "XWY", "WXA"};
		course = new int[] {2, 3, 4};
		expext = new String[] {"WX", "XY"};
		result = solution.solution(orders, course);
		System.out.println(String.format("[테스트 케이스 3] : %s", Arrays.equals(expext, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		HashMap<String, Integer> orderMap = new HashMap<>();
		
		public String[] solution(String[] orders, int[] course) {
			// 주문된 메뉴를 정리
			ArrayList<String> menus = new ArrayList<>();
			
			for(int c : course) {
				orderMap.clear();
				
				for(String order : orders) {
					// 코스 메뉴 개수보다 주문 음식 수가 적으면 패스
					if(order.length() < c) {
						continue;
					} else if(order.length() == c) {
						addMenu(order);
					} else {
						comb(order, "", c);
					}
				}
				
				if(orderMap.values().size() > 0) {
					// 최대 주문 수
					Integer maxOrderedCount = Collections.max(orderMap.values());
					
					// 최대 주문 수와 같고 1회 이상 주문된 메뉴조합만 추가
					orderMap.entrySet().stream()
						.filter(map -> map.getValue() == maxOrderedCount && map.getValue() > 1)
						.forEach(map -> menus.add(map.getKey()));
				}
			}
			
			Collections.sort(menus);
			String[] answer = menus.toArray(new String[menus.size()]);
			
			return answer;
		}
		
		// 메뉴 추가
		public void addMenu(String menu) {
			String[] temp = menu.split("");
			Arrays.sort(temp);
			
			// 메뉴 오름차순 정렬
			StringBuffer sb = new StringBuffer();
			for(String t : temp) {
				sb.append(t);
			}
			
			String newMenu = sb.toString();
			if(orderMap.containsKey(newMenu)) {
				orderMap.put(newMenu, orderMap.get(newMenu) + 1);
			} else {
				orderMap.put(newMenu, 1);
			}
		}
		
		// 문자열, 추출한 문자열, 추출해야할 개수
		public void comb(String str, String result, int r) {
			if(r == 1) {
				// 추출해야할 문자열 개수가 1이면, 추출한 문자열에 나머지 문자를 각각 더한 케이스를 메뉴에 추가
				String[] arr = str.split("");
				for(int j = 0;j < arr.length;j++) {
					// add menu
					addMenu(result + arr[j]);
				}
			} else {
				// 추출해야할 개수가 1보다 크면 맨 처음 문자열을 제외한 나머지 케이스를 모두 구한다.
				for(int i = 0;i <= str.length() - r;i++) {
					comb(str.substring(i + 1), result + str.substring(i, i + 1), r - 1);
				}
			}
		}
	}
}