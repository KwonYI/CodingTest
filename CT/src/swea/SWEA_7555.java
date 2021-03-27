package swea;

import java.io.*;

public class SWEA_7555 {

	static int N;
	static int answer = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.valueOf(bf.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			String A = bf.readLine();
			String B = bf.readLine();

			int lcm = A.length() * B.length() / gcd(A.length(), B.length());
			
			String newA = "";
			String newB = "";
			
			for (int i = 0; i < lcm / A.length(); i++) {
				newA += A;
			}
			
			for (int i = 0; i < lcm / B.length(); i++) {
				newB += B;
			}
			
			if(newA.equals(newB)) {
				answer = 1;
			}else {
				answer = 0;
			}

			sb.append('#').append(testCase).append(' ').append(answer).append('\n');
		}

		System.out.println(sb.toString());
	}

	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
}