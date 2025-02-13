import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CircularLinkedList<T> {
    
    // Nested Node Class
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T e) {
            data = e;
            next = null;
        }
    }
    // End of Nested Node Class

    // instance variables
    private Node<T> head, current;
    
    // this function adds data to the end of the linked list
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if(head == null) {
            head = newNode;
            head.next = head;
            current = head;
        } else {
            Node<T> temp = head;
            while(temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    // this function moves forward down the linked list
    public void step() {
        if(current != null) {
            current = current.next;
        }
    }

    // this function returns the current node
    public T currentNode() {
        return current != null ? current.data : null;
    }

    public static void main(String[] args) {
        CircularLinkedList<String> monopolyBoard = new CircularLinkedList<String>();

        try(BufferedReader br = new BufferedReader(new FileReader("monopoly_spaces.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                monopolyBoard.append(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        for(int i = 0; i < 41; i++) {
            System.out.println(monopolyBoard.currentNode());
            monopolyBoard.step();
          }
    }
}
