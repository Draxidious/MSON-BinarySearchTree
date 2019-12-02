package structures;

import java.util.Iterator;

public class BinaryTreeUtilityImp implements BinaryTreeUtility {
    @Override
    public <T> Iterator<T> getPreOrderIterator(BinaryTreeNode<T> root) {
        if(root==null) throw new NullPointerException("Gave null root node to preorderIterator");
        return new PreOrderIterator(root);
    }

    @Override
    public <T> Iterator<T> getInOrderIterator(BinaryTreeNode<T> root) {
        return null;
    }

    @Override
    public <T> Iterator<T> getPostOrderIterator(BinaryTreeNode<T> root) {
        return null;
    }

    @Override
    public <T> int getDepth(BinaryTreeNode<T> current) {
        if (current.hasLeftChild() && current.hasRightChild()) {
            return 1 + Math.max(getDepth(current.getLeftChild()), getDepth(current.getRightChild()));
        }
        if (current.hasLeftChild()) {
            return 1 + getDepth(current.getLeftChild());
        }
        if (current.hasRightChild()) {
            return 1 + getDepth(current.getRightChild());
        }
        return 0;
    }

    @Override
    public <T> boolean isBalanced(BinaryTreeNode<T> current, int tolerance) {
        if (current == null) {
            throw new NullPointerException("null node in tree visited");
        }
        if (tolerance < 0) {
            throw new IllegalArgumentException("tolerance less than 0");
        }
        if (current.hasLeftChild() && current.hasRightChild()) {
            if (Math.abs(getDepth(current.getLeftChild()) - getDepth(current.getRightChild())) <= tolerance) {
                return true;
            }
            return false;
        }
        if (!current.hasLeftChild() && !current.hasRightChild()) {
            return true;
        }

        return false;
    }

    @Override
    public <T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root) {
        if (root.hasLeftChild() && root.getLeftChild().getData().compareTo(root.getData()) > 0) {
            return false;
        }
        if (root.hasRightChild() && root.getRightChild().getData().compareTo(root.getData()) < 0) {
            return false;
        }
        if (root.hasLeftChild()) {
            isBST(root.getLeftChild());
        }
        if (root.hasRightChild()) {
            isBST(root.getRightChild());
        }
        return true;
    }
}
