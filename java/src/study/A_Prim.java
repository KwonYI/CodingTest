package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Prim {
	
	static int N;
	static int[][] arr;
	static boolean[] visited;
	
	static int[] minEdge;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(bf.readLine());
		
		visited = new boolean[N];
		arr = new int[N][N];
		minEdge = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		minEdge[0] = 0;
		int min = 0, minVertex = 0, total = 0;
		
		for (int c = 0; c < N; c++) {
			min = Integer.MAX_VALUE;
			minVertex = 0;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					minVertex = i;
					min = minEdge[i];
				}
			}
			
			total += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && arr[minVertex][i] != 0 && minEdge[i] > arr[minVertex][i]) {
					minEdge[i] = arr[minVertex][i];
				}
			}
			
			for (int ele : minEdge) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
		
		System.out.println(total);
		
	}
}