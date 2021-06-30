def solution(numbers, target):
    answer = [0]
    
    for number in numbers :
        sub = []
        for a in answer :
            sub.append(a + number)
            sub.append(a - number)
        answer = sub
        
    return answer.count(target)