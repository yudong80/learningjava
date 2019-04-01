package lessons;

import org.junit.Test;

import static java.lang.System.out;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CyclicRotationTest {

    @Test
    public void test1() {
        assertThat(rotateArray(new int[] { 3,8,9,7,6}, 3), equalTo(new int[] {9, 7, 6, 3, 8}));
        assertThat(rotateArray(new int[] { 0,0,0 }, 1), equalTo(new int[] { 0,0,0 }));
        assertThat(rotateArray(new int[] { 1,2,3,4 }, 4), equalTo(new int[] { 1,2,3,4 } ));
    }

    private static int[] rotateArray(int[] input, int n) {
        int[] after = input;
        for(int i=0; i< n; ++i) {
            int[] before = after;
            after = rotateOnce(before);

            out.println(printArray(before) + ">>>" + printArray(after));
        }
        return after;
    }

    private static int[] rotateOnce(int[] input) {
        int[] res = new int[input.length];
        res[0] = input[input.length -1];

        assert res.length == input.length;

        for(int i= 0; i < input.length -1; ++i) {
            res[i+1] = input[i];
        }
        return res;
    }

    private static String printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i : arr) sb.append(i).append(',');
        sb.append(']');

        return sb.toString();
    }
}