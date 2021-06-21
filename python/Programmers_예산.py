def solution(d, budget):
    d.sort()
    answer = 0
    
    for num in d :
        if budget < num :
            break
        else :
            budget -= num
            answer += 1
        
    return answer