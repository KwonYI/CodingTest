package Goorm;

import java.io.*;
import java.util.*;

class Goorm_뒤집기정렬 {
	
	static class Work{
		int cnt;
		String arr;
		
		public Work(int c, String arr){
			this.cnt = c;
			this.arr = arr;
		}
	}
	
	static int n;
	static int answer = 0;
	static String arr, sorted;
	static HashSet<String> visited = new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(bf.readLine());
		arr = bf.readLine();
		
		String[] sorting = arr.split(" ");
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < sorting.length; i++){
			pq.add(Integer.valueOf(sorting[i]));
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()){
			sb.append(pq.poll()).append(' ');
		}
		sb.setLength(sb.length() - 1);
		sorted = sb.toString();
		
		Queue<Work> q = new LinkedList<>();
		q.add(new Work(0, arr));
		
		while(!q.isEmpty()){
			Work w = q.poll();
			
			int cnt = w.cnt;
			String arr = w.arr;
			
			if(sorted.equals(arr)){
				answer = cnt;
				break;
			}
			
			if(!visited.add(arr)){
				continue;
			}
			
			for(int i = 0; i < n - 1; i++){
				for(int j = i + 1; j < n; j++){
					String newStr = makeStr(arr, i, j);
					q.add(new Work(cnt + 1, newStr));
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static String makeStr(String str, int start, int end){
		String[] arr = str.split(" ");
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < start; i++) sb.append(arr[i]).append(' ');
		
		for(int i = end; i >= start; i--) sb.append(arr[i]).append(' ');
		
		for(int i = end + 1; i < n; i++) sb.append(arr[i]).append(' ');
		
		sb.setLength(sb.length() - 1);
		
		return sb.toString();
	}
}