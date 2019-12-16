package structures;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class InOrderIterator<T> implements Iterator<T> {
    private final Deque<BinaryTreeNode<T>> stack;
    private BinaryTreeNode<T> curnode;

    public InOrderIterator(BinaryTreeNode<T> node) {
        stack = new LinkedList<>();
        curnode = node;
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || curnode != null;
    }

    @Override
    public T next() {
        while (curnode != null && curnode.hasLeftChild()) {
            stack.push(curnode);
            curnode = curnode.getLeftChild();
        }
        if (curnode == null) {
            curnode = stack.pop();
        }
        T result = curnode.getData();

        if (curnode.hasRightChild()) {
            curnode = curnode.getRightChild();
        } else {
            curnode = null;
        }
        return result;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
