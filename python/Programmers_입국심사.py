def solution(n, times):
    times.sort()
    answer = 100000 * 1000000000 + 1
    left, right = 1, n * times[-1]
    
    while left <= right :
        mid = (left + right) // 2
        
        if possible(times, n, mid) :
            answer = min(answer, mid)
            right = mid - 1
        else :
            left = mid + 1
    
    return answer

def possible(times, n, mid) :
    amount = sum(map(lambda x : mid // x, times))
    return amount >= n
    