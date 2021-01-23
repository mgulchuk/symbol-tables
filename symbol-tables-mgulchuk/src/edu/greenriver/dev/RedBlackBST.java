package edu.greenriver.dev;

public class RedBlackBST <Key extends Comparable<Key>, Value> {
    // constants
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // helper class
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;
        private boolean color;
            // true/red if link from parent is red

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.count = n;
            this.color = color;
        }
    }

    //field
    private Node root;

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // constructor
    public RedBlackBST() {
        root = null;
    }

    //helper methods
    private boolean isRed(Node x){
        if(x == null){
            return false;
        }
        return x.color == RED;
    }

    private int size(Node current){
        if(current == null){
            return 0;
        }
        return current.count;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    private Node rotateLeft(Node h){
        //assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
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
            return new Node(key, val, 1, RED);
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
        // if we get here, insertion is complete and we are
        // working our way back up the recursion

        if (isRed(current.right) && !isRed(current.left)){
            current = rotateLeft(current);
        }
        if (isRed(current.left) && isRed(current.left.left)){
            current = rotateRight(current);
        }
        if (isRed(current.left) && isRed(current.right)){
            flipColors(current);
        }
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }

    private void inorder(Node current, Queue<Key> q){
        if(current == null)
            return;
        // Recursive case; go left, then myself, go right
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }
}
