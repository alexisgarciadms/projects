'''
Class should have identifiers that point to the head and tail
Should have a method to insert at the head
Should have a method to insert at the tail
Should have a method to remove the head
Should have a method to remove tail 
Should have a method to display the contents of the linked list
'''
import string as string

class singlylinkedlist:
    def __init__(self):
        self.__head = None
        self.__tail = None

    def insertHead(self, element):
        node = Node(element)
        node.setNext(self.__head)
        self.__head = node 

    def insertTail(self, element):
        node = Node(element)
        node.setNext(None)

        previous = self.__head
        while previous.getNext() != self.__tail:
            previous = previous.getNext()
        previous = previous.setNext(node)
        self.__tail = node

    def removeHead(self):
        temp = self.__head
        self.__head = temp.getNext()
        temp = None

    def removeTail(self):
        previous = self.__head
        while previous.getNext() != self.__tail:
            previous = previous.getNext()
        self.__tail = previous
        previous.setNext(None)

    def display_values(self):
        node = self.__head
        while node != None:
            print(node.getElement())
            node = node.getNext()

class Node:
    def __init__(self, element):
        self.__element = element
        self.__next = None
    
    def getElement(self):
        return self.__element
    def getNext(self):
        return self.__next

    def setElement(self, newElement):
        self.__element = newElement
    def setNext(self, newNode): 
        self.__next = newNode

mylist = singlylinkedlist()
for char in string.ascii_uppercase:
    mylist.insertHead(char)

mylist.display_values()
print("_______________")
mylist.removeTail()
mylist.removeTail()
mylist.removeHead()
mylist.insertTail(1)
mylist.display_values()

print("_______________")

mylist2 = singlylinkedlist()
mylist2.insertHead("A")
mylist2.insertTail("B")
mylist2.display_values()




