A, B, V = map(int, input().split(" "))

day = 1
L = A - B
V -= A

if V % L != 0 :
  day += (V / L + 1)
else :
  day += (V / L)

print(day)