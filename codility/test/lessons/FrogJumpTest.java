package lessons;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FrogJumpTest {

    @Test
    public void test() {
        assertThat(frogJump(10, 85, 30), equalTo(3));
        assertThat(frogJump(10, 105, 30), equalTo(4));
    }

    public static int frogJump(int x, int y, int d) {
        assert x <= y;

        return (int) Math.ceil((double)(y - x) / d);
    }
}