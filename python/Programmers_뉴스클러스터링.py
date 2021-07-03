from collections import Counter

def solution(str1, str2):
    str1 = str1.lower()
    str2 = str2.lower()
    str1 = [str1[i:i+2] for i in range(len(str1) - 1) if str1[i:i+2].isalpha()]
    str2 = [str2[i:i+2] for i in range(len(str2) - 1) if str2[i:i+2].isalpha()]
    
    inter, union = set(str1) & set(str2), set(str1) | set(str2)
    
    str1, str2 = Counter(str1), Counter(str2)
    A = sum([min(str1[i], str2[i])for i in inter])
    B = sum([max(str1[u], str2[u])for u in union])
    
    return int(65536 * A / B) if B != 0 else 65536