package structures;

import config.Configuration;

import java.util.LinkedList;
import java.util.Queue;

public class PortableTreeViewer<T> {
    public PortableTreeViewer(BinaryTreeNode<T> root) {
		System.out.println(toDotFormat(root));
    }

    public static <T> String toDotFormat(BinaryTreeNode<T> root) {
        // header
        int count = 0;
        String dot = "digraph G { \n";
        dot += "graph [ordering=\"out\"]; \n";
        // iterative traversal
        Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
        queue.add(root);
        BinaryTreeNode<T> cursor;
        while (!queue.isEmpty()) {
            cursor = queue.remove();
            if (cursor.hasLeftChild()) {
                // add edge from cursor to left child
                dot += cursor.getData().toString() + " -> "
                        + cursor.getLeftChild().getData().toString() + ";\n";
                queue.add(cursor.getLeftChild());
            } else {
                // add dummy node
                dot += "node" + count + " [shape=point];\n";
                dot += cursor.getData().toString() + " -> " + "node" + count
                        + ";\n";
                count++;
            }
            if (cursor.hasRightChild()) {
                // add edge from cursor to right child
                dot += cursor.getData().toString() + " -> "
                        + cursor.getRightChild().getData().toString() + ";\n";
                queue.add(cursor.getRightChild());
            } else {
                // add dummy node
                dot += "node" + count + " [shape=point];\n";
                dot += cursor.getData().toString() + " -> " + "node" + count
                        + ";\n";
                count++;
            }

        }
        dot += "};";
        return dot;
    }

}
