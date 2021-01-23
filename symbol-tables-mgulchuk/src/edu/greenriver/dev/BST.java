package edu.greenriver.dev;

public class BST <Key extends Comparable<Key>, Value> {
    // helper class
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    } // end of Node class

    // field
    private Node root;

    // constructor
    public BST() {
        root = null;
    }

    public Value get(Key key) {
        Node current = root;

        while (current != null) {
            int result = key.compareTo(current.key);
            if (result < 0) {
                // if key < current.key
                current = current.left;
            }
            else if (result > 0) {
                // else if key > current.key
                current = current.right;
            }
            else {
                return current.val;
            }
        } // end of while loop

        return null;    // if we got here, it's not found
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);    // start the recursion
    }

    // helper method for recursion
    private Node put(Node current, Key key, Value val) {
        if (current == null) {
            return new Node(key, val);
        }
        int result = key.compareTo(current.key);
        if (result < 0) {
            current.left = put(current.left, key, val);
        }
        else if (result > 0) {
            current.right = put(current.right, key, val);
        }
        else {  // result == 0
            current.val = val;
        }
        return current;
    }
}
