package ru.progwards.java2.lessons.basetypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BiDirList<T> {

    BiDirList.Item<T> first;
    BiDirList.Item<T> last;

    public BiDirList(T[] items) {
        for (Object item : items) {
            BiDirList.Item<T> l = this.last;
            BiDirList.Item<T> newNode = new BiDirList.Item(l, item, (BiDirList.Item) null);
            this.last = newNode;
            if (l == null) {
                this.first = newNode;
            } else {
                l.next = newNode;
            }
        }
    }

    public static void main(String[] args) {
        String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};
        BiDirList<String> list = BiDirList.from(seasons);
        list.addFirst("t3");
        list.addLast("t2");
        list.addLast("test");
        list.addFirst("t1");
        list.remove("Summer");
        System.out.println(list.at(4));
        System.out.println(list.at(10));
        System.out.println(list.size());
    }

    public static <T> BiDirList<T> from(T[] array) {
        return new BiDirList<>(array);
    }

    public static <T> BiDirList<T> of(T... array) {
        return new BiDirList<>(array);
    }

    public void toArray(T[] array) {
        ListIterator<T> iterator = new ListIterator<>(first);
        List<T> list = new ArrayList<>();
        T item;
        while (iterator.hasNext()) {
            item = iterator.next();
            list.add(item);
        }
        array = (T[]) list.toArray();
    }

    public void addLast(T item) {
        BiDirList.Item<T> l = this.last;
        BiDirList.Item<T> newNode = new BiDirList.Item<>(l, item, (BiDirList.Item<T>) null);
        this.last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
    }

    public void addFirst(T item) {
        BiDirList.Item<T> f = this.first;
        BiDirList.Item<T> newNode = new BiDirList.Item<>((BiDirList.Item<T>) null, item, f);
        this.first = newNode;
        if (f == null) {
            this.last = newNode;
        } else {
            f.prev = newNode;
        }
    }

    public void remove(T item) {
        BiDirList.Item<T> x;
        if (item == null) {
            for (x = this.first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                }
            }
        } else {
            for (x = this.first; x != null; x = x.next) {
                if (item.equals(x.item)) {
                    unlink(x);
                }
            }
        }
    }

    private void unlink(Item<T> x) {
        BiDirList.Item<T> next = x.next;
        BiDirList.Item<T> prev = x.prev;
        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
    }

    public T at(int i) {
        List<T> list = addAllToList();
        if (i > list.size()) {
            return null;
        }
        return list.get(i);
    }

    private List<T> addAllToList() {
        ListIterator<T> iterator = new ListIterator<>(first);
        List<T> list = new ArrayList<>();
        T item;
        while (iterator.hasNext()) {
            item = iterator.next();
            list.add(item);
        }
        return list;
    }

    public int size() {
        return addAllToList().size();
    }

    static class Item<T> {
        T item;
        Item<T> next;
        Item<T> prev;

        Item(Item<T> prev, T element, Item<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private static class ListIterator<T> implements Iterator<T> {

        private Item<T> current;

        public ListIterator(Item<T> first) {
            current = first;
        }

        public boolean hasNext() {

            return current != null;
        }

        public T next() {
            if (hasNext()) {
                T item = current.item;
                current = current.next;
                return item;
            }
            return null;
        }

        public void remove() {

        }
    }
}

