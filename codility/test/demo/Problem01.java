package demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem01 {

    @Test
    public void test() {
//        int[] a ={-3, -14, -5, 7, 8, 42, 8, 3};
//        assertEquals(new Problem01().solution(a), "SUMMER");

        int[] b ={2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
        assertEquals(new Problem01().solution(b), "AUTUMN");
    }

    private static final String WINTER = "WINTER";
    private static final String SPRING = "SPRING";
    private static final String SUMMER = "SUMMER";
    private static final String AUTUMN = "AUTUMN";

    private static final String[] SEASON_NAMES = {
            WINTER,
            SPRING,
            SUMMER,
            AUTUMN};

    public String solution_org(int[] T) {
        final Season[] seasons = {
                new Season(SEASON_NAMES[0]),
                new Season(SEASON_NAMES[1]),
                new Season(SEASON_NAMES[2]),
                new Season(SEASON_NAMES[3])
        };

        int each = T.length / 4;
        for (int i=0; i< T.length; ++i) {
            int category = (i) / each;
            int value = T[i];
            System.out.println("category : " + category);
            seasons[category].update(value);
        }

        int maxDiff = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i=0; i< SEASON_NAMES.length; ++i) {
            int diff = seasons[i].getDifference();
//            System.out.println("idx : " + i + " >> diff : " + diff + " >> max " + seasons[i].getMax() + "  min :" + seasons[i].getMin());
            if (diff > maxDiff) {
                maxIndex = i;
                maxDiff = diff;
            }
        }

        return SEASON_NAMES[maxIndex];
    }

    public String solution(int[] T) {
        final Season[] seasons = {
                new Season(SEASON_NAMES[0]),
                new Season(SEASON_NAMES[1]),
                new Season(SEASON_NAMES[2]),
                new Season(SEASON_NAMES[3])
        };

        int each = T.length / 4;
        for (int i=0; i< T.length; ++i) {
            int category = (i) / each;
            int value = T[i];
            //System.out.println("category : " + category);
            seasons[category].update(value);
        }

        int maxDiff = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i=0; i< SEASON_NAMES.length; ++i) {
            int diff = seasons[i].getDifference();
            if (diff > maxDiff) {
                maxIndex = i;
                maxDiff = diff;
            }
        }

        return SEASON_NAMES[maxIndex];
    }

    class Season {
        private String name;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;

        public Season(String name) { this.name = name; }

        public void update(int num) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        public int getDifference() {
            return max - min;
        }

//        public int getMax() { return max; }
//        public int getMin() { return min; }
    }
}
