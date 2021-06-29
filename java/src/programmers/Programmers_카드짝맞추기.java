package programmers;

// ex) 1-1번 카드와 1-2번카드까지의 거리가 같을경우에 대한 예외처리가 테케에는 없다
// 거리가 같으면 1-1위치에서 2-1,2-2 뽑으러갈때, 1-2위치에서 2-1, 2-2 뽑으러갈떄의 값이 달라질수있다.

import java.util.*;

class Programmers_카드짝맞추기 {
    
    class Pos{
        int r, c, cnt;
        
        public Pos(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.cnt = t;
        } 
    }
    
    final int R = 4, C = 4;
    final int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0 , 1 } }; 
    
    int answer;
    int[] selected;
    int[][] Board;
    HashMap<Integer, Pos> cards1 = new HashMap<>();
    HashMap<Integer, Pos> cards2 = new HashMap<>();
    
    public int solution(int[][] board, int r, int c) {
        int cardCnt = 0;
        Board = copy(board);
        
        // 1. 카드 위치 저장
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(board[i][j] != 0){
                    int card = board[i][j];
                    
                    if(cards1.containsKey(card)){
                        cards2.put(card, new Pos(i, j, 0));
                    }else{
                        cards1.put(card, new Pos(i, j, 0));
                    }
                    
                    cardCnt++;
                }
            }
        }
        
        selected = new int[cardCnt / 2];
        answer = 987654321;
        
        selectCard(0, 0, cardCnt, r, c, board);
        
        return answer;
    }
    
    public void selectCard(int cnt, int flag, int cardCnt, int r, int c, int[][] board){
        if(cnt == cardCnt / 2){
            play(selected, r, c, board);
            return;
        }
        for(int i = 1; i <= cardCnt / 2; i++){
            if((flag & (1 << i)) != 0) continue;
            selected[cnt] = i;
            selectCard(cnt + 1, flag | (1 << i), cardCnt, r, c, board);
        }
    }
    
    public void play(int[] selected, int r, int c, int[][] board){
        int moveCnt = 0;
        
        for(int card : selected){
            Pos card1 = cards1.get(card);
            Pos card2 = cards2.get(card);
            Pos cur = new Pos(r, c, 0);
            
            int dist1 = getMoveCnt(cur, card1) + getMoveCnt(card1, card2);
            int dist2 = getMoveCnt(cur, card2) + getMoveCnt(card2, card1);
            
            if(dist1 > dist2){
                moveCnt += (dist2 + 2);
                r = card1.r;
                c = card1.c;
            }else{
                moveCnt += (dist1 + 2);
                r = card2.r;
                c = card2.c;
            }
            
            Board[card1.r][card1.c] = Board[card2.r][card2.c] = 0;
        }
        
        answer = Math.min(answer, moveCnt);
        Board = copy(board);
        return;
    }
    
    public int getMoveCnt(Pos o1, Pos o2){
        boolean[][] visited = new boolean[R][C];
        Queue<Pos> q = new LinkedList<>();
        q.add(o1);
        int moveCnt = 0;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            int r = cur.r;
            int c = cur.c;
            int cnt = cur.cnt;
            
            if(r == o2.r && c == o2.c){
                moveCnt = cnt;
                break;
            }
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if(nr < 0 || nc < 0 || nr > R - 1 || nc > C - 1) {
                    continue;
                }
                
                q.add(new Pos(nr, nc, cnt + 1));
                
                while(true){
                	int nnr = nr + dir[0];
                	int nnc = nc + dir[1];
                	
                    if(nnr < 0 || nnc < 0 || nnr > R - 1 || nnc > C - 1 || Board[nr][nc] != 0){
                        break;
                    }
                    nr = nnr;
                    nc = nnc;
                }
                
                q.add(new Pos(nr, nc, cnt + 1));
            }
        }
        
        return moveCnt;
    }
    
    public int[][] copy(int[][] board){
        int[][] map = new int[R][C];
        
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                map[r][c] = board[r][c];
            }
        }
        
        return map;
    }
}