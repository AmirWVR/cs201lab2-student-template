import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        //get the elements of the linked list first
        int listSize = size();

        // number of loop iteartions
        int iterations;
        if(listSize % 2 == 0){
            iterations = listSize / 2;
        } else {
            iterations = (listSize - 1 ) / 2;
        }

        // each iteration counts as 1 swap
        
        E prevMinValue = null;
        E prevMaxValue = null;

        for (int i = 0; i < iterations; i++) {
            Node<E> minNode = null;
            Node<E> prevOfMin = null;
            Node<E> maxNode = null;
            Node<E> prevOfMax = null;
            Node<E> prevOfCurrent = null;
            Node<E> current = head;

            while (current != null) {
                boolean inRange = (i == 0) ||
                    (current.getElement().compareTo(prevMinValue) > 0 &&
                    current.getElement().compareTo(prevMaxValue) < 0);

                if (inRange) {
                    if (minNode == null || current.getElement().compareTo(minNode.getElement()) < 0) {
                        minNode = current;
                        prevOfMin = prevOfCurrent;
                    }
                    if (maxNode == null || current.getElement().compareTo(maxNode.getElement()) > 0) {
                        maxNode = current;
                        prevOfMax = prevOfCurrent;
                    }
                }
                prevOfCurrent = current;
                current = current.next;
            }

            // swap nodes
            if (minNode == maxNode){
                break; 
            }

            if (minNode.next == maxNode){
                Node<E> nextOfMax = maxNode.next;
                if (prevOfMin == null)
                {
                    head = maxNode;
                }
                else
                {
                    prevOfMin.next = maxNode;
                }
                maxNode.next = minNode;
                minNode.next = nextOfMax;
            }
            else if (maxNode.next == minNode){
                Node<E> nextOfMin = minNode.next;
                if (prevOfMax == null)
                {
                    head = minNode;
                }
                else
                {
                    prevOfMax.next = minNode;
                }
                minNode.next = maxNode;
                maxNode.next = nextOfMin;
            } else {
                if (prevOfMin == null) {
                    head = maxNode;
                } else {
                    prevOfMin.next = maxNode;
                }

                if (prevOfMax == null) {
                    head = minNode;
                } else {
                    prevOfMax.next = minNode;
                }

                Node<E> nextOfMax = maxNode.next;
                Node<E> nextOfMin = minNode.next;
                maxNode.next = nextOfMin;
                minNode.next = nextOfMax;
            }

            prevMinValue = minNode.getElement();
            prevMaxValue = maxNode.getElement();
        }

        Node<E> newTail = head;

        while(newTail.next != null){
            newTail = newTail.next;
        }
        tail = newTail;
    }
   
}

