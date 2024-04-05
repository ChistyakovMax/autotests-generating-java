package ru.itmo;

import ru.itmo.generator.page.PageGenerator;
import ru.itmo.generator.testsuite.TestSuiteGenerator;

public class Main {
    public static void main(String[] args) {
        PageGenerator pg = new PageGenerator();
        TestSuiteGenerator tsg = new TestSuiteGenerator();
        try {
            pg.generatePages();
            tsg.generateAllTestSuites();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}