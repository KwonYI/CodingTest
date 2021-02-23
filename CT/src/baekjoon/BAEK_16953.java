package baekjoon;

import java.util.*;

public class BAEK_16953 {
	
	static class Element{
		long num;
		int cnt;
		
		public Element(long num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	static HashSet<Long> visited = new HashSet<>();
	static final int LIMIT = 1000000000;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int start = sc.nextInt();
		int target = sc.nextInt();
		int answer = -1;
		
		Queue<Element> q = new LinkedList<Element>();
		q.add(new Element(start, 1));
		visited.add((long) start);
		
		while (!q.isEmpty()) {
			Element cur = q.poll();
			
			long num = cur.num;
			int cnt = cur.cnt;
			
			if(num == target) {
				answer = cnt;
				break;
			}
			
			long newNum = num*2;
			if(newNum <= LIMIT && visited.add(newNum)) {
				q.add(new Element(newNum, cnt + 1));
			}
			
			newNum = num*10 + 1;
			if(newNum <= LIMIT && visited.add(newNum)) {
				q.add(new Element(newNum, cnt + 1));
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}
