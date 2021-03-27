package study;

import java.util.Arrays;
import java.util.Scanner;

public class nexpPer {

	static int N;
	static int[] inputs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		inputs = new int[N];

		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}

		Arrays.sort(inputs);
		
		do {
			System.out.println(Arrays.toString(inputs));
		}while(np(inputs));

		sc.close();
	}

	public static boolean np(int[] numbers) {
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
