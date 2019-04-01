package lessons;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FishTest {

    private static final int UPSTREAM = 0;
    private static final int DOWNSTREAM = 1;

    @Test
    public void test() {
        int[] a = {4, 3, 2, 1, 5};
        int[] b = {0, 1, 0, 0, 0};
        assertThat(fish(a, b), equalTo(2));

        a = new int[] {4, 3, 2, 1, 5};
        b = new int[] {0, 1, 0, 1, 0};
        assertThat(fish(a, b), equalTo(2));

        a = new int[] {4, 3, 2, 6, 5};
        b = new int[] {0, 1, 0, 1, 0};
        assertThat(fish(a, b), equalTo(3));
    }

    private static int fish(int size[], int[] direction) {
        assert size.length > 0 && size.length == direction.length;

        int survival = 0;
        int conflictFishSize = 0;
        for(int i=0; i< size.length; ++i) {

            if (conflictFishSize == 0 && direction[i] == UPSTREAM) {
                survival++;
            } else if (direction[i] == DOWNSTREAM) {
                if (size[i] > conflictFishSize) {
                    survival++;
                }
                conflictFishSize = size[i];
            } else if (conflictFishSize > 0 && direction[i] == UPSTREAM) {
                conflictFishSize = combat(conflictFishSize, size[i]);
            }
        }

        return survival;
    }

    private static int combat(int downStreamFishSize, int upStreamFishSize) {
        return Math.max(downStreamFishSize, upStreamFishSize);
    }
}