def solution(n, lost, reserve):
    n -= len(lost)
    
    reserve_but_lost = set(reserve) & set(lost)
    
    n += len(reserve_but_lost)
    
    for index in reserve_but_lost :
        lost.remove(index)
        reserve.remove(index)
        
    for l in lost :
        if l - 1 in reserve : 
            reserve.remove(l - 1)
            n += 1
        elif l + 1 in reserve :
            reserve.remove(l + 1)
            n += 1
        else : pass
    return n