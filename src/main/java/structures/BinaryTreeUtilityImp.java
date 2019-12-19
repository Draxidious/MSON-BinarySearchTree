package structures;

import java.util.Iterator;

public class BinaryTreeUtilityImp<T> implements BinaryTreeUtility {

    @Override
    public <T> Iterator<T> getPreOrderIterator(BinaryTreeNode<T> root) {
        if (root == null) {
            throw new NullPointerException("Gave null root node to preorderIterator");
        }
        return new PreOrderIterator(root);
    }

    @Override
    public <T> Iterator<T> getInOrderIterator(BinaryTreeNode<T> root) {
        if (root == null) {
            throw new NullPointerException("Gave null root node to preorderIterator");
        }


        return new InOrderIterator(root);
    }

    @Override
    public <T> Iterator<T> getPostOrderIterator(BinaryTreeNode<T> root) {
        if (root == null) {
            throw new NullPointerException("Gave null root node to postorderIterator");
        }

        return new PostOrderIterator(root);
    }

    @Override
    public <T> int getDepth(BinaryTreeNode<T> current) {
        if (current == null) {
            return 0;
        }
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

            if (Math.abs(getDepth(current.getLeftChild()) - getDepth(current.getRightChild())) <= tolerance
                    && isBalanced(current.getLeftChild(), tolerance)
                    && isBalanced(current.getRightChild(), tolerance)) {
                return true;
            }

        }
        if (current.hasRightChild() || current.hasLeftChild()) {
            if (getDepth(current) <= tolerance) {
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
        if (root == null) {
            throw new NullPointerException("null root to check if BST");
        }
        return isBSTHelper(root, null, null);
    }

    private <T extends Comparable<? super T>> boolean isBSTHelper(BinaryTreeNode<T> root, T min, T max) {

        if (min != null && root.getData().compareTo(min) < 0) {
            return false;
        }
        if (max != null && root.getData().compareTo(max) >= 0) {
            return false;
        }
        boolean leftIsBST = !root.hasLeftChild() || isBSTHelper(root.getLeftChild(), min, root.getData());
        boolean rightIsBST = !root.hasRightChild() || isBSTHelper(root.getRightChild(), root.getData(), max);
        return leftIsBST && rightIsBST;

    }


}
