/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLength = Integer.MIN_VALUE;
        for (String str : asciis){
            if (str.length() > maxLength){
                maxLength = str.length();
            }
        }

        String[] newAsciis = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            newAsciis[i] = asciis[i];
        }

        for (int i = maxLength - 1; i >= 0; i--) {
            sortHelperLSD(newAsciis, i);
        }
        return newAsciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] newAsciis = countingSort(asciis, index);
        System.out.print("index: " + index + " ");
        for(String s : newAsciis){
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = newAsciis[i];
        }
    }

    public static String[] countingSort(String[] asciis, int index) {
        int max = Integer.MIN_VALUE;
        for (String i : asciis) {
            if (i.length() <= index){
                continue;
            }
            max = Math.max(max, (int) i.charAt(index));
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (String i : asciis) {
            if (i.length() <= index){
                counts[0]++; // the priority is the lowest -- zero
            } else {
                counts[(int)i.charAt(index)]++;
            }
        }

        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            String item = asciis[i];
            int c;
            if (item.length() <= index){
                c = 0;
            } else {
                c = item.charAt(index);
            }
            int place = starts[c];
            sorted[place] = item;
            starts[c] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = {"bob", "ascii", "chandler", "ball", "fitz", "alice", "apple"};
        for(String s : sort(asciis)){
            System.out.print(s + " ");
        }
    }
}
