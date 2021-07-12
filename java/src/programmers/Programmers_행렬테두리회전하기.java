package programmers;

import java.util.*;

class Programmers_행렬테두리회전하기 {
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int r = 0; r < rows; r++){
            for(int c = 1; c <= columns; c++){
                map[r][c - 1] = r * columns + c;
            }
        }
        
        for(int i = 0; i < queries.length; i++){
            int sr = queries[i][0] - 1;
            int sc = queries[i][1] - 1;
            int tr = queries[i][2] - 1;
            int tc = queries[i][3] - 1;
            
            int lt = map[sr][sc];
            int rt = map[sr][tc];
            int lb = map[tr][sc];
            int rb = map[tr][tc];
            
            pq.add(lt);
            pq.add(rt);
            pq.add(lb);
            pq.add(rb);
            
            for(int c = tc - 1; c > sc; c--){
                pq.add(map[sr][c]);
                map[sr][c + 1] = map[sr][c];
            }
            
            for(int r = tr - 1; r > sr; r--){
                pq.add(map[r][tc]);
                map[r + 1][tc] = map[r][tc];
            }
            
            for(int c = sc + 1; c < tc; c++){
                pq.add(map[tr][c]);
                map[tr][c - 1] = map[tr][c];
            }
            
            for(int r = sr + 1; r < tr; r++){
                pq.add(map[r][sc]);
                map[r - 1][sc] = map[r][sc];
            }
            
            map[sr][sc + 1] = lt;
            map[sr + 1][tc] = rt;
            map[tr - 1][sc] = lb;
            map[tr][tc - 1] = rb;
            
            answer[i] = pq.poll();
            pq.clear();
        }
        
        return answer;
    }
}