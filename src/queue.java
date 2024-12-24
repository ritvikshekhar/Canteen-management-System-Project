import java.io.Serializable;
import java.util.LinkedList;

public class queue<order> implements Serializable {
    LinkedList<order> list;

    public queue() {
        list = new LinkedList<>();
    }

    public void push(order t) {
        list.add(t);
    }

    public order pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public order top() {
        return list.peekFirst();
    }

    public void Traverse() {
        for (order element : list) {
            System.out.println(element);
        }
    }

}
//
