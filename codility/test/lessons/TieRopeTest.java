package lessons;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TieRopeTest {
    @Test
    public void test() {
        assertThat(tieRopes(new int[] { 1,2,3,4,1,1,3 }, 4), equalTo(3));
    }

    private static int FORWARD = 1;
    private static int BACKWARD = 2;
    private static int SHUFFLE = 3;

    private static int tieRopes(int[] arr, int threshold) {
        List<Integer> list = Arrays.asList(
                tieRope(arr, threshold, FORWARD),
                tieRope(arr, threshold, BACKWARD),
                tieRope(arr, threshold, SHUFFLE));
//        System.out.println(" >>> res : " + list);

        Collections.reverse(list);
        return list.get(0);
    }

    private static int tieRope(int[] arr, int threshold, int direction) {

        Pair<Integer, Integer> pair = new Pair<>(0,0);

        if (FORWARD == direction) {
            for (int rope : arr) {
                pair = tieRopeInternal(rope, threshold, pair);
            }
        } else if (BACKWARD == direction) {
            for (int i=0; i< arr.length; ++i) {
                int rope = arr[arr.length-1-i];
                pair = tieRopeInternal(rope, threshold, pair);
            }
        } else if (SHUFFLE == direction) {
            Integer[] newArr = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
            Collections.shuffle(Arrays.asList(newArr));

            for (int rope : newArr) {
                pair = tieRopeInternal(rope, threshold, pair);
            }
        }

        return pair.getValue();
    }

    private static Pair<Integer, Integer> tieRopeInternal(int rope, int threshold, Pair<Integer, Integer> pair) {
        int localLen = pair.getKey();
        int res = pair.getValue();

        localLen += rope;
        if (localLen >= threshold) {
            localLen = 0;
            res++;
        }

        return new Pair<>(localLen, res);
    }
}
