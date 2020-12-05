package com.epam.cdp.m2.hw2.aggregator.performance;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;
import com.epam.cdp.m2.hw2.aggregator.Java7Aggregator;
import com.epam.cdp.m2.hw2.aggregator.Java8Aggregator;
import com.epam.cdp.m2.hw2.aggregator.Java8ParallelAggregator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class JavaAggregatorPerformanceTest {
    @Parameterized.Parameter(0)
    public Aggregator aggregator;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public long limit;

    private static String getRandomString() {
        int r = (int) (Math.random() * 5);
        String name = new String[]{"India", "USA", "UK", "Russia", "Belarus"}[r];

        return name;
    }

    private static List<String> getRandomWords(long size) {
        List<String> words = new ArrayList<>();

        for (int i = 0; i < 100000; i += 1) {
            words.add(getRandomString());
        }

        return words;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();


        data.add(new Object[]{new Java7Aggregator(), getRandomWords(100000), 100});
        data.add(new Object[]{new Java8Aggregator(), getRandomWords(100000), 100});
        data.add(new Object[]{new Java8ParallelAggregator(), getRandomWords(100000), 100});

        return data;
    }

    @Test
    public void testPerformance() {
        System.out.println(this.aggregator.getClass().getName() + " " + "getDuplicates");
        TimeIt.code(() -> this.aggregator.getDuplicates(this.words, this.limit));

        System.out.println(this.aggregator.getClass().getName() + " " + "getMostFrequentWords");
        TimeIt.code(() -> this.aggregator.getMostFrequentWords(this.words, this.limit));
    }
}
