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


    public boolean remove(Comparable toRemove) {
        if (!contains(toRemove)) {
            return false;
        }
        BinaryTreeNode<T> parent = parentFinder(root, (T) toRemove);
        BinaryTreeNode<T> child = getNode((T) toRemove);
        size--;
        return removeHelper(parent, child);
    }

    public BinaryTreeNode<T> parentFinder(BinaryTreeNode<T> current, T child) {
        return null;
    }

    public BinaryTreeNode<T> getNode(T data) {
        // return the node that contains the data
        // it's important to get the node as you need to know if it has any children
        // when you remove it
        return null;
    }

    private boolean removeHelper(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        if (toRemove.hasLeftChild() && toRemove.hasRightChild()) {
            System.out.println("Two Children");
            return removeNodeWithTwoChildren(parent, toRemove);
        } else if (!toRemove.hasLeftChild() && !toRemove.hasRightChild()) {
            System.out.println("No Children");
            return removeNodeWithNoChildren(parent, toRemove);
        } else if ((toRemove.hasLeftChild() && !toRemove.hasRightChild()) || (!toRemove.hasLeftChild() && toRemove.hasRightChild())) {
            System.out.println("One child");
            return removeNodeWithOneChild(parent, toRemove);
        }
        System.out.println("Something went wrong");

        return false;
    }

    private boolean removeNodeWithTwoChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        // To remove a node with two children, we first find the value we are going to
        // replace it with.

        // the replacement is found by first getting the right child of the toRemove node
        // and then traversing as far left on that right child
        // to find the value in the tree that comes after this node.

        // Set the data in the node we are "removing" to be the value of the
        // replacement node.

        // We then have to remove the node which we used as a replacement.
        // Note that it will either be a leaf node with no children or only have a right child (you should utilize the below helper methods to remove it)
        return false;
    }

    private boolean removeNodeWithNoChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        // Figure out if it's the left or right child of the parent and then set it to null
        // if the parent is null, then you must be removing the root so you need to update
        // the root to null
        return false;
    }

    private boolean removeNodeWithOneChild(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        // Determine which node parent should link to instead of the
        // node we are removing.
        // If toRemove has a left child we will promote its left child
        // otherwise we will promote its right child

        // If the node we are removing is the root node
        // its child becomes the new root node.

        // Set the left or right child to be the thing we are promoting
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
        if (isEmpty()) {
            throw new IllegalStateException("Cannot getMin of an empty search tree");
        }
        BinaryTreeNode curnode = root;
        while (curnode.hasLeftChild()) {
            curnode = curnode.getLeftChild();
        }
        return (Comparable) curnode.getData();
    }

    @Override
    public Comparable getMaximum() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot getMax of an empty search tree");
        }
        BinaryTreeNode curnode = root;
        while (curnode.hasRightChild()) {
            curnode = curnode.getRightChild();
        }
        return (Comparable) curnode.getData();
    }

    @Override
    public BinaryTreeNode toBinaryTreeNode() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return util.getInOrderIterator(root);
    }
}
