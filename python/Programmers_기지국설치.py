def solution(n, stations, w):
    cover_range = 2 * w + 1
    answer = 0
    answer += build(1, stations[0] - w - 1, cover_range)
    answer += build(stations[-1] + w + 1, n, cover_range)
    
    left = stations[0]
    for right in stations[1:] :
        left_no = left + w + 1
        right_no = right - w - 1
        
        answer += build(left_no, right_no, cover_range)
        left = right
        
    return answer

def build(left, right, cover_range) :
    if left > right :
         return 0
    else :
        diff = right - left + 1
        if diff % cover_range == 0 :
            return diff // cover_range
        else :
            return diff // cover_range + 1
