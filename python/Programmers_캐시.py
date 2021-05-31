# cache = collections.deque(maxlen=cacheSize)?
def solution(cacheSize, cities):
    if cacheSize == 0 :
        answer = 5 * len(cities)
    else :
        cache = []
        answer = 0
        
        for i in range(len(cities)) :
            city = cities[i].lower()
            
            if city in cache :
                answer += 1
                cache.remove(city)
            else :
                answer += 5
                
            cache.append(city)
            cache = cache[-cacheSize:]
                      
    return answer