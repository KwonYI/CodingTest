import java.io.*;
import java.util.*;

public class BAEK_6064 {
	
	static int N;
	static HashSet<Integer> list = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.valueOf(bf.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int lcm = M * N / gcd(M, N);
			int answer = -1;

			int possible = x;
			while(possible < lcm) {
				list.add(possible);
				possible += M;
			}
			
			possible = y;
			while(possible < lcm) {
				if(list.contains(possible)) {
					answer = possible;
					break;
				}
				possible += N;
			}
			
			sb.append(answer).append('\n');
			list.clear();
		}
		
		System.out.println(sb);
	}
	
	public static int gcd(int x, int y) {
		if(x%y == 0) return y;
		else return gcd(y, x%y);
	}
}