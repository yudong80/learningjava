package lessons;

import org.junit.Test;

import static java.lang.System.out;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BinaryGapTest {

    @Test
    public void test01() {
        assertThat(binaryGap(9), equalTo(2));
        assertThat(binaryGap(529), equalTo(4));
        assertThat(binaryGap(15), equalTo(0));
        assertThat(binaryGap(32), equalTo(0));
    }

    private static int binaryGap(int n) {
        String input = Integer.toBinaryString(n);

        int maxLen = 0;
        int len = 0;

        out.println("input : " + input);
        for(char c : input.toCharArray()) {
            assert c == '0' || c == '1';

            if (c == '1' && len == 0) { //start
                len = 1;
            } else if (c == '0' && len > 0) { //mid
                len++;
            } else if (c == '1' && len > 0) { //end
                len--;
                if (len > maxLen) {
                    maxLen = len;
                }
                len = 0;
            }

//            out.println("char = " + c + " >> len : " + len + " >>> maxLen = " + maxLen);
        }

        return maxLen;
    }

}
