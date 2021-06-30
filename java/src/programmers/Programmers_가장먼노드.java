package programmers;

import java.util.*;

public class Programmers_가장먼노드 {
	class Node implements Comparable<Node>{
        int index;
        int price;
        
        public Node(int i, int p){
            this.index = i;
            this.price = p;
        }
        
        public int compareTo(Node o){
            return this.price - o.price;
        }
    }
    
    public int solution(int n, int[][] edge) {
        ArrayList<Integer> arr[] = new ArrayList[n + 1];
        int[] p = new int[n + 1];
        
        for(int i = 1; i <= n; i++){
            arr[i] = new ArrayList<>();
            p[i] = Integer.MAX_VALUE;
        }
        
        for(int[] e : edge){
            int from = e[0];
            int to = e[1];
            
            arr[from].add(to);
            arr[to].add(from);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        int maxLength = 0;
        p[1] = 0;
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            
            int cur = node.index;
            int price = node.price;
            
            for(int next : arr[cur]){
                if(p[next] > p[cur] + 1){
                    p[next] = p[cur] + 1;
                    maxLength = Math.max(maxLength, p[next]);
                    pq.add(new Node(next, p[next]));
                }
            }
        }
        
        int answer = 0;
        for(int i = 1; i <= n; i++){
            if(p[i] == maxLength){
                answer++;
            }
        }
        
        return answer;
    }
}