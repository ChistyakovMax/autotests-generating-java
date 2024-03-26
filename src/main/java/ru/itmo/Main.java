package ru.itmo;

import ru.itmo.generator.page.PageGenerator;
import ru.itmo.generator.testcase.TestCaseGenerator;

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