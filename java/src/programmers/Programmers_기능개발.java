import java.util.*;

class Programmers_기능개발 {

    int M;
    Queue<Integer> q = new LinkedList<>();
    ArrayList<Integer> arr = new ArrayList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        M = progresses.length;

        for(int i = 0; i < M; i++){
            int remain = 100 - progresses[i];
            int speed = speeds[i];
            int complete = remain / speed;

            if(remain % speed != 0){
                complete++;
            }

            q.add(complete);
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            int cnt = 1;

            while(!q.isEmpty()){
                if(cur >= q.peek()){
                    cnt++;
                    q.poll();
                }else{
                    break;
                }
            }

            arr.add(cnt);
        }

        int[] answer = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            answer[i] = arr.get(i);
        }

        return answer;
    }
}