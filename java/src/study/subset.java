package study;

import java.util.Scanner;

public class subset {

	static int N, M;
	static int[] inputs, outputs;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		inputs = new int[N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
		
		sub(0, 0);
		
		sc.close();
	}
	
	private static void sub(int cnt, int check) {
		if(cnt == N) {
			for (int i = 0; i < N; i++) {
				if((check & (1 << i)) != 0) System.out.print(inputs[i] + " ");
			}
			System.out.println();
			return;
		}
		sub(cnt + 1, check | (1 << cnt));
		sub(cnt + 1, check &= ~(1 << cnt));
	}
}
