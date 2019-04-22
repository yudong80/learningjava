package demo;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MissingInteger {

    @Test
    public void test() {
        int[] a ={1, 3, 6, 4, 1, 2};  //5
        assertEquals(new MissingInteger().solution(a), 5);
        int[] b = {1, 2, 3}; //4
        assertEquals(new MissingInteger().solution(b), 4);
        int[] c = {-1, -3}; //1
        assertEquals(new MissingInteger().solution(c), 1);
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        if (A.length == 1) return singlePositive(A[0]);

        int firstNum = A[0];
        if (firstNum > 2) return 1;

        int lastNum = A[A.length -1];
        if (lastNum < 1) return 1;

        for (int i=0; i< A.length -1; ++i) {
            if (A[i] <= 0) continue;

            int diff = A[i+1] - A[i];
            if (diff == 0) continue;
            if (diff == 1) continue;

            return A[i] +1;
        }
        return lastNum +1;
    }

    private int singlePositive(int n) {
        return n == 1 ? 2 : 1;
    }
}
