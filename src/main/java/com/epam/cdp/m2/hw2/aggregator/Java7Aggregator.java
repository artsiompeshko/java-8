package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        if (numbers == null) {
            return 0;
        }

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        if (words == null || limit < 0) {
            return null;
        }

        Map<String, Pair<String, Long>> wordCounts = new HashMap<>();

        // count words
        for (String word : words) {
            Pair<String, Long> wordPair = wordCounts.get(word);

            if (wordPair == null) {
                wordCounts.put(word, new Pair<>(word, 1L));
            } else {
                wordCounts.put(word, new Pair<>(word, wordPair.getValue() + 1));
            }
        }


        List<Pair<String, Long>> result = new ArrayList<>(wordCounts.values());

        // sort
        result.sort(new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> w1, Pair<String, Long> w2) {
                if (w1.getValue() == w2.getValue()) {
                    return w1.getKey().compareTo(w2.getKey());
                }

                return w2.getValue().compareTo(w1.getValue());
            }
        });

        // return limited result
        return result.subList(0, limit > result.size() ? result.size() : (int) limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        if (words == null || limit < 0) {
            return null;
        }

        Set<String> duplicates = new HashSet<>();
        Set<String> entries = new HashSet<>();

        for (String word : words) {
            if (entries.contains(word.toLowerCase())) {
                duplicates.add(word.toUpperCase());
            } else {
                entries.add(word.toLowerCase());
            }
        }

        List<String> result = new ArrayList<>(duplicates);

        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }

                return o1.length() - o2.length();
            }
        });

        return result.subList(0, limit > result.size() ? result.size() : (int) limit);
    }
}
