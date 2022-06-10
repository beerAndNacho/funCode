package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * lv2. 오픈 채팅방
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/42888
 * @author boolancpain
 */
public class Test2 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String[] record = new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] expect = new String[] {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."};
		String[] result = solution.solution(record);
		System.out.println(String.format("테스트 케이스1 %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public String[] solution(String[] record) {
			ArrayList<History> histories = new ArrayList<>();
			HashMap<String, String> users = new HashMap<>();
			
			for(String r : record) {
				String[] elements = r.split(" ");
				
				String action = elements[0];
				String uId = elements[1];
				
				// 나가지 않은 경우엔 이름을 계속 업데이트한다.
				// 1. 처음 입장
				// 2. 다시 입장
				// 3. 닉네임 변경
				if(!action.equals("Leave")) {
					// update user
					users.put(uId, elements[2]);
				}
				
				// 변경하는 경우엔 히스토리에 추가하지 않음
				if(!action.equals("Change")) {
					// add history
					histories.add(new History(uId, action));
				}
			}
			
			String[] answer = new String[histories.size()];
			for(int i = 0;i < histories.size();i++) {
				String name = users.get(histories.get(i).getUid());
				String action = histories.get(i).getAction().equals("Enter") ? "들어왔습니다." : "나갔습니다.";
				
				answer[i] = String.format("%s님이 %s", name, action);
			}
			
			return answer;
		}
		
		class History {
			private String uid;
			private String action;
			
			public History(String uid, String action) {
				this.uid = uid;
				this.action = action;
			}
			public String getUid() {
				return uid;
			}
			public void setUid(String uid) {
				this.uid = uid;
			}
			public String getAction() {
				return action;
			}
			public void setAction(String action) {
				this.action = action;
			}
		}
	}
}