package ru.progwards.java2.lessons.basetypes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoubleHashTable<K extends HashValue, V> implements Iterable<V> {
    private K[] keyTable;
    private V[] valueTable;
    private boolean[] removed;
    private int capacity;
    private int realSize;

    public DoubleHashTable() {
        int initCapacity = 101;
        keyTable = (K[])new HashValue[initCapacity];
        valueTable = (V[])new Object[initCapacity];
        removed = new boolean[initCapacity];
        capacity = initCapacity;
        realSize = 0;
    }

    boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    void resizeTable() {
        K[] tempKeyTable = (K[])new HashValue[capacity];
        V[] tempValueTable = (V[])new Object[capacity];
        System.arraycopy(keyTable, 0, tempKeyTable, 0, capacity);
        System.arraycopy(valueTable, 0, tempValueTable, 0, capacity);
        capacity = 2*capacity;
        while (!isPrime(capacity)) {
            capacity++;
        }
        keyTable = (K[])new HashValue[capacity];
        valueTable = (V[])new Object[capacity];
        removed = new boolean[capacity];
        realSize = 0;
        for (int i = 0; i < tempValueTable.length; i++) {
            if (tempValueTable[i] != null) {
                K key = tempKeyTable[i];
                V value = tempValueTable[i];
                add(key, value);
            }
        }
    }

    public int getHashDiv(int k) {
        return k % capacity;
    }

    public int getHashMul(int k) {
        final double A = 0.6180339887;
        final double N = capacity;
        double d = A*k;
        return (int)(N*(d-Math.floor(d)));
    }

    int step(int index, int step) {
        index += step;
        if (index < 0) {
            index += capacity-1;
        }
        if (index > capacity-1) {
            index -= capacity-1;
        }
        return index;
    }

    public void add(K key, V value) {
        int k = key.getHash();
        int index = getHashMul(k);
        int step = getHashDiv(k);
        int collision = 0;
        while (collision < capacity/10) {
            if (valueTable[index] == null && !removed[index]) {
                keyTable[index] = key;
                valueTable[index] = value;
                realSize++;
                return;
            }
            index = step(index, step);
            collision++;
        }
        resizeTable();
        add(key, value);
    }

    public V get(K key) {
        int k = key.getHash();
        int index = getHashMul(k);
        int step = getHashDiv(k);
        int collision = 0;
        while (collision < capacity/10) {
            if (key.equals(keyTable[index])) {
                return valueTable[index];
            }
            index = step(index, step);
            collision++;
        }
        throw new NoSuchElementException("В таблице нет данных по ключу " + key.toString());
    }

    public void remove(K key) {
        int k = key.getHash();
        int index = getHashMul(k);
        int step = getHashDiv(k);
        int collision = 0;
        while (collision < capacity/10) {
            if (key.equals(keyTable[index])) {
                keyTable[index] = null;
                valueTable[index] = null;
                removed[index] = true;
                realSize--;
                return;
            }
            index = step(index, step);
            collision++;
        }
        throw new NoSuchElementException("Объект " + key.toString() + " для удаления не найден.");
    }

    public void change(K key1, K key2) {
        V value1 = get(key1);
        remove(key1);
        add(key2, value1);
    }

    public int size() {
        return realSize;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i=0; i < capacity; i++) {
            if (valueTable[i] != null) {
                str.append("<").append(keyTable[i]).append(", ").append(valueTable[i]).append(">").append("\n");
            }
        }
        return str.toString();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<V> iterator() {
        return new DoubleHashTableIterator<>(valueTable);
    }

    public static void main(String[] args) {
        DoubleHashTable<IntKey, Integer> t = new DoubleHashTable<>();
        for (int i=0; i<212; i++) {
            t.add(new IntKey(2*i),i);
        }
        t.change(new IntKey(0), new IntKey(11));

        System.out.println(t.toString());
        for (Integer integer : t) {
            System.out.println(integer);
        }

        DoubleHashTable<StringKey, Integer> t2 = new DoubleHashTable<>();
        for (int i=0; i<105; i++) {
            t2.add(new StringKey(Integer.toString(2*i)),i);
        }
    }
}

class IntKey implements HashValue {
    Integer number;

    IntKey(Integer number) {
        this.number = number;
    }

    @Override
    public int getHash() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntKey intKey = (IntKey) o;
        return Objects.equals(number, intKey.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

class StringKey implements HashValue {
    static final long UINT_MAX = 4_294_967_295L;
    String line;

    StringKey(String line) {
        this.line = line;
    }

    static long unsignedInt(long num) {
        return num % UINT_MAX;
    }

    public static long RSHash(String str) {
        long a = 63689;
        long b = 378551;
        long hash = 0;
        for(int i = 0; i < str.length(); i++) {
            hash = unsignedInt(hash*a + str.charAt(i));
            a = unsignedInt(a*b);
        }
        return hash;
    }

    @Override
    public int getHash() {
        return (int)RSHash(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringKey stringKey = (StringKey) o;
        return Objects.equals(line, stringKey.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line);
    }

    @Override
    public String toString() {
        return line;
    }
}

class DoubleHashTableIterator<T> implements Iterator<T> {
    private final T[] table;
    private int pos;

    DoubleHashTableIterator(T[] table) {
        this.table = table;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        while (pos < table.length) {
            if (table[pos] == null) {
                pos = pos+1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        pos = pos+1;
        return table[pos-1];
    }
}

