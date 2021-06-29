package programmers;

import java.util.*;

class Programmers_카카오프렌즈컬러링북 {
    
    class Pixel{
        int r;
        int c;
        
        public Pixel(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    boolean[][] visited;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(picture[r][c] != 0 && !visited[r][c]){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, find(r, c, picture, m, n));
                }
            }
        }

        int[] answer = new int[]{numberOfArea, maxSizeOfOneArea};
        return answer;
    }
    
    public int find(int R, int C, int[][] picture, int m, int n){
        int size = 0;
        int color = picture[R][C];
        
        Queue<Pixel> q = new LinkedList<>();
        q.add(new Pixel(R, C));
        
        while(!q.isEmpty()){
            Pixel p = q.poll();
            
            int r = p.r;
            int c = p.c;
            
            if(visited[r][c]){
                continue;
            }
            
            visited[r][c] = true;
            size++;
            
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if(nr < 0 || nc < 0 || nr > m - 1 || nc > n - 1 || visited[nr][nc] || picture[nr][nc] != color){
                    continue;
                }
                
                q.add(new Pixel(nr, nc));
            }
        }
        
        return size;
    }
}