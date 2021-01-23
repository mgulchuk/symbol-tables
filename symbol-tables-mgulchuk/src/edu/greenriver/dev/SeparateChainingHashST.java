package edu.greenriver.dev;

public class SeparateChainingHashST<Key, Value> {
    //fields
    private int m;  // # of buckets, hash table size
    private SequentialSearchST<Key, Value>[] st;
                                // st is the array of linked lists
                                // st == symbol table

    // constructor
    public SeparateChainingHashST(){
        m = 997;    // hard coded for now

        // set up the array of linked lists
        st = new SequentialSearchST[m];
        // for each array position, make a new SequentialSearchST
        for(int i = 0; i < m; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    // helper method
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key){
        int index = hash(key);
        return st[index].get(key);
    }

  public void put(Key key, Value val){
        int index = hash(key);          // figure out bucket number
      st[index].put(key, val);          // gp into into bucket, put into list

  }

    public Iterable<Key> keys() {
        // get all keys together
        Queue<Key> theBigQueue = new Queue<>();
        for (int i = 0; i < m; i++){
            Iterable<Key> littleQueue = st[i].keys();

            for(Key k : littleQueue){
                theBigQueue.enqueue(k);
            }
        }
        return theBigQueue;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

}

