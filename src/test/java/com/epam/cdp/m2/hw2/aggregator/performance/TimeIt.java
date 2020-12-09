package com.epam.cdp.m2.hw2.aggregator.performance;

class TimeIt {
    public static void code(Code code) {
        long now = System.currentTimeMillis();

        code.run();

        System.out.println(System.currentTimeMillis() - now + "ms");
    }
}
