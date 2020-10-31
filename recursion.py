'''
Classes showing recursion in play
'''
def message(times):
    if times >= 0:
        print(f'In a recursion {times}')
        message(times - 1)

message(3)

#Adding numbers using recursion
def sum(num_list, start, end):
    if start > end:
        return 0
    return num_list[start] + sum(num_list, start+1, end)

#Adding numbers using a for loop 
def sum1(num_list):
    total = 0
    for i in num_list:
        total = total + i
        i = i + 1
    return total

list1 = [1,2,3]

print(sum(list1, 0, 2))
print(sum1(list1))
