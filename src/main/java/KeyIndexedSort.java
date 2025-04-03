public class KeyIndexedSort {
    private static final int R = 256;  // Extended ASCII characters (0-255)
    private static final int CUTOFF = 15;  // Optional insertion sort cutoff

    // Non-instantiable class
    public KeyIndexedSort() { }

    /**
     * Sorts strings by their d-th character using key-indexed counting sort
     * @param a Array to sort
     * @param d Character position to sort by (0-indexed)
     */
    public static void sortByPosition(String[] a, int d) {
        //let us store length of array into variable called "n"
        int n = a.length;

        //if array has only 1 element no need to sort at all
        if (n <= 1) return;

        //let us create extra space we can store string based count frequency
        String[] aux = new String[n];
        //will maintain count frequency
        int[] count = new int[R + 2];

        //let us first count the number of frequency
        for (String s : a) {
            count[charAt(s, d) + 2]++;  // Shift index by 2 to handle -1 case
        }

        //let convert our number count into the index
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        //let fill up the array with each string
        for (String s : a) {
            aux[count[charAt(s, d) + 1]++] = s;
        }

        //let copy back to our original array which given as input
        System.arraycopy(aux, 0, a, 0, n);
    }

    // Get d-th character or -1 if out of bounds
    private static int charAt(String s, int d) {

        return d < s.length() ? s.charAt(d) : -1;
    }

    /**
     * Checks if array is sorted by d-th character
     * @param a Array to check
     * @param d Character position to verify
     * @return True if sorted, false otherwise
     */
    public static boolean isSorted(String[] a, int d) {
        for (int i = 1; i < a.length; i++) {
            int current = charAt(a[i], d);
            int previous = charAt(a[i-1], d);
            if (current < previous) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String[] words = {"apple", "banana", "kiwi", "grape", "pear"};

        // Sort by 2nd character (index 1)
        sortByPosition(words, 1);

        for (String word : words) {
            System.out.println(word);
        }

        System.out.println("Sorted correctly: " + isSorted(words, 1));
    }
}
