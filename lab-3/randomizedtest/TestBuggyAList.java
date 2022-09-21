package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        BuggyAList<Integer> list2 = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            list1.addLast(4 + i);
            list2.addLast(4 + i);
        }
        for (int i = 0; i < 3; i++) {
            int left = list1.removeLast();
            int right = list2.removeLast();
            assertEquals(left, right);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(), L2.size());
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // addLast
                assertEquals(L.size(), L2.size());
                if (L.size() <= 0) {
                    continue;
                }
                int num1 = L.removeLast();
                int num2 = L2.removeLast();
                assertEquals(num1, num2);
                System.out.println("removeLast(" + num1 + ")");
            }
        }
    }
}
