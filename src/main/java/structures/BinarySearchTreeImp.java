package structures;

import java.util.Iterator;

public class BinarySearchTreeImp<T extends Comparable<? super T>> implements BinarySearchTree<T> {
    private BinaryTreeNodeImp root;
    private BinaryTreeUtilityImp<T> util = new BinaryTreeUtilityImp<>();
    private int size = 0;
    /**
     * Returns the node associated with the most recent contains call, if contains was false, it will be null.
     * if the last contains call was true, this variable will have the node found;
     */
    private BinaryTreeNodeImp containsnode;

    @Override
    public BinarySearchTree add(T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("tried to add null element to tree");
        }
        size++;
        if (root == null) {
            root = new BinaryTreeNodeImp(null, toAdd, null);
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
        if (isEmpty()) {
            return false;
        }
        return containsHelper(toFind, root);
    }

    public boolean containsHelper(Comparable toFind, BinaryTreeNodeImp curnode) {
        if (toFind.compareTo(curnode.getData()) == 0) {
            containsnode = curnode;
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


    public boolean remove(T toRemove) {
        if (!contains(toRemove)) {
            return false;
        }
        BinaryTreeNode<T> parent = parentFinder(root, toRemove);
        BinaryTreeNode<T> child = getNode(toRemove);
        size--;
        return removeHelper(parent, child);
    }

    public BinaryTreeNode<T> parentFinder(BinaryTreeNodeImp<T> current, T child) {
        if (!current.hasLeftChild() && !current.hasRightChild() || current == null) {
            return null;
        } else {
            if ((current.hasLeftChild() && current.getLeftChild().getData().compareTo(child) == 0) ||
                    (current.hasRightChild()) && current.getRightChild().getData().compareTo(child) == 0) {
                return current;
            } else {
                if (current.getData().compareTo(child) > 0 && current.hasLeftChild()) {
                    return parentFinder(current.getLeftChild(), child);
                } else if (current.getData().compareTo(child) < 0 && current.hasRightChild()) {
                    return parentFinder(current.getRightChild(), child);
                }
            }
        }

        return null;
    }


    public BinaryTreeNode<T> getNode(T data) {
        if (!contains(data)) {
            return null;
        }
        boolean found = false;
        BinaryTreeNode<T> cur = root;
        while (!found) {
            if (cur.getData().compareTo(data) == 0) {
                found = true;
            } else {
                if (cur.getData().compareTo(data) > 0 && cur.hasLeftChild()) {
                    cur = cur.getLeftChild();
                } else if (cur.getData().compareTo(data) < 0 && cur.hasRightChild()) {
                    cur = cur.getRightChild();
                }
            }
        }
        return cur;
    }

    private boolean removeHelper(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        if (toRemove.hasLeftChild() && toRemove.hasRightChild()) {
            return removeNodeWithTwoChildren(parent, toRemove);
        } else if (!toRemove.hasLeftChild() && !toRemove.hasRightChild()) {

            return removeNodeWithNoChildren(parent, toRemove);
        } else if ((toRemove.hasLeftChild() && !toRemove.hasRightChild()) || (!toRemove.hasLeftChild() && toRemove.hasRightChild())) {

            return removeNodeWithOneChild(parent, toRemove);
        }
        System.out.println("Something went wrong");

        return false;
    }

    private boolean removeNodeWithTwoChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        // To remove a node with two children, we first find the value we are going to
        // replace it with. the replacement is found by first getting the right child of the toRemove node
        // and then traversing as far left on that right child to find the value in the tree that comes after this node.
        // Set the data in the node we are "removing" to be the value of the
        // replacement node.
        // We then have to remove the node which we used as a replacement.
        // Note that it will either be a leaf node with no children or only have a right child (you should utilize the below helper methods to remove it)

        BinaryTreeNode<T> par = toRemove;
        BinaryTreeNode<T> replace = toRemove.getRightChild();

        boolean found = false;
        while (!found) {
            if (replace.hasLeftChild()) {
                par = replace;
                replace = replace.getLeftChild();
            } else found = true;
        }
        toRemove.setData(replace.getData());
        removeHelper(par, replace);
        return true;



    }

    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> toRemove) {
        BinaryTreeNode<T> cur = toRemove;
        if (toRemove.hasRightChild()) {
            cur = toRemove.getRightChild(); // get the right child of toRemove
        }
        while (cur.hasLeftChild()) { // traverse left on that right child until we find the value.
            cur = cur.getLeftChild();
        }
        return cur;
    }

    private boolean removeNodeWithNoChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> toRemove) {
        // Figure out if it's the left or right child of the parent and then set it to null
        // if the parent is null, then you must be removing the root so you need to update
        // the root to null
        if (parent == null) {
            root = null;
            return true;
        }
        if (parent.hasRightChild() && parent.getRightChild() == toRemove) {
            parent.setRightChild(null);
            return true;
        }
        if (parent.hasLeftChild() && parent.getLeftChild() == toRemove) {
            parent.setLeftChild(null);
            return true;
        }
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
        if (parent == null) {
            if (toRemove.hasLeftChild()) {
                root = root.getLeftChild();
            }
            if (toRemove.hasRightChild()) {
                root = root.getRightChild();
            }
            return true;
        }
        if (toRemove.hasRightChild()) {
            if (parent.hasRightChild() && parent.getRightChild() == toRemove) {
                parent.setRightChild(toRemove.getRightChild());
            }
            if (parent.hasLeftChild() && parent.getLeftChild() == toRemove) {
                parent.setLeftChild(toRemove.getRightChild());
            }
            return true;
        }
        if (toRemove.hasLeftChild()) {
            if (parent.hasRightChild() && parent.getRightChild() == toRemove) {
                parent.setRightChild(toRemove.getLeftChild());
            }
            if (parent.hasLeftChild() && parent.getLeftChild() == toRemove) {
                parent.setLeftChild(toRemove.getLeftChild());
            }
            return true;
        }

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
    public T getMinimum() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot getMin of an empty search tree");
        }
        BinaryTreeNode<T> curnode = root;
        while (curnode.hasLeftChild()) {
            curnode = curnode.getLeftChild();
        }
        return (T) curnode.getData();
    }

    @Override
    public T getMaximum() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot getMax of an empty search tree");
        }
        BinaryTreeNode<T> curnode = root;
        while (curnode.hasRightChild()) {
            curnode = curnode.getRightChild();
        }
        return curnode.getData();
    }

    @Override
    public BinaryTreeNodeImp toBinaryTreeNode() {
        return root;
    }

    @Override
    public Iterator iterator() {
        return util.getInOrderIterator(root);
    }
}
