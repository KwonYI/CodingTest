package programmers;

class Programmers_자물쇠와열쇠 {
    
    int N, M;
    int[][] Lock, Key;
    boolean answer = false;
    
    public boolean solution(int[][] key, int[][] lock) {
        Key = key;
        Lock = lock;
        M = key.length;
        N = lock.length;
        
        ex :for(int d = 0; d < 4; d++){ // 회전
            for(int i = -M + 1; i < N; i++){ // lock[0][0] + key[M - 1][M - 1] 부터
                for(int j = -M + 1; j < N; j++){ // lock[N - 1][N - 1] + key[0][0] 까지
                    if(match(i, j)){
                        answer = true;
                        break ex;
                    }
                }
            }
            Key = rotate(Key);
        }
        
        return answer;
    }
    
    public boolean match(int i, int j){
        int[][] map = new int[N][N];
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                map[r][c] = Lock[r][c];
            }
        }
        
        for(int r = 0; r < M; r++){
            int nr = r + i;
            if(nr < 0 || nr > N - 1) continue;
            for(int c = 0; c < M; c++){
                int nc = c + j;
                if(nc < 0 || nc > N - 1) continue;
                map[nr][nc] += Key[nr - i][nc - j];
            }
        }
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                if(map[r][c] != 1) return false;
            }
        }
        
        return true;
    }
    
    public int[][] rotate(int[][] key){
        int M = key.length;
        int[][] newKey = new int[M][M];
        for(int r = 0; r < M; r++){
            for(int c = 0; c < M; c++){
                newKey[r][c] = key[M - c - 1][r];
            }
        }
        return newKey;
    }
}