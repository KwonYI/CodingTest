package baekjoon;

import java.io.*;

public class BAEK_2529 {
	
	static int k;
	static int[] selected;
	static char[] condition;
	static boolean first; // 제일 작은 수
	static String min, max;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.valueOf(bf.readLine());
		
		selected = new int[k + 1];
		condition = new char[k];
		
		String[] input = bf.readLine().split(" ");
		for (int i = 0; i < k; i++) {
			condition[i] = input[i].charAt(0);
		}
		
		check(0, 0);
		
		System.out.println(max + "\n" + min);
	}

	private static void check(int cnt, int flag) {
		if(cnt == k + 1) {
			for (int i = 0; i < k; i++) {
				if(condition[i] == '<') {
					if(selected[i] >= selected[i + 1]) {
						return;
					}
				}else {
					if(selected[i] <= selected[i + 1]) {
						return;
					}
				}
			}
			
			StringBuilder sub = new StringBuilder();
			for (int num : selected) {
				sub.append(num);
			}
			
			if(!first) {
				min = sub.toString();
				first = true;
			}
			max = sub.toString();
			
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if((flag & (1 << i)) != 0) continue;
			selected[cnt] = i;
			check(cnt + 1, flag | (1 << i));
		}
	}
}