package swea;

import java.io.*;
import java.util.*;
 
public class SWEA_1949 {
 
    static int N, K;
    static int top;
    static int answer;
     
    static int[][] map;
    static boolean[][] visited;
    static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
 
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.valueOf(bf.readLine());
 
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(bf.readLine());
             
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            map = new int[N][N];
            visited = new boolean[N][N];
             
            top = 0;
            answer = 0;
             
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(bf.readLine());
                for (int c = 0; c < N; c++) {
                    int cur = Integer.parseInt(st.nextToken());
                    map[r][c] = cur;
                    top = Math.max(top, cur);
                }
            }
             
            // 봉우리에서 시작
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(map[r][c] == top) {
                        find(r, c, top, false, 1);
                    }
                }
            }
             
            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }
 
        System.out.println(sb);
    }
 
    private static void find(int r, int c, int top, boolean use, int len) {
        answer = Math.max(answer, len);
         
        visited[r][c] = true;
         
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
             
            if(!check(nr,nc) || visited[nr][nc]) continue;
             
            if(map[nr][nc] < top) find(nr, nc, map[nr][nc], use, len + 1);
            else if(!use && map[nr][nc] - K < top) find(nr, nc, top - 1, true, len + 1);
        }
         
        visited[r][c] = false;
    }
     
    private static boolean check(int r, int c) {
        if(r < 0 || c < 0 || r > N - 1|| c > N - 1) return false;
        return true;
    }
}