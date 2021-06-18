def solution(absolutes, signs):
    return sum([i * j for i, j in zip(absolutes, [ 1 if sign else -1 for sign in signs])])