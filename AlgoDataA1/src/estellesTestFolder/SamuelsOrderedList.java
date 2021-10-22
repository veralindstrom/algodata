/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public class SamuelsOrderedList<Datatype extends Comparable<Datatype>> implements Queue<Datatype> {
    private Node sentinel;

    public SamuelsOrderedList() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Enqueue an item to the ordered queue.
     * All elements are inserted in ascending order.
     * <p>
     * In case list is empty, the element is simply added after
     * the sentinel element.
     * <p>
     * Before starting to traverse the list until an element that is smaller
     * is found, the method checks if the <code> item </code> that is to
     * be added is larger than the element currently in the back of the
     * queue.
     *
     * @param item that is added before
     */
    @Override
    public void enqueue1(Datatype item) {
        Node node = new Node(item);
        if (isEmpty())
            addBefore(node, sentinel.next);
        else if (sentinel.prev.lessOrEquals(item))
            addBefore(node, sentinel);
        else
            add(sentinel.next, item);
    }

    /**
     * Traverses the list nodes recursively and checks
     * if the value stored in the current node is less than
     * the value.
     *
     * @param current
     * @param item
     */
    private void add(Node current, Datatype item) {
        if(item != null && current.item != null) {
            if (item.compareTo(current.item) < 0) {
                Node toAdd = new Node(item);
                addBefore(toAdd, current);
            } else {
                add(current.next, item);
            }
        }
    }

    /**
     * Add a node before the node given by the second param <code> current </code>
     *
     * @param toAdd   the node that is to be added
     * @param current the node that <code> toAdd </code> is to be added in front of
     */
    private void addBefore(Node toAdd, Node current) {
        toAdd.next = current;
        toAdd.prev = current.prev;
        toAdd.next.prev.next = toAdd;
        toAdd.next.prev = toAdd;
    }

    /**
     * dequeue element at the back of the queue
     *
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the element at the back of the queue
     */
    @Override
    public Datatype dequeue() {
        Datatype item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if(item != null)
            System.out.println("Dequeue [" + item.toString() + "]");
        print();
        return item;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    /**
     * Check if the list is currently empty
     *
     * If <code> next </code> or <code> prev </code> in the sentinel
     * element is pointing back to itself, the list is considered empty.
     * @return
     */
    @Override
    public boolean isEmpty() {
        return Objects.equals(sentinel, sentinel.next) || Objects.equals(sentinel, sentinel.prev);
    }

    @Override
    public String toString() {
        return toStringRecursive(sentinel.next, new StringBuilder());
    }

    /**
     * Appends string representation of list <code> Node </code>
     * to <code> StringBuilder </code> object recursively.
     * <p>
     * Base case:
     * the current node equals the <code>sentinel</code> node
     *
     * @param node The node that is represented as a string
     * @param sb   The <code> StringBuilder </code> object that the
     *             string representation is appended to
     *
     * @return a string representation of this queue
     */
    private String toStringRecursive(Node node, StringBuilder sb) {
        if (isSentinel(node))
            return sb.toString();
        else {
            return toStringRecursive(node.next, sb.append("[ " + node.item.toString() + " ] "));
        }
    }

    private boolean isSentinel(Node node) {
        return Objects.equals(sentinel, node);
    }

    @Override
    public Iterator<Datatype> iterator() {
        return null;
    }

    public static void main(String... args) {
        Queue<Integer> queue = new SamuelsOrderedList<>();
        queue.enqueue1(1);
        queue.print();
        queue.enqueue1(3);
        queue.print();
        queue.enqueue1(2);
        queue.print();
        queue.enqueue1(12);
        queue.print();
        queue.enqueue1(12);
        queue.print();
        queue.enqueue1(0);
        queue.print();
        queue.enqueue1(-35);
        queue.print();
        queue.enqueue1(4);
        queue.print();
        queue.enqueue1(16);
        queue.print();
        queue.dequeue();
    }

    private class Node {
        Datatype item;
        Node next;
        Node prev;

        Node() {

        }

        Node(Datatype item) {
            this.item = item;
        }

        boolean lessOrEquals(Datatype item) {
            if (this.item.compareTo(item) <= 0)
                return true;
            return false;
        }

    }
}
