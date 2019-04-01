package example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OddOcurrencesInArrayTest {
    private static final int PAIRED = 1;
    private static final int UNPAIRED = 0;
    private static final int NOT_FOUND = -1;

    @Test
    public void test1() {
        assertThat(unPaired(new int[]{ 9,3,9,3,9,7,9}), equalTo(7));
        assertThat(unPaired(new int[]{ 9,3,9,3,9,7,9, 100, 200, 300, 200, 100, 300}), equalTo(7));
    }

    private static int unPaired(int[] input) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int key : input) {
            if (!map.containsKey(key)) {
                map.put(key, UNPAIRED);
            } else {
                if (map.get(key) == PAIRED) {
                    map.put(key, UNPAIRED);
                } else { //unpaired
                    map.put(key, PAIRED);
                }
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) == UNPAIRED) {
                return key;
            }
        }

        return NOT_FOUND;
    }
}