from collections import Counter
from itertools import combinations

def solution(orders, course):
    answer = []
    orders = list(sorted(order) for order in orders)
    
    for num in course :
        course_candidate = []
        for order in orders :
            course_candidate += combinations(order, num)
        
        course_candidate = Counter(course_candidate).most_common()
        most_popular = course_candidate
        
        if most_popular and most_popular[0][1] > 1:
            answer += ["".join(course) for course, cnt in course_candidate if cnt == most_popular[0][1]]
                
    return sorted(answer)