package study;

import java.util.Scanner;

public class nexpComb {

	static int N, M;
	static int[] inputs, p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		inputs = new int[N];
		p = new int[N];

		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}

		int cnt = 0;
		while(++cnt <= M) p[N - cnt] = 1;
		
		do { 
			for (int i = 0; i < N; i++) {
				if(p[i] == 1) System.out.print(inputs[i] + " ");
			}
			System.out.println();
		}while(nc(p));

		sc.close();
	}

	public static boolean nc(int[] numbers) {
		int i = N - 1;
		while(i > 0 && numbers[i-1] >= numbers[i]) i--;
		
		if(i == 0) return false;
		
		int j = N - 1;
		while(numbers[i-1] >= numbers[j]) j--;
		
		swap(numbers, i - 1, j);
		
		int k = N -1;
		while(i < k) swap(numbers, i++, k--);
		
		return true;
	}

	public static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
