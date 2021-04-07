def solution(new_id):
    new_id = new_id.lower()
    
    answer = ''
    for s in new_id :
        if s.isalpha() or s.isdigit() or s in ['-', '_', '.'] :
            answer += s
    
    while '..' in answer :
        answer = answer.replace('..', '.')
        
    if answer[0] == '.' or answer[-1] == '.':
        if answer[0] != '.' :
            answer = answer[:-1]
        elif answer[-1] != '.' :
            answer = answer[1:]
        else :
            answer = answer[1:-1]
        
    answer = 'a' if len(answer) == 0 else a[:15]
    
    while len(answer) != 3:
        answer += answer[-1]
        
    return answer