package programmers;

import java.util.*;

class Programmers_[3차]파일명정렬 {
    
    class Name implements Comparable<Name>{
        String head;
        int num;
        int index;

        public Name(String h, int n, int i){
            this.head = h;
            this.num = n;
            this.index = i;
        }

        public int compareTo(Name o){
            int c1 = this.head.compareTo(o.head);
            if(c1 == 0){
                if(this.num == o.num){
                    return this.index - o.index;
                }else{
                    return this.num - o.num;
                }
            }else{
                return c1;
            }
        }
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<Name> pq = new PriorityQueue<>();

        int index = 0;
        for(String file : files){
            int numIndex = 0;
            StringBuilder head = new StringBuilder();

            for(int i = 0; i < file.length(); i++){
                char c = file.charAt(i);
                int type = c - '0';

                if(type >= 0 && type <= 9){
                    numIndex = i;
                    break;
                }else if(type >= 17 && type <= 42){
                    char newC = (char) (type + 32 + (int) '0');
                    head.append(newC);
                }else{
                    head.append(c);
                }
            }

            StringBuilder numStr = new StringBuilder();
            for(int i = numIndex; i < file.length(); i++){
                int type = file.charAt(i) - '0';

                if(type >= 0 && type <= 9){
                    numStr.append(type);
                }else{
                    break;
                }
            }

            int num = Integer.valueOf(numStr.toString());
            pq.add(new Name(head.toString(), num, index++));
        }

        index = 0;
        while(!pq.isEmpty()){
            Name cur = pq.poll();
            answer[index++] = files[cur.index];
        }

        return answer;
    }
}