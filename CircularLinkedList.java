import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
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
    private Node<T> tail, current;
    
    // this function adds data to the end of the linked list
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if(tail == null) {
            tail = newNode;
            tail.next = tail;
            current = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
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
    
    public int rollDie() {
        Random random = new Random();
        int firstRoll, secondRoll;
        firstRoll = random.nextInt(6) + 1;
        secondRoll = random.nextInt(6) + 1;
        return firstRoll + secondRoll;
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

        // Showing the program works
        for(int i = 0; i < 41; i++) {
            System.out.println(monopolyBoard.currentNode());
            monopolyBoard.step();
        }

        // Extra credit opportunity, rolling the die
        System.out.println("\nThe current position is: " + monopolyBoard.currentNode());
        int rolled = monopolyBoard.rollDie();
        for(int i = 0; i < rolled; i++) {
            monopolyBoard.step();
        }
        System.out.println("After moving " + rolled + " spaces. You are now on: " + monopolyBoard.currentNode());
    }
}
