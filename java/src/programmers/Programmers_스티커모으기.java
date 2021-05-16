package programmers;

// 경계값 주의
class  Programmers_스티커모으기{
    public int solution(int sticker[]) {
        int length = sticker.length;
        
        if(length == 1){
            return sticker[0];
        }
        
        int[] p1 = new int[length + 2];
        p1[0] = p1[1] = 0;
        for(int i = 2; i <= length; i++){
            p1[i] = Math.max(p1[i - 1], p1[i - 2] + sticker[i - 1]);
        }
        
        int[] p2 = new int[length + 2];
        p2[0] = p2[1] = 0;
        for(int i = 2; i <= length; i++){
            p2[i] = Math.max(p2[i - 1], p2[i - 2] + sticker[i - 2]);
        }
        
        return Math.max(p1[length], p2[length]);
    }
}
