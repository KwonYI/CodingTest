def solution(s):
    answer = 0
    
    for i in range(len(s)) :
        new_s = s[i:] + s[:i]
        answer += check(new_s)
    
    return answer

def check(s) :
    cvt = {'(' : 0, '{' : 1, '[' : 2, ')' : 0, '}' : 1, ']' : 2}
    stack = []
    valid = 1
    
    for ele in s :
        if ele in ['(', '{', '['] :
            stack.append(cvt[ele])
        else :
            if stack[-1:] != [cvt[ele]] :
                valid = 0
                break
            else :
                stack.pop()
                
    return valid if len(stack) == 0 else 0