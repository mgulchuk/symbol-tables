package edu.greenriver.dev;

/**
 * BinarySearchST
 * Algorithm 3.2
 * Binary search (in an ordered array)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author PUT YOUR NAME HERE
 * @version 1.0
 */
public class BinarySearchST <K extends Comparable<K>, V> {
    private K[] keys;
    private V[] vals;
    private int size;

    public BinarySearchST(int capacity){
        keys=(K[]) new Comparable[capacity];
        vals=(V[]) new Object[capacity];
        size = 0;
    }

    // return size
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int rank(K key){
        int lo = 0;
        int hi = size - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
                hi = mid - 1;
            else if(cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public int size(K lo, K hi){
        if(hi.compareTo(lo)<0)
            return 0;
        else if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public void delete(K key){
        if(key == null)
            return;
        int index = rank(key);
        if(index == size)
            return;
        for(int i = index; i < size-1; i++){
            keys[i] = keys[i+1];
            vals[i] = vals[i+1];
        }
        size--;

        keys[size] = null;
        vals[size] = null;

        if(size > 0 && size == keys.length/4)
            resize(keys.length/2);
    }

    public V get(K key){
        if(isEmpty())
            return null;
        int n = rank(key);
        if(n < size && keys[n].compareTo(key) == 0)
            return vals[n];
        else
            return null;
    }

    public boolean contains(K key){
        return get(key) != null;
    }

    public K min(){
        return keys[0];
    }

    public K max(){
        return keys[size-1];
    }

    private void resize(int max){
        K[] tempK = (K[]) new Comparable[max];
        V[] tempV = (V[]) new Object[max];

        for(int i = 0; i < size; i++){
            tempK[i] = keys[i];
            tempV[i] = vals[i];
        }

        keys = tempK;
        vals = tempV;
    }

    public void deleteMin(){
        delete(min());
        if(size > 0 && size == keys.length/4)
            resize(keys.length/2);
    }

    public void deleteMax(){
        delete(max());
        if(size > 0 && size == keys.length/4)
            resize(keys.length/2);
    }

    public K select(int k){
        return keys[k];
    }

    public K ceiling(K key){
        int n = rank(key);
        return keys[n];
    }

    public void put(K key, V val){
        if(size == keys.length)
            resize(2 * keys.length);
        int n = rank(key);
        if(n < size && keys[n].compareTo(key) == 0){
            vals[n] = val;
            return;
        }
        for(int i = size; i > n; i++){
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }

        keys[n] = key;
        vals[n] = val;
        size++;
    }

    public Iterable<K> keys(K lo, K hi){
        Queue<K> q = new Queue<>();
        for(int i = rank(lo); i < rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }




}
