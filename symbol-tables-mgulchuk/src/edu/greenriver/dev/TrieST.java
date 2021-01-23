package edu.greenriver.dev;

public class TrieST<Value>{
    private final static int R = 256;
    private Node root = new Node();

    private static class Node{
        private Object val;
        private Node[] next = new Node[R];
    }

    public boolean contains(String key){
        return get(key) != null;
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null)
            return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d){
        if(x == null)
            return null;
        if(d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d){
        if(x == null)
            x = new Node();
        if(d == key.length()){
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keys(){
        return keysWithPreFix("");
    }

    public Iterable<String> keysWithPreFix(String prefix){
        Queue<String> queue = new Queue<String>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> queue){
        if(x == null)
            return;
        if(x.val != null)
            queue.enqueue(prefix);
        for(char c = 0; c < R; c++){
            collect(x.next[c], prefix + c, queue);
        }
    }
}
