package programmers;

import java.util.*;

class Programmers_숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int bIndex = B.length - 1;
        
        for(int i = A.length - 1; i >= 0; i--){
            int a = A[i];
            int b = B[bIndex];
            
            if(a < b){
                answer++;
                bIndex--;
            }
        }
        
        return answer;
    }
}
