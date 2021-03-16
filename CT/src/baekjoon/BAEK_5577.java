package baekjoon;

import java.io.*;

public class BAEK_5577 {

	static class Pang{
		int index;
		int length;
		
		public Pang(int index, int length) {
			this.index = index;
			this.length = length;
		}
	}
	
	static int N, M;
	static int[] init, balls;
	static int[] colors = { 1, 2, 3 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		M = N = Integer.valueOf(bf.readLine());

		init = new int[N];
		balls = new int[N];
		
		for (int i = 0; i < N; i++) {
			init[i] = Integer.valueOf(bf.readLine());
		}
		
		for (int i = 0; i < N; i++) {
			int cur = init[i];
			for (int change : colors) {
				if(cur != change) {
					init[i] = change;
					
					copy();
					play(N);
					
					init[i] = cur;
				}
			}
		}
		
		System.out.println(M);
	}
	
	private static void copy() {
		for (int i = 0; i < N; i++) {
			balls[i] = init[i];
		}
	}

	private static void play(int remain) {
		Pang pang = findIndex();
		
		if(pang != null) {
			delete(pang.index, pang.length);
			play(remain - pang.length);
		}else {
			M = Math.min(M, remain);
			return;
		}
	}
	
	public static Pang findIndex() {
		for (int index = 0; index <= N - 4; index++) {
			if(balls[index] == 0) continue;
			
			int curColor = balls[index];
			int curLength = 1;
			
			for (int j = index + 1; j < N; j++) {
				if(balls[j] == 0) continue;
				
				if(balls[j] != curColor) break;
				
				curLength++;
			}
			
			if(curLength >= 4) {
				return new Pang(index, curLength);
			}
		}
		
		return null;
	}
	
	private static void delete(int index, int length) {
		int cnt = 0;
		for (int i = index; i < N; i++) {
			if(balls[i] == 0) continue;
			
			balls[i] = 0;
			if(++cnt == length) break;
		}
	}
}