package edu.greenriver.dev;

import java.util.StringJoiner;

/**
 * SequentialSearchST
 * Algorithm 3.1
 * Sequential search (in an unordered linked list)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author PUT YOUR NAME HERE
 * @version 1.0
 */
public class SequentialSearchST<K, V> {

    private Node first;         // first node in the linked list
    private int size;           // keep track of # of nodes

    private class Node {
        private K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            // see Algorithm 3.1 on page 375
            this.key = key;
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + key + "|" + val + "|" + hashCode() + "]";
        }
    }

    /**
     * Create a sequential search symbol table
     */
    public SequentialSearchST() {
        first = null;
        size = 0;
    }

    /**
     * Get the value associated with a key
     * @param key Key to search for
     * @return Value associated with the key (null if key is absent)
     */
    @SuppressWarnings("NewMethodNamingConvention")
    public V get(K key) {
        // see Algorithm 3.1 on page 375
        // Search for key, and return the value
        // To search for key, I need to create a
        // "current" variable and walk through the list...
        Node current = first;

        while (current != null) {
            if (key.equals(current.key)) {
                return current.val;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Put key-value pair into the table
     * @param key Key to insert
     * @param val Value associated with the key to insert
     */
    @SuppressWarnings("NewMethodNamingConvention")
    public void put(K key, V val) {
        // see Algorithm 3.1 on page 375
        // Search the list for the key and
        // if the key already exists, replace the value.
        // If we don't find key in the list, add a new node.

        Node current = first;

        while (current != null) {
            if (key.equals(current.key)) {
                current.val = val;
                return;     // done, end the method
            }
            current = current.next;
        }

        // I know key doesn't exist, so add a new node
        // at the front
        first = new Node(key, val, first);
        size++;
    }

    /**
     * Remove key (and its value) from table
     * @param key Key to search for
     */
    public void delete(K key) {
        // see default implementation provided on p. 364
        put(key, null);
        // WE SHOULD DO BETTER!!  REPLACE ME LATER!!
    }

    /**
     * Is there a value paired with key?
     * @param key Key to search
     * @return true if the key-value pair exists, false otherwise
     */
    public boolean contains(K key) {
        // see default implementation provided on p. 364
        return get(key) != null;
        /*
        if (get(key) != null) {
            return true;
        }
        return false;
         */
    }

    /**
     * Is the table empty?
     * @return true if table is empty, false otherwise
     */
    public boolean isEmpty() {
        // see default implementation provided on p. 364
        return first == null;
    }

    /**
     * Number of key-value pairs in the table
     * @return number of key-value pairs in the table
     */
    public int size() {
        return size;
    }

    /**
     * All the keys in the table
     * @return a collection of all the keys in the table
     */
    public Iterable<K> keys() {
        // Suggested solution:
        // 1. Create a new queue (which implements Iterable)
        // 2. Loop through the linked list, starting at front, and enqueue all the keys
        // 3. Return the reference to the queue

        Queue<K> queue = new Queue<>();

        Node current = first;

        while (current != null) {
            queue.enqueue(current.key);
            current = current.next;
        }

        return queue;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "{", "}");

        for (K key : keys()) {
            // key=value
            stringJoiner.add(key + "=" + get(key));
        }

        return stringJoiner.toString();
    }
}
