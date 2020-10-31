'''
Class implementing the stack data structure
'''
class Stack:
    def __init__(self):
        self.__element = []

    def pop(self):
        #return self.__element.pop(0)
        if self.is_empty(): 
            return None

        top = len(self.__element) - 1
        item = self.__element[top] 
        del self.__element[top]
        
        return item

    def push(self, element):
        self.__element.append(element)

    def __len__(self):
        return len(self.__element) 

    def is_empty(self):
        return (len(self.__element) == 0)

stack = Stack()
print(len(stack))

stack.push(5)
stack.push(6)
stack.push(7)

print(stack.pop())
print(len(stack))