import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item item : unsorted) {
            int result = item.compareTo(pivot);
            if (result < 0){
                less.enqueue(item);
            } else if (result > 0){
                greater.enqueue(item);
            }
            else {
                equal.enqueue(item);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1){
            return items;
        }
        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();

        partition(items, pivot, less, equal, greater);

        Queue<Item> result = catenate(catenate(quickSort(less), equal), quickSort(greater));

        return result;
    }

    public static <Item extends Comparable> void printQueue(Queue<Item> queue) {
        Iterator<Item> i = queue.iterator();
        while (i.hasNext()){
            System.out.print(i.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // test 1
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Fitz");

        printQueue(students);

        Queue<String> sorted_students = quickSort(students);

        printQueue(sorted_students);

        // test 2
        Queue<Integer> table = new Queue<>();
        table.enqueue(3);
        table.enqueue(12);
        table.enqueue(5);
        table.enqueue(32);
        table.enqueue(112);
        table.enqueue(53);
        table.enqueue(6);
        table.enqueue(2);
        table.enqueue(99);

        printQueue(table);

        Queue<Integer> sorted_table = quickSort(table);

        printQueue(sorted_table);
    }
}
