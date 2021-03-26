package swea;

import java.io.*;
import java.util.*;

public class SWEA_7559 {

	static class Number{
		long num;
		int cnt;
		
		public Number(long num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	static int N;
	static Queue<Number> q = new LinkedList<>();
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.valueOf(bf.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			q.clear();
			answer = -1;
			
			q.add(new Number(A, 0));
			
			while(!q.isEmpty()) {
				Number cur = q.poll();
				
				long num = cur.num;
				
				if(num == B) {
					answer = cur.cnt + 1;
					break;
				}
				
				long next = num*2;
				if(next <= B) {
					q.add(new Number(next, cur.cnt + 1));
				}
				
				next = num * 10 + 1;
				if(next <= B) {
					q.add(new Number(next, cur.cnt + 1));
				}
			}
			
			sb.append('#').append(testCase).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb.toString());
	}
}