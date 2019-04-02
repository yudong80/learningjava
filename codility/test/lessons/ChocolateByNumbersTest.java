package lessons;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ChocolateByNumbersTest {

    @Test
    public void test() {
        assertThat(eatChocolates(10, 4), equalTo(5));
    }

    private static final int START_INDEX = 0;
    private static final int FINISHED = -1;

    private static int eatChocolates(int size, int step) {
        boolean[] chocolates = booleanArray(size, true);

        int idx = START_INDEX;
        do {
            idx = eatOne(chocolates, idx, step);
        } while (idx != FINISHED);

        return eaten(chocolates);
    }

    private static int eatOne(boolean[] arr, int index, int step) {
        if (arr[index] == false) return FINISHED;
        arr[index] = false;
        return nextIndex(arr.length, index, step);
    }

    private static int nextIndex(int arrLength, int index, int step) {
        int res = (index + step) % arrLength;
        System.out.println("arrLength: "+ arrLength + " & index= " + index + " &step =" + step + " >> nextIndex : " + res);
        return res;
    }

    private static int eaten(boolean[] arr) {
        int num = 0;
        for (boolean v : arr) {
            num += v? 1: 0;
        }
        return num;
    }

    private static boolean[] booleanArray(int size, boolean val) {
        boolean[] arr = new boolean[size];
        for (int i=0; i< arr.length; ++i) {
            arr[i] = val;
        }
        return arr;
    }
}
