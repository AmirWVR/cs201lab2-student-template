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
    public void swap()
    {
        List<Node<E>> nodeList = new ArrayList<>();
        Map<Node<E>, Node<E>> prevOf = new HashMap<>();

        Node<E> prev = null;
        Node<E> current = head;
        while (current != null)
        {
            nodeList.add(current);
            prevOf.put(current, prev);
            prev = current;
            current = current.next;
        }

        List<Node<E>> sorted = new ArrayList<>(nodeList);
        Collections.sort(sorted, (a, b) -> a.getElement().compareTo(b.getElement()));

        int lowIndex = 0;
        int highIndex = sorted.size() - 1;

        while (lowIndex < highIndex)
        {
            Node<E> minNode = sorted.get(lowIndex);
            Node<E> maxNode = sorted.get(highIndex);

            Node<E> prevOfMin = prevOf.get(minNode);
            Node<E> prevOfMax = prevOf.get(maxNode);
            Node<E> nextOfMin = minNode.next;
            Node<E> nextOfMax = maxNode.next;

            if (nextOfMin == maxNode)
            {
                if (prevOfMin == null) { head = maxNode; } else { prevOfMin.next = maxNode; }
                maxNode.next = minNode;
                minNode.next = nextOfMax;

                prevOf.put(maxNode, prevOfMin);
                prevOf.put(minNode, maxNode);
                if (nextOfMax != null) { prevOf.put(nextOfMax, minNode); }
            }
            else if (nextOfMax == minNode)
            {
                if (prevOfMax == null) { head = minNode; } else { prevOfMax.next = minNode; }
                minNode.next = maxNode;
                maxNode.next = nextOfMin;

                prevOf.put(minNode, prevOfMax);
                prevOf.put(maxNode, minNode);
                if (nextOfMin != null) { prevOf.put(nextOfMin, maxNode); }
            }
            else
            {
                if (prevOfMin == null) { head = maxNode; } else { prevOfMin.next = maxNode; }
                if (prevOfMax == null) { head = minNode; } else { prevOfMax.next = minNode; }

                maxNode.next = nextOfMin;
                minNode.next = nextOfMax;

                prevOf.put(maxNode, prevOfMin);
                prevOf.put(minNode, prevOfMax);
                if (nextOfMin != null) { prevOf.put(nextOfMin, maxNode); }
                if (nextOfMax != null) { prevOf.put(nextOfMax, minNode); }
            }

            lowIndex++;
            highIndex--;
        }

        Node<E> newTail = head;
        while (newTail.next != null)
        {
            newTail = newTail.next;
        }
        tail = newTail;
    }
}

