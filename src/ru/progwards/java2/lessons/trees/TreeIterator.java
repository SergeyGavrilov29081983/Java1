package ru.progwards.java2.lessons.trees;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<K, V> implements Iterator<BinaryTree.TreeLeaf> {
    private final Stack<BinaryTree.TreeLeaf> stack = new Stack<>();

    private void formStack(BinaryTree.TreeLeaf root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public TreeIterator(BinaryTree.TreeLeaf root) {
        formStack(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public BinaryTree.TreeLeaf next() {
        BinaryTree.TreeLeaf popped = stack.pop();
        formStack(popped.right);
        return popped;
    }

   /* Для обычного BinaryTree из примера в лекциях сделать итератор,
    который позволяет в обычном for получить прямой обход дерева.
    В самом дереве дополнительную информацию для этого хранить нельзя,
    все хранить только в итераторе.
    В BinaryTree добавить метод public TreeIterator<K,V> getIterator()*/
}
