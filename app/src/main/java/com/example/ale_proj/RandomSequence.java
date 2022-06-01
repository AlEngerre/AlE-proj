package com.example.ale_proj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomSequence {
    public static List<Integer> getRandom(int minValue, int maxValue, int count) {

        if (maxValue - minValue + 1 < count) {

            throw new RuntimeException();

        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = minValue; i <= maxValue; i++) {

            result.add(i);

        }
        ;

        Collections.shuffle(result);

        return result.subList(0, count);
    }
}
