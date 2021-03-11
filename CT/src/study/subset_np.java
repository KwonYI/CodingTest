package study;

import java.util.Scanner;

public class subset_np {

	static int N, M;
	static int[] inputs, outputs;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = 1 << N;
		
		inputs = new int[N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
		
		subNp(M);
		
		sc.close();
	}
	
	private static void subNp(int testCase) {
		for (int flag = 0; flag < testCase; flag++) {
			for (int i = 0; i < N; i++) {
				if((flag & (1 << i)) != 0) System.out.print(inputs[i] + " ");
			}
			System.out.println();
		}
	}
}
