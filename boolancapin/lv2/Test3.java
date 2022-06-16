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
		// 주문된 메뉴 조합을 저장할 map
		HashMap<String, Integer> orderMap = new HashMap<>();
		
		public String[] solution(String[] orders, int[] course) {
			// 코스요리 목록
			ArrayList<String> menus = new ArrayList<>();
			
			for(int c : course) {
				orderMap.clear();
				
				for(String order : orders) {
					// 주문한 메뉴 수가 코스요리 조합 개수보다 작으면 패스
					if(order.length() < c) {
						continue;
					} else if(order.length() == c) {
						addMenu(order);
					} else {
						// 주문한 메뉴에서 코스 요리 조합을 구한다.
						comb(order, "", c);
					}
				}
				
				if(orderMap.values().size() > 0) {
					// 최대 주문 수
					Integer maxOrderedCount = Collections.max(orderMap.values());
					
					// 최대 주문 수와 같고 1회 이상 주문된 메뉴 조합만 추가
					orderMap.entrySet().stream()
						.filter(map -> map.getValue() == maxOrderedCount && map.getValue() > 1)
						.forEach(map -> menus.add(map.getKey()));
				}
			}
			
			// 오름차순 정렬
			Collections.sort(menus);
			
			// to array
			return menus.toArray(new String[menus.size()]);
		}
		
		/**
		 * 주문한 메뉴들을 오름찬수으로 정렬해서 저장한다.
		 * 
		 * @param menu : 주문한 메뉴들
		 */
		public void addMenu(String menu) {
			String[] temp = menu.split("");
			Arrays.sort(temp);
			
			// 메뉴 오름차순 정렬
			StringBuffer sb = new StringBuffer();
			for(String t : temp) {
				sb.append(t);
			}
			
			String sortMenu = sb.toString();
			if(orderMap.containsKey(sortMenu)) {
				orderMap.put(sortMenu, orderMap.get(sortMenu) + 1);
			} else {
				orderMap.put(sortMenu, 1);
			}
		}
		
		/**
		 * 메뉴 조합을 모두 구한다.
		 * 
		 * @param str : 남은 메뉴
		 * @param result : 뽑은 메뉴 조합
		 * @param r : 뽑아야 하는 메뉴 개수
		 */
		public void comb(String str, String result, int r) {
			if(r == 1) {
				// 뽑아야 하는 메뉴 개수가 1이면, 뽑은 메뉴 조합에 남은 메뉴를 각각 더해서 코스 메뉴에 추가
				String[] arr = str.split("");
				for(int j = 0;j < arr.length;j++) {
					// add menu
					addMenu(result + arr[j]);
				}
			} else {
				// 뽑아야 하는 메뉴 개수가 1보다 크면, i번째 메뉴를 뽑은 메뉴에 추가하고 i번째 메뉴를 제외한 나머지 메뉴에서 r - 1개만큼 또 메뉴를 뽑는다.
				for(int i = 0;i <= str.length() - r;i++) {
					comb(str.substring(i + 1), result + str.substring(i, i + 1), r - 1);
				}
			}
		}
	}
}