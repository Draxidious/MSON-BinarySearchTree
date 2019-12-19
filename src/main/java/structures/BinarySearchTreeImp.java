package structures;

import java.util.Iterator;

public class BinarySearchTreeImp implements BinarySearchTree {
    private BinaryTreeNodeImp root;

    @Override
    public BinarySearchTree add(Comparable toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("tried to add null element to tree");
        }
        if (root == null) {
            root = (BinaryTreeNodeImp) toAdd;
        } else {
            if (!root.hasLeftChild() && !root.hasRightChild()) {
                if (toAdd.compareTo(root.getData()) < 0) {
                    root.setLeftChild((BinaryTreeNode) toAdd);
                } else {
                    root.setRightChild((BinaryTreeNode) toAdd);
                }
            }
            if (!root.hasLeftChild() && root.hasRightChild()) {
                if (toAdd.compareTo(root.getData()) < 0) {
                    root.setLeftChild((BinaryTreeNode) toAdd);
                } else {
                    add((Comparable) root.getRightChild());
                }
            }
            if (root.hasLeftChild() && !root.hasRightChild()) {
                if (toAdd.compareTo(root.getData()) >= 0) {
                    root.setRightChild((BinaryTreeNode) toAdd);
                } else {
                    add((Comparable) root.getLeftChild());
                }
            }
        }


        return this;
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
