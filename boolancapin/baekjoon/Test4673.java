package baekjoon.bruteforce;

/**
 * 셀프 넘버
 * 
 * @see https://www.acmicpc.net/problem/4673
 * @author boolancpain
 */
public class Test4673 {
	public static void main(String[] args) {
		int size = 100;
		
		int[] arr = new int[size + 1];
		for(int i = 1;i < size;i++) {
			int num = i;
			int total = num;
			
			while(num > 0) {
				total += num % 10;
				num /= 10;
			}
			
			if(total <= size) {
				// 생성자가 있는 숫자에 flag
				arr[total] = 1;
			}
		}
		
		for(int i = 1;i <= size;i++) {
			if(arr[i] == 0) {
				System.out.println(i);
			}
		}
	}
}