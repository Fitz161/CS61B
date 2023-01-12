import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Iterator<Item> i = items.iterator();
        Queue<Queue<Item>> queues = new Queue<>();
        while (i.hasNext()){
            Queue<Item> queue = new Queue<Item>();
            queue.enqueue(i.next());
            queues.enqueue(queue);
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> queue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()){
            queue.enqueue(getMin(q1, q2));
        }
        return queue;
    }


    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() == 1){
            return items;
        }
        Queue<Item> queue = new Queue<>();
        for (int i = 0; i < items.size() / 2; i++) {
            queue.enqueue(items.dequeue());
        }
        return mergeSortedQueues(mergeSort(queue), mergeSort(items));
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
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Fitz");

        printQueue(students);

        Queue<String> sorted_students = mergeSort(students);

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

        Queue<Integer> sorted_table = mergeSort(table);

        printQueue(sorted_table);
    }
}
