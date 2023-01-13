import java.util.HashMap;
import java.util.Map;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
//        Map<Integer, Integer> counts = new HashMap<>();
//        for (int i : arr){
//            int count = counts.getOrDefault(i, 0);
//            counts.put(i, count + 1);
//        }
//
//        Map<Integer, Integer> starts = new HashMap<>();
//        int pos = 0;
//        for (int key : counts.keySet()) { // no order
//            starts.put(key, pos);
//            pos += counts.get(key);
//        }
//        int[] sorted = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            int item = arr[i];
//            int index = starts.get(item);
//            sorted[index] = item;
//            starts.put(item, index + 1);
//        }
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }
        // find min
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = min < i ? min : i;
        }

        int[] positive_counts = new int[max + 1];
        int[] negative_counts = new int[-min + 1];
        for (int i : arr) {
            if (i >= 0) {
                positive_counts[i]++;
            } else {
                negative_counts[-i]++;
            }
        }

        int[] positive_starts = new int[max+ 1];
        int[] negative_starts = new int[-min + 1]; // no zero
        int pos = 0;

        for (int i = negative_starts.length - 1; i > 0; i -= 1) {
            // zero is not in negative part, so i should bigger than zero
            negative_starts[i] = pos;
            pos += negative_counts[i];
        }
        for (int i = 0; i < positive_starts.length; i += 1) {
            positive_starts[i] = pos;
            pos += positive_counts[i];
        }

        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            if (item >= 0) {
                int place = positive_starts[item];
                sorted[place] = item;
                positive_starts[item] += 1;
            } else {
                int place = negative_starts[-item];
                sorted[place] = item;
                negative_starts[-item] += 1;
            }
        }
        return sorted;
    }
}
