A, B = input(), input()

while True :
  if len(A) == len(B) :
    break

  B, last = B[:-1], B[-1]

  if last == 'B' :
    B = B[::-1]

print(1 if A == B else 0)  