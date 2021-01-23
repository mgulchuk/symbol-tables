package edu.greenriver.dev;

import javax.swing.*;

/**
 * ArrayST
 * Exercise 3.1.2
 * Sequential search (in an unordered array)
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author PUT YOUR NAME HERE
 * @version 1.0
 */
public class ArrayST<K, V> {
    private K[] keys;
    private V[] vals;
    private int size;

    public ArrayST(int capacity){
        keys=(K[]) new Comparable[capacity];
        vals=(V[]) new Object[capacity];
        size = 0;
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

    public int size(){
        return size;
    }

    public V get(K key){
        for(int i = 0; i < size; i++){
            if(keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public void put(K key, V val){
        // Search the list for the key and if the key already exists
        // replace the value if we don't find key in the list, adda new node
        if(size == keys.length)
            resize(2 * keys.length);
        for(int i = 0; i < size; i++){
            if(keys[i].equals(key)){
                vals[i] = val;
                return;
            }
        }
        keys[size] = key;;
        vals[size] = val;
        size++;
    }

    public void delete(K key){
        if(key == null)
            return;
        int index = -1;
        for(int i = 0; i < size; i++){
            if(key.equals(keys[i])){
                index = i;
                break;
            }
        }
        if(index == -1)
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

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(K key){
        return get(key) != null;
    }

    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for(int i = 0; i < size; i++)
            q.enqueue(keys[i]);
        return q;
    }

}
