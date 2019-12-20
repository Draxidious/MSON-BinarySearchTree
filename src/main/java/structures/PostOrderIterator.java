package structures;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class PostOrderIterator<T> implements Iterator<T> {
    private final Deque<BinaryTreeNode<T>> stack;
    private BinaryTreeNode<T> curnode;
    private BinaryTreeNode<T> peekNode;
    private BinaryTreeNode<T> lastNode;

    public PostOrderIterator(BinaryTreeNode<T> node) {
        stack = new LinkedList<>();
        curnode = node;
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || curnode != null;
    }

    @Override
    public T next() {
        while (curnode != null) {
            stack.push(curnode);
            if (curnode.hasLeftChild()) {
                curnode = curnode.getLeftChild();
            } else {
                curnode = null;
            }
        }
        peekNode = stack.peek();
        if (peekNode.hasRightChild() && peekNode.getRightChild() != lastNode) {
            curnode = peekNode.getRightChild();
            return next();
        } else {
            lastNode = stack.pop();
            return peekNode.getData();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

