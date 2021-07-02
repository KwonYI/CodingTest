def solution(s):
    answer = []
    
    for ele in s :
        if answer[-1:] == [ele] :
            answer.pop()
        else :
            answer.append(ele)
            
    return 1 if len(answer) == 0 else 0