package programmers.lv2;

/**
 * 두 원 사이의 정수 쌍
 * 
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/181187
 * @author boolancpain
 */
public class Test10 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int r1 = 2;
		int r2 = 3;
		long expect = 20;
		long result = solution.solution(r1, r2);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect == result ? "통과" : "실패"));
	}
	
	static class Solution {
		public long solution(int r1, int r2) {
			long temp = 0;
			for(int x = 1;x < r2;x++) {
				long min = (int) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2)));
				long max = (int) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
				if(min == 0) min = 1;
				temp += max - min + 1;
			}
			// 각 축의 접점과 접점을 제외한 나머지 면적의 좌표를 구한 뒤 4를 곱한다.
			return (r2 - r1 + 1) * 4 + temp * 4;
		}
	}
}