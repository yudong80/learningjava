package demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Problem03 {

    @Test
    public void test() {
        int[] a ={4, 3, 3, 4, 1, 2, 2, 3, 6, 5, 4, 5};
        assertEquals(new Problem03().solution(a), "YES");
    }

    class Pair {
        int first;
        int second;

        public Pair(int a, int b) { first = a; second = b;}
        public void rotate() {
            if (first > second) {
                int tmp = second;
                first = second;
                second = tmp;
            }
        }
    }

    public String solution(int[] A) {
        //make pair
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(A[0], A[1]));
        list.add(new Pair(A[2], A[3]));
        list.add(new Pair(A[4], A[5]));
        list.add(new Pair(A[6], A[7]));
        list.add(new Pair(A[8], A[9]));
        list.add(new Pair(A[10], A[11]));

        for (Pair p : list) {
            p.rotate();
        }

        list = buildFirstLevel(list);
        list = buildSecondLevel(list);
        list = buildThirdLevel(list);

        return list.isEmpty() ? "YES" : "NO";
    }

    private List<Pair> buildFirstLevel(List<Pair> input) {
        //1. find smallest
        int idx = -1;
        int min = Integer.MAX_VALUE;
        for (int i=0; i< input.size(); ++i) {
            Pair pair = input.get(i);
            if (pair.first < min) {
                idx = i;
                min = pair.first;
            }
        }

        //System.out.println("idx : " + idx);
        if (idx < 0) return input;

        //2. find neigbhor
        int idx2 = -1;
        int target = input.get(idx).second + 1;
        for (int i=0; i< input.size(); ++i) {
            Pair pair = input.get(i);
            if (pair.first == target) {
                idx2 = i;
            }
        }

        //System.out.println("idx2 : " + idx2 + " >> target : " + target);
        if (idx2 < 0) return input;

        //3. find neighbor
        int idx3 = -1;
        int target2 = input.get(idx2).second + 1;
        for (int i=0; i< input.size(); ++i) {
            Pair pair = input.get(i);
            if (pair.first == target2) {
                idx3 = i;
            }
        }

        List<Pair> newList = new ArrayList<>();
        for (int i=0; i< input.size(); ++i) {
            if (i == idx || i == idx2 || i == idx3) continue;
            newList.add(input.get(i));
        }

        //System.out.println("idx3 : " + idx3 + " >> target2 : " + target2);
        return newList;
    }

    private List<Pair> buildSecondLevel(List<Pair> input) {
        //1. find smallest
        int idx = -1;
        int min = Integer.MAX_VALUE;
        for (int i=0; i< input.size(); ++i) {
            Pair pair = input.get(i);
            if (pair.first < min) {
                idx = i;
                min = pair.first;
            }
        }

        //System.out.println("idx : " + idx);
        if (idx < 0) return input;

        //2. find neigbhor
        int idx2 = -1;
        int target = input.get(idx).second + 1;
        for (int i=0; i< input.size(); ++i) {
            Pair pair = input.get(i);
            if (pair.first == target) {
                idx2 = i;
            }
        }

        List<Pair> newList = new ArrayList<>();
        for (int i=0; i< input.size(); ++i) {
            if (i == idx || i == idx2 ) continue;
            newList.add(input.get(i));
        }
        return newList;
    }

    private List<Pair> buildThirdLevel(List<Pair> input) {
        List<Pair> emptyList = new ArrayList<>();
        return input.size() == 1 ? emptyList : input;
    }
}
