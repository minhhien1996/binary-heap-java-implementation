import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by minhhien1996 on 7/5/17.
 */
public class Heap {
    static final int CAPACITY = 100;
    private int[] data = new int[CAPACITY];
    private int count = 0;;

    public void add(int n) {
        if (count < CAPACITY)
        data[count] = n;
        reheapUp(count);
        count++;
    }

    public void remove() {
        int n = data[0];
        data[0] = data[count - 1];
        reheapDown(0);
    }

    public void reheapUp(int i) {
            if (i == 0) return;
         int parent = parent(i);
         int max = indexOfMax(i, parent);
         if (i == max) {
             swap(i, parent);
             reheapUp(parent);
         }
    }

    public void reheapDown(int i) {
        int leftChild = leftChild(i);
        int rightChild = rightChild(i);
        int max = indexOfMaxThree(i, leftChild, rightChild);
        if (max != i) {
            swap(i, max);
            reheapDown(max);
        }
    }

    private void swap(int a, int b) {
        int t = data[a];
        data[a] = data[b];
        data[b] = t;
    }

    private int indexOfMax(int a, int b) {
        if (data[a] > data[b]) return a;
        return b;
    }

    private int indexOfMaxThree(int a, int b, int c) {
         return indexOfMax(indexOfMax(a, b), c);
    }

    private int parent(int i) {
        return ((int) Math.floor((i - 1) / 2));
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public String getString() {
        if (data.length == 0)
            return "Heap has no nodes.";

        int root = data[0];
        if (count == 0)
            return "Tree has no nodes.";
        return getString(0, "", true);
    }

    private String getString(int index, String prefix, boolean isTail) {
        StringBuilder builder = new StringBuilder();

        int value = this.data[index];
        builder.append(prefix + (isTail ? "└── " : "├── ") + value + "\n");
        List<Integer> children = null;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        if (leftIndex != Integer.MIN_VALUE || rightIndex != Integer.MIN_VALUE) {
            children = new ArrayList<Integer>(2);
            if (leftIndex != Integer.MIN_VALUE && leftIndex < count) {
                children.add(leftIndex);
            }
            if (rightIndex != Integer.MIN_VALUE && rightIndex < count) {
                children.add(rightIndex);
            }
        }
        if (children != null) {
            for (int i = 0; i < children.size() - 1; i++) {
                builder.append(getString(children.get(i), prefix + (isTail ? "    " : "│   "), false));
            }
            if (children.size() >= 1) {
                builder.append(getString(children.get(children.size() - 1), prefix
                        + (isTail ? "    " : "│   "), true));
            }
        }

        return builder.toString();
    }
}

