package structures;

public class BinaryTreeNodeImp<T> implements BinaryTreeNode<T> {
    /**
     * Element in node.
     */
    private T element;
    /**
     * Left child of node.
     */
    private BinaryTreeNodeImp<T> left;
    /**
     * Right Child of node.
     */
    private BinaryTreeNodeImp<T> right;

    public BinaryTreeNodeImp(BinaryTreeNode<T> le, T elem, BinaryTreeNode<T> ri) {
        element = elem;
        left = (BinaryTreeNodeImp<T>) le;
        right = (BinaryTreeNodeImp<T>) ri;
    }

    @Override
    public T getData() {
        return element;
    }

    @Override
    public void setData(T data) {
        element = data;
    }

    @Override
    public boolean hasLeftChild() {
        return left != null;
    }

    @Override
    public boolean hasRightChild() {
        return right != null;
    }

    @Override
    public BinaryTreeNodeImp<T> getLeftChild() {
        return left;
    }

    @Override
    public BinaryTreeNodeImp<T> getRightChild() {
        return right;
    }

    @Override
    public void setLeftChild(BinaryTreeNode<T> leftNew) {
        this.left = (BinaryTreeNodeImp<T>) leftNew;
    }

    @Override
    public void setRightChild(BinaryTreeNode<T> rightNew) {
        this.right = (BinaryTreeNodeImp<T>) rightNew;
    }
}
