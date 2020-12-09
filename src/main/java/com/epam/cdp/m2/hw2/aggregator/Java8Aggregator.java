package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers should not be null");
        }

        return numbers.stream()
                .reduce(0, (acc, value) -> acc + value);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        if (words == null) {
            throw new IllegalArgumentException("words should not be null");
        }

        if (limit < 0) {
            throw new IllegalArgumentException("limit should be positive number");
        }

        return words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .map(wordEntry -> new Pair<String, Long>(wordEntry.getKey(), wordEntry.getValue()))
                .sorted((w1, w2) -> {
                    if (w1.getValue() == w2.getValue()) {
                        return w1.getKey().compareTo(w2.getKey());
                    }

                    return w2.getValue().compareTo(w1.getValue());
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        if (words == null) {
            throw new IllegalArgumentException("words should not be null");
        }

        if (limit < 0) {
            throw new IllegalArgumentException("limit should be positive number");
        }

        return words.stream()
                .collect(Collectors.groupingBy(word -> word.toUpperCase(), Collectors.counting()))
                .entrySet().stream()
                .filter(wordEntry -> wordEntry.getValue() >= 2)
                .map(wordEntry -> wordEntry.getKey())
                .sorted((w1, w2) -> {
                    if (w1.length() == w2.length()) {
                        return w1.compareTo(w2);
                    }

                    return w1.length() - w2.length();
                })
                .limit(limit)
                .collect(Collectors.toList());
    }
}