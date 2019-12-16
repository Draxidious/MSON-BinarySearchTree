package structures;

import java.util.Iterator;

public class BinarySearchTreeImp implements BinarySearchTree{
    @Override
    public BinarySearchTree add(Comparable toAdd) {
        //if greater that, move to right, if less than, move left, if
        //right or left node does not exist, make this node that node
        return null;
    }

    @Override
    public boolean contains(Comparable toFind) {
        return false;
    }

    @Override
    public boolean remove(Comparable toRemove) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Comparable getMinimum() {
        //farthest left
        return null;
    }

    @Override
    public Comparable getMaximum() {
        //farthest right
        return null;
    }

    @Override
    public BinaryTreeNode toBinaryTreeNode() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
