package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로 탈출
 * 
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/159993
 * @author boolancpain
 */
public class Test11 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		String[] maps = new String[] {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		int expect = 16;
		int result = solution.solution(maps);
		System.out.println(String.format("[테스트 케이스 1] : %s", expect == result ? "통과" : "실패"));
		
		maps = new String[] {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"};
		expect = -1;
		result = solution.solution(maps);
		System.out.println(String.format("[테스트 케이스 2] : %s", expect == result ? "통과" : "실패"));
		
		maps = new String[] {"LOSOO", "XXOXO", "EOOOO"};
		expect = 8;
		result = solution.solution(maps);
		System.out.println(String.format("[테스트 케이스 3] : %s", expect == result ? "통과" : "실패"));
		
		maps = new String[] {"SOOOO", "OOOOO", "OOOOO", "OOOOO", "OOOLE"};
		expect = 8;
		result = solution.solution(maps);
		System.out.println(String.format("[테스트 케이스 4] : %s", expect == result ? "통과" : "실패"));
	}
	
	public static class Solution {
		public int solution(String[] maps) {
			int x = maps.length;
			int y = maps[0].split("").length;
			
			int[][] convertMaps = new int[x][y];
			
			int[] start = new int[2];
			int[] lever = new int[2];
			int[] end = new int[2];
			for(int i = 0;i < x;i++) {
				String[] mapRow = maps[i].split("");
				for(int j = 0;j < y;j++) {
					switch (mapRow[j]) {
					case "S":
						start = new int[] {i, j};
						break;
					case "E":
						end = new int[] {i, j};
						convertMaps[i][j] = 1;
						break;
					case "L":
						lever = new int[] {i, j};
						convertMaps[i][j] = 2;
						break;
					case "O":
						convertMaps[i][j] = 3;
						break;
					default:
						convertMaps[i][j] = 4;
					}
				}
			}
			
			int startToLever = search(convertMaps, start, lever);
			if(startToLever == -1) {
				return -1;
			}
			int leverToEnd = search(convertMaps, lever, end);
			if(leverToEnd == -1) {
				return -1;
			}
			return startToLever + leverToEnd;
		}
		
		public int search(int[][] maps, int[] start, int[] target) {
			// 상하좌우 x, y 이동
			int[] moveX = new int[] {0, 0, -1, 1};
			int[] moveY = new int[] {-1, 1, 0, 0};
			
			int sizeX = maps.length;
			int sizeY = maps[0].length;
			
			Queue<int[]> q = new LinkedList<>();
			// 방문 기룩
			boolean[][] visited = new boolean[sizeX][sizeY];
			// 시작 지점 기록
			visited[start[0]][start[1]] = true;
			
			// 시작점(start)에서 갈 수 있는 모든 방향을 찾는다(상하좌우 순)
			for(int i = 0;i < 4;i++) {
				int posX = start[0] + moveX[i];
				int posY = start[1] + moveY[i];
				
				// 유효한 칸만 추가한다(유효한 미로 좌표, 방문하지 않은 칸, 'X'가 아닌 칸)
				if(posX >= 0 && posX < sizeX && posY >= 0 && posY < sizeY
						&& !visited[posX][posY]
						&& maps[posX][posY] != 4) {
					// 타겟을 찾은 경우 리턴
					if(posX == target[0] && posY == target[1]) {
						return 1;
					}
					q.offer(new int[] {posX, posY, 1});
					// 방문 체크
					visited[posX][posY] = true;
				}
			}
			
			// 이동 가능한 큐가 있는 경우 다음 이동 경로를 탐색한다.
			while(!q.isEmpty()) {
				int[] curr = q.poll();
				
				for(int i = 0;i < 4;i++) {
					int posX = curr[0] + moveX[i];
					int posY = curr[1] + moveY[i];
					
					// 유효한 칸만 추가한다(유효한 미로 좌표, 방문하지 않은 칸, 'X'가 아닌 칸)
					if(posX >= 0 && posX < sizeX && posY >= 0 && posY < sizeY
							&& !visited[posX][posY]
							&& maps[posX][posY] != 4) {
						// 타겟을 찾은 경우 리턴
						if(posX == target[0] && posY == target[1]) {
							return curr[2] + 1;
						}
						q.offer(new int[] {posX, posY, curr[2] + 1});
						// 방문 체크
						visited[posX][posY] = true;
					}
				}
			}
			
			return -1;
		}
	}
}