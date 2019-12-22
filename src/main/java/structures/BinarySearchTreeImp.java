package structures;

import java.util.Iterator;

public class BinarySearchTreeImp<T> implements BinarySearchTree {
    private BinaryTreeNodeImp root;
    private BinaryTreeUtilityImp<T> util = new BinaryTreeUtilityImp<>();
    private int size = 0;

    @Override
    public BinarySearchTree add(Comparable toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("tried to add null element to tree");
        }
        size++;
        if (root == null) {
            root = new BinaryTreeNodeImp(null, toAdd, null);
            return this;
        }

        if (contains(toAdd)) {
            return this;
        }


        return addHelper(toAdd, root);
    }

    public BinarySearchTree addHelper(Comparable toAdd, BinaryTreeNodeImp curnode) {
        if (toAdd.compareTo(curnode.getData()) < 0) {
            if (!curnode.hasLeftChild()) {
                curnode.setLeftChild(new BinaryTreeNodeImp(null, toAdd, null));
            } else {
                addHelper(toAdd, curnode.getLeftChild());
            }
        } else {
            if (!curnode.hasRightChild()) {
                curnode.setRightChild(new BinaryTreeNodeImp(null, toAdd, null));
            } else {
                addHelper(toAdd, curnode.getRightChild());
            }
        }

        return this;
    }

    @Override
    public boolean contains(Comparable toFind) {
        if (toFind == null) {
            throw new NullPointerException("Tried to find a null element");
        }
        if (root == null) {
            return false;
        }
        return containsHelper(toFind, root);
    }

    public boolean containsHelper(Comparable toFind, BinaryTreeNodeImp curnode) {
        if (toFind.compareTo(curnode.getData()) == 0) {
            return true;
        }
        if (curnode.hasRightChild() && !curnode.hasLeftChild()) {
            if (containsHelper(toFind, curnode.getRightChild())) {
                return true;
            }
        }
        if (curnode.hasLeftChild() && !curnode.hasRightChild()) {
            if (containsHelper(toFind, curnode.getLeftChild())) {
                return true;
            }
        }
        if (curnode.hasLeftChild() && curnode.hasRightChild()) {
            if (containsHelper(toFind, curnode.getLeftChild()) || containsHelper(toFind, curnode.getRightChild())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(Comparable toRemove) {
        size--;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Comparable getMinimum() {
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


    public BinaryTreeNode getRoot() {
        return root;
    }
}
