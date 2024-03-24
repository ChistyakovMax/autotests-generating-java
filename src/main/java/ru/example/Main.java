package ru.example;

import ru.example.generator.page.PageGenerator;
import ru.example.generator.testcase.TestCaseGenerator;

public class Main {
    public static void main(String[] args) {
        PageGenerator pg = new PageGenerator();
        TestCaseGenerator tg = new TestCaseGenerator();
        try {
            //pg.generatePages();
            tg.generateTestCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}