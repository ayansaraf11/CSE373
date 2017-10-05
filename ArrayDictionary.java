package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    private Pair<K, V>[] pairs;
    private int currentArraySize = 10;
    private int size;
    
    public ArrayDictionary() {
        this.pairs = makeArrayOfPairs(currentArraySize);
        this.size = 0;
    }
    
    /**
    * This method will return a new, empty array of the given size
    * that can contain Pair<K, V> objects.
    *
    * Note that each element in the array will initially be null.
    */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }
    
    /**
    * Returns the value corresponding to the given key.
    *
    * @throws NoSuchKeyException if the dictionary does not contain the given key.
    */
    @Override
    public V get(K key) {
        if (this.isEmpty()) {
            throw new NoSuchKeyException();
        } else {
            for (int i = 0; i < size; i++) {
                K keyValue = pairs[i].key;
                if (keyValue == key || keyValue.equals(key)) {
                    return pairs[i].value;
                }
            }
            throw new NoSuchKeyException();
        }
    }
    
    /**
    * Adds the key-value pair to the dictionary. If the key already exists in the dictionary,
    * replace its value with the given one.
    */
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            for (int i = 0; i < size; i++) {
                K keyValue = pairs[i].key;
                if (keyValue == key || keyValue.equals(key)) {
                    pairs[i].value = value;
                }
            }
        } else {
            if (size == currentArraySize) {
                Pair<K, V>[] newPairArray = makeArrayOfPairs(currentArraySize * 2);
                for (int i = 0; i < size; i++) {
                    newPairArray[i]= new Pair<K, V>(pairs[i].key, pairs[i].value);
                }
                pairs = newPairArray;
                currentArraySize = currentArraySize * 2;
            }
            pairs[size] = new Pair<K, V>(key, value);
            size++;
        }
    }
    
    /**
    * Remove the key-value pair corresponding to the given key from the dictionary.
    *
    * @throws NoSuchKeyException if the dictionary does not contain the given key.
    */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            throw new NoSuchKeyException();
        }
        V removedValue = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            K keyValue = pairs[i].key;
            if (keyValue == key || keyValue.equals(key)) {
                removedValue = pairs[i].value;
                pairs[i].key = null;
                pairs[i].value = null;
                index = i;
            }
        }
        for (int i = index; i < size; i++) {
            pairs[i] = pairs[i + 1];
        }
        pairs[size] = null;
        size--;
        return removedValue;
    }
    
    /**
    * Returns 'true' if the dictionary contains the given key and 'false' otherwise.
    */
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            K keyValue = pairs[i].key;
            if (keyValue == key || keyValue.equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Returns the number of key-value pairs stored in this dictionary.
    */
    @Override
    public int size() {
        return this.size;
    }
    
    private static class Pair<K, V> {
        public K key;
        public V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
