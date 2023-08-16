package programmers.lv2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 주차 요금 계산
 * 
 * @see https://programmers.co.kr/learn/courses/30/lessons/92341
 * @author boolancpain
 */
public class Test6 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] fees = new int[] {180, 5000, 10, 600};
		String[] records = new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] expect = new int[] {14600, 34400, 5000};
		int[] result = solution.solution(fees, records);
		System.out.println(String.format("[테스트 케이스 1] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		fees = new int[] {120, 0, 60, 591};
		records = new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
		expect = new int[] {0, 591};
		result = solution.solution(fees, records);
		System.out.println(String.format("[테스트 케이스 2] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
		
		fees = new int[] {1, 461, 1, 10};
		records = new String[] {"00:00 1234 IN"};
		expect = new int[] {14841};
		result = solution.solution(fees, records);
		System.out.println(String.format("[테스트 케이스 3] : %s", Arrays.equals(expect, result) ? "통과" : "실패"));
	}
	
	static class Solution {
		public int[] solution(int[] fees, String[] records) {
			HashMap<String, History> parkings = new HashMap<>();
			
			for(String record : records) {
				// 입/출차 시간, 차량번호, 내역
				String[] detailRecord = record.split(" ");
				
				String time = detailRecord[0];
				String carNumber = detailRecord[1];
				String action = detailRecord[2];
				
				History history = parkings.containsKey(carNumber) ? parkings.get(carNumber) : new History();
				
				if(action.equals("IN")) {
					// 입차
					history.enter(time);
				} else {
					// 출차
					history.exit(time);
				}
				
				// map 저장
				if(!parkings.containsKey(carNumber)) {
					parkings.put(carNumber, history);
				}
			}
			
			String[] cars = parkings.keySet().toArray(new String[parkings.size()]);
			// 차량번호 오름차순 정렬
			Arrays.sort(cars);
			
			for(String c : cars) {
				History history = parkings.get(c);
				if(history.getInTime() != null) {
					// 입차 후 출차가 없는 경우 23시 59분으로 출차 처리
					history.exit("23:59");
				}
				
				// 주차 요금 계산
				history.calc(fees);
			}
			
			// 주차 요금 입력
			int[] answer = new int[cars.length];
			for(int i = 0;i < cars.length;i++) {
				answer[i] = parkings.get(cars[i]).getFee();
			}
			
			return answer;
		}
		
		class History {
			private int fee;	// 요금
			private int totalPark;	// 누적 주차 시간
			private LocalTime inTime;	// 입차 시간
			
			public int getFee() {
				return fee;
			}
			public void setFee(int fee) {
				this.fee = fee;
			}
			public int getTotalPark() {
				return totalPark;
			}
			public void setTotalPark(int totalPark) {
				this.totalPark = totalPark;
			}
			public LocalTime getInTime() {
				return inTime;
			}
			public void setInTime(LocalTime inTime) {
				this.inTime = inTime;
			}
			
			// 입차
			public void enter(String inTime) {
				String h = inTime.split(":")[0];
				String m = inTime.split(":")[1];
				this.inTime = LocalTime.of(Integer.parseInt(h), Integer.parseInt(m));
			}
			
			// 출차
			public void exit(String outTime) {
				String h = outTime.split(":")[0];
				String m = outTime.split(":")[1];
				// 출차시간
				LocalTime outLocalTime = LocalTime.of(Integer.parseInt(h), Integer.parseInt(m));
				
				// 누적 주차시간(분)
				this.totalPark += (int) ChronoUnit.MINUTES.between(this.inTime, outLocalTime);
				
				// 입차시간 초기화
				this.inTime = null;
			}
			
			// 주차요금 계산
			public void calc(int[] fees) {
				// fees : 기본 주차시간, 기본요금, 추가 시간당, 추가요금
				// 기본 요금부과
				this.fee += fees[1];
				
				// 기본 주차시간을 초과한 경우
				if(this.totalPark > fees[0]) {
					int remain = this.totalPark - fees[0];
					// 추가 요금 부과 횟수
					int add = remain % fees[2] > 0 ? remain / fees[2] + 1 : remain / fees[2];
					// 추가 요금 부과
					this.fee += add * fees[3];
				}
			}
		}
	}
}